package org.courseregistration.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.courseregistration.client.client.ServerException;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

import javax.net.ssl.SSLContext;

public class HttpClientFactory {
    /**
     * A factory to get webtarget with http client configured with user details,uri details and SSL
     * @param username username of the user requesting a resource
     * @param password password of a user requesting a resource
     * @return ResteasyWebTarget RestEasy Target
     * @throws ServerException
     */
    public static ResteasyWebTarget getWebTarget(final String username, final String password) throws ServerException {
        try {
            final HttpConfig httpConfig = new HttpConfig.Builder().build();

            final HttpHost targetHost = new HttpHost(httpConfig.getHost(), httpConfig.getPort(), httpConfig.getProtocol());

            final SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .useTLS()
                    .build();

            final SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new AllowAllHostnameVerifier());

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", connectionSocketFactory)
                    .build();

            final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(httpConfig.getMaxTotalConnections());
            cm.setDefaultMaxPerRoute(httpConfig.getMaxDefaultConnectionsPerRoute());

            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                    new UsernamePasswordCredentials(username, password));


            final CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setDefaultCredentialsProvider(credentialsProvider)
                    .setConnectionManager(cm)
                    .build();

            final DigestScheme digestAuth = new DigestScheme();
            digestAuth.overrideParamter("realm", httpConfig.getRealmName());

            final AuthCache authCache = new BasicAuthCache();
            authCache.put(targetHost, digestAuth);

            final BasicHttpContext localContext = new BasicHttpContext();
            localContext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);

            final ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient, localContext);
            final ResteasyClient resteasyClient = new ResteasyClientBuilder().httpEngine(engine).build();
            return resteasyClient.target(httpConfig.getBaseUrl());
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    /**
     * get WebTarget For Anonymous User
     * @return ResteasyWebTarget
     */
    public static ResteasyWebTarget getWebTargetForAnonymousUser() {

        final HttpConfig httpConfig = new HttpConfig.Builder().build();

        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(httpConfig.getMaxTotalConnections());
        cm.setDefaultMaxPerRoute(httpConfig.getMaxDefaultConnectionsPerRoute());

        final CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(cm)
                .build();

        final ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient);
        final ResteasyClient resteasyClient = new ResteasyClientBuilder().httpEngine(engine).build();
        return resteasyClient.target(httpConfig.getBaseUrl());
    }

}



package org.courseregistration.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

public class HttpClientFactory {

    public static ResteasyWebTarget getWebTarget(final String username, final String password) {

        final HttpConfig httpConfig = new HttpConfig.Builder().build();

        final HttpHost targetHost = new HttpHost(httpConfig.getHost(), httpConfig.getPort(), httpConfig.getProtocol());

        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
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

        final ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient,localContext);
        final ResteasyClient resteasyClient = new ResteasyClientBuilder().httpEngine(engine).build();
        return resteasyClient.target(httpConfig.getBaseUrl());
    }


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



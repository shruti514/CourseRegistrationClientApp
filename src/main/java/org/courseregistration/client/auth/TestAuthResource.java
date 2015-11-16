package org.courseregistration.client.auth;


import org.apache.http.HttpHost;
import org.apache.http.HttpMessage;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.protocol.BasicHttpContext;
import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.model.Professor;
import org.courseregistration.client.model.Student;
import org.courseregistration.client.resources.StudentResource;
import org.courseregistration.client.responses.StudentResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import sun.net.www.http.HttpClient;
import sun.net.www.protocol.http.AuthScheme;

import javax.swing.*;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.courseregistration.client.auth.DigestAuthDetails.from;


public class TestAuthResource {

    public static void getResource() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target("http://localhost:8888/api.courseregistration").path("students/100025");

        String username = "userProf1234";
        String password = "pass";

        Response response = target.request()
                .header("Authorization", from(username, password).getDigestHeader())
                .accept(APPLICATION_JSON)
                .get();
        if (401 == response.getStatus()) {
            String headerString = response.getHeaderString("WWW-Authenticate");

            AuthHeaderParser authHeaderParser = new AuthHeaderParser(headerString);
            response.close();

            response = target.request()
                    .header("Authorization", from(username, password)
                            .forHTTPMethod(HttpMethod.GET)
                            .withNonce(authHeaderParser.getNonce())
                            .withNounceCount(2)
                            .withUri("api.courseregistration/student/100025").getDigestHeader()
                    )
                    .accept(APPLICATION_JSON)
                    .get();
        }
        StudentResponse studentResponse = response.readEntity(StudentResponse.class);
        System.out.println(studentResponse.toString());
        response.close();
    }


    public static void test(){

        String username = "userProf1234";
        String password = "pass";
        Professor professor = new Professor();

        UserContext userContext = UserContext.forUser(username,password,professor);

        ResteasyClient resteasyClient = HttpClientFactory.getClient(userContext);
        ResteasyWebTarget target = resteasyClient.target("http://localhost:8888/api.courseregistration");

        StudentResource proxy = target.proxy(StudentResource.class);

        Response studentResponse = proxy.getStudent(100025);

        System.out.println(studentResponse.readEntity(StudentResponse.class));

        resteasyClient.close();
    }

}





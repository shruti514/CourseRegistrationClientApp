package org.courseregistration.client.auth;


import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.client.ServerException;
import org.courseregistration.client.resources.StudentResource;
import org.courseregistration.client.responses.StudentResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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


    public static void test() throws ServerException {

        String username = "userProf1234";
        String password = "pass";

        ResteasyWebTarget target = HttpClientFactory.getWebTarget(username, password);

        StudentResource proxy = target.proxy(StudentResource.class);

        Response studentResponse = proxy.getStudent(100025);

        System.out.println(studentResponse.readEntity(StudentResponse.class));

        target.getResteasyClient().close();
    }

}





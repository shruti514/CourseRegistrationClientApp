package org.courseregistration.client.auth;


import org.courseregistration.client.resources.StudentResource;
import org.courseregistration.client.responses.StudentResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.courseregistration.client.auth.DigestAuthDetails.digestBuilder;


public class TestAuthResource {

    public static void getResource() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        /*ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8888/api.courseregistration");

        String username = "userProf1234";
        String password="pass";

        String digestHeaderValue = digestBuilder(username, password).build().toString();
        target.request().header("Authorization", digestHeaderValue);

        StudentResource proxy = target.proxy(StudentResource.class);
        Response response = proxy.getStudent(100025);

        StudentResponse studentResponse = (StudentResponse)response.getEntity();
        System.out.println(studentResponse);*/


        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target("http://localhost:8888/api.courseregistration").path("students/100025");

        String username = "userProf1234";
        String password="pass";

        Response response = target.request()
                                    .header("Authorization", digestBuilder(username, password).build().toString())
                                    .accept(APPLICATION_JSON)
                                    .get();
        Response.StatusType statusInfo = response.getStatusInfo();
        StudentResponse value = response.readEntity(StudentResponse.class);
        System.out.println(value.toString());
        response.close();
    }

}





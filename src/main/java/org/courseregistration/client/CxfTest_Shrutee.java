package org.courseregistration.client;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.courseregistration.client.responses.StudentResponse;
import org.jboss.resteasy.client.ProxyFactory;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class CxfTest_Shrutee {

    public static void  getStudent(){
        List<Object> providers = new ArrayList<>();
        providers.add( new JacksonJaxbJsonProvider() );

        WebClient client = WebClient.create("http://localhost:8888/api.courseregistration/", providers);
        client = client.accept("application/json").type("application/json").path("/students/100025");

        StudentResponse studentResponse = client.get(StudentResponse.class);
        System.out.println("Student Response:" + studentResponse.toString());
    }
}

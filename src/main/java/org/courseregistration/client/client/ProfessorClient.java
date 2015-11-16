package org.courseregistration.client.client;

import org.courseregistration.client.resources.ProfessorResource;
import org.courseregistration.client.responses.ProfessorResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * Created by SHITAL on 11/13/2015.
 */

public class ProfessorClient {
    public static  void getProfessor(){

        ResteasyClient resteasyClient = new ResteasyClientBuilder().build();

        ProfessorResource client = ProxyFactory.create(ProfessorResource.class,
                "http://localhost:8888/api.courseregistration/");
        ProfessorResponse professor  = client.getProfessor(100016);

        System.out.println(professor.toString());

    }

}

package org.courseregistration.client.client;

import org.courseregistration.client.resources.ProfessorResource;
import org.courseregistration.client.responses.ProfessorResponse;
import org.jboss.resteasy.client.ProxyFactory;

import javax.ws.rs.core.Response;


/**
 * Created by SHITAL on 11/13/2015.
 */

public class ProfessorClient {
    //  Get information about professor
    public static  void getProfessor(){
       //  ResteasyClient resteasyClient = new ResteasyClientBuilder().build();
        ProfessorResource professorResource = ProxyFactory.create(ProfessorResource.class,
                "http://localhost:8888/api.courseregistration/");
        Response professor  = professorResource.getProfessor(100016);
        System.out.println(professor.toString());
    }

    //  Get information about all professors
    public static void getAllProfessors(){
        ProfessorResource professorResource = ProxyFactory.create(ProfessorResource.class,"http://localhost:8888/api.courseregistration/");
        ProfessorResponse professor = professorResource.getAllProfessors();
        System.out.println(professor.toString());
    }

    //  Add single professor to database
    public static void setProfessor(){
        ProfessorResource professorResource = ProxyFactory.create(ProfessorResource.class,"http://localhost:8888/api.courseregistration");
        ProfessorResponse professor = professorResource.setProfessor(65487);
        System.out.println("Professor Added");
    }

    //  Add multiple students
    public static void setMultipleStudents(){
        ProfessorResource professorResource = ProxyFactory.create(ProfessorResource.class,"http://localhost:8888/api.courseregistration");
        ProfessorResponse professor = professorResource.setMultipleProfessors();
    }

    //  delete single professor
    public static void deleteProfessor(){
        ProfessorResource professorResource = ProxyFactory.create(ProfessorResource.class,"http://localhost:8888/api.courseregistration");
        ProfessorResponse professor = professorResource.deleteProfessor(100026);
        System.out.println(professor.toString());
    }

    //  delete multiple professors
    public  static void deleteMultipleProfessors(){
        ProfessorResource professorResource = ProxyFactory.create(ProfessorResource.class,"http://localhost:8888/api.courseregistration");
        ProfessorResponse professor = professorResource.deleteMultipleProfessors();
    }

    //  update professor details
    public  static void updateProfessor(){
        ProfessorResource professorResource = ProxyFactory.create(ProfessorResource.class,"http://localhost:8888/api.courseregistration");
        ProfessorResponse professor = professorResource.updateProfessor(100016);
    }
}

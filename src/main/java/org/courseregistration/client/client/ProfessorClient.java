package org.courseregistration.client.client;

import java.util.Scanner;

import javax.ws.rs.core.Response;

import javax.ws.rs.PathParam;
import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.model.Professor;
import org.courseregistration.client.resources.ProfessorResource;
import org.courseregistration.client.responses.ProfessorResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;


/**Created by SHITAL on 11/13/2015.*/

public class ProfessorClient {

    private ProfessorResource proxy = null;
    private ResteasyWebTarget target = null;

    Scanner reader = new Scanner(System.in);

    public void getConnection(UserContext userContext) throws ServerException {
        if (userContext != null) {
            target = HttpClientFactory.getWebTarget(userContext.getUsername(),
                    userContext.getPassword());
        } else {
            target = HttpClientFactory.getWebTargetForAnonymousUser();
        }
        proxy = target.proxy(ProfessorResource.class);
    }

    public void closeConection() {
        try {
            if (!target.getResteasyClient().isClosed())
                target.getResteasyClient().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 1.See professor profile
    public ProfessorResponse getProfessor(int id) throws ServerException{
        Response response = proxy.getProfessor(id);
        if (response.getStatus() == 200){
            return response.readEntity(ProfessorResponse.class);
        }

        throwNewException(response);
        return null;
    }

    public ProfessorResponse getAllProfessors() throws ServerException{
        Response response = proxy.getAllProfessors();

        if(response.getStatus() == 200){
            return response.readEntity(ProfessorResponse.class);
        }

        throwNewException(response);
        return null;
    }

    public ProfessorResponse deleteProfessor(Long id) throws ServerException{
        Response response = proxy.deleteProfessor(id);
        if(response.getStatus() == 200){
            return response.readEntity(ProfessorResponse.class);
        }
        throwNewException(response);
        return null;
    }

    //2. update professor profile
    public  Response updateProfessor(@PathParam("professorId") long id, Professor current) throws ServerException{
        Professor professor = updateForProfessor(current);
        if(professor != null){
            Response response = proxy.updateProfessor(id,professor);
           if(response.getStatus() == 200){
               System.out.println(response.toString());
               return response;
           }
            throwNewException(response);
        }
        return null;
    }

    private void throwNewException(Response response) throws ServerException {
        String errorResponse = response.readEntity(String.class);
        target.getResteasyClient().close();
        System.out.println("Error:" + response.getStatus() + errorResponse);
        throw new ServerException(errorResponse);
    }

    private Professor updateForProfessor(Professor professor){
        try {
            System.out.println();
            System.out
                    .println("___________________________________________________________________");
            System.out.println("Professor update form");
            System.out
                    .println("___________________________________________________________________");
            System.out.println("Please enter values for fields to update: ");
            String input = "";


            System.out.println("Do you want to Submit update? [y:n]: ");
            if (reader.nextLine().equalsIgnoreCase("y")) {
                System.out.println("You are about to Update above fields.");
                return professor;
            } else {
                System.out.println("Successfully Cancelled.");
                return null;
            }
        }catch (Exception e){
                e.printStackTrace();
            }
            System.out
                    .println("You are not able to update as some values are either empty or not set properly.");
            return null;
        }
}
























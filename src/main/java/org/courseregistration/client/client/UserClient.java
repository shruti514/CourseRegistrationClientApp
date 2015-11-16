package org.courseregistration.client.client;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.model.LoginRequest;
import org.courseregistration.client.model.LoginResponse;
import org.courseregistration.client.resources.UserResource;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;

public class UserClient {

    public static LoginResponse login(final String username, final String password){

        ResteasyWebTarget target = HttpClientFactory.getWebTarget(username, password);

        UserResource proxy = target.proxy(UserResource.class);

        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        Response response = proxy.login(request);

        LoginResponse loginResponse = response.readEntity(LoginResponse.class);

        System.out.println(loginResponse);

        target.getResteasyClient().close();

        return  loginResponse;
    }
}

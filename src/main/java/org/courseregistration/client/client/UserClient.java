package org.courseregistration.client.client;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.responses.ErrorResponse;
import org.courseregistration.client.model.LoginRequest;
import org.courseregistration.client.model.LoginResponse;
import org.courseregistration.client.resources.UserResource;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;

public class UserClient {

    public static LoginResponse login(final String username, final String password) throws ServerException {

        ResteasyWebTarget target = HttpClientFactory.getWebTarget(username, password);

        UserResource proxy = target.proxy(UserResource.class);

        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        Response response = proxy.login(request);

        if(response.getStatus() == 200){
            return response.readEntity(LoginResponse.class);
        }

        ErrorResponse errorResponse = response.readEntity(ErrorResponse.class);
        target.getResteasyClient().close();

        throw new ServerException(errorResponse);
    }
}

package org.courseregistration.client.client;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.model.LoginRequest;
import org.courseregistration.client.model.LoginResponse;
import org.courseregistration.client.resources.UserResource;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class UserClient {
    private static final Logger logger = LoggerFactory.getLogger(UserClient.class);

    /**
     * Login based on roles
     * @param username
     * @param password
     * @return LoginResponse
     * @throws ServerException
     */
    public static LoginResponse login(final String username, final String password) throws ServerException {

        ResteasyWebTarget target = HttpClientFactory.getWebTarget(username, password);

        UserResource proxy = target.proxy(UserResource.class);

        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        Response response = proxy.login(request);

        if (response.getStatus() == 200) {
            return response.readEntity(LoginResponse.class);
        }

        String errorResponse = response.readEntity(String.class);
        target.getResteasyClient().close();
        logger.error("Error:"+response.getStatus()+errorResponse);
        throw new ServerException(errorResponse);
    }
}

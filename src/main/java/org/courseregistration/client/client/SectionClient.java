package org.courseregistration.client.client;

import javax.ws.rs.core.Response;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.resources.SectionResource;
import org.courseregistration.client.responses.SectionResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class SectionClient {

	private SectionResource proxy;
	private ResteasyWebTarget target;

	public void getConnection(UserClient userClient) {
		target = HttpClientFactory.getWebTargetForAnonymousUser();
		proxy = target.proxy(SectionResource.class);
	}

	public SectionResponse getSection(int id) throws ServerException {
		Response response = proxy.getSection(id);
		if (response.getStatus() == 200) {
			return response.readEntity(SectionResponse.class);
		}

		throwNewException(response);
		return null;
	}

	public SectionResponse getAllSections() throws ServerException {
		Response response = proxy.getAllSections();

		if (response.getStatus() == 200) {
			return response.readEntity(SectionResponse.class);
		}

		throwNewException(response);
		return null;
	}

	private void throwNewException(Response response) throws ServerException {
		String errorResponse = response.readEntity(String.class);
		target.getResteasyClient().close();
		System.out.println("Error:" + response.getStatus() + errorResponse);
		throw new ServerException(errorResponse);
	}

}

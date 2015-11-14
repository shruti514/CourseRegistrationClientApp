package org.courseregistration.client.client;

import org.courseregistration.client.resources.SectionResource;
import org.courseregistration.client.responses.SectionResponse;
import org.jboss.resteasy.client.ProxyFactory;

public class SectionClient {

	public static void getSection() {

		SectionResource resource = ProxyFactory.create(SectionResource.class,
				"http://localhost:8888/api.courseregistration/");
		SectionResponse response = resource.getSection(100035);

		System.out.println(response.getSection().toString());
	}
}

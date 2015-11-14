package org.courseregistration.client.client;

import org.courseregistration.client.model.Section;
import org.courseregistration.client.resources.SectionResource;
import org.jboss.resteasy.client.ProxyFactory;

public class SectionClient {

	public static void getSection() {

		SectionResource resource = ProxyFactory.create(SectionResource.class,
				"http://localhost:8888/api.courseregistration/");
		Section response = resource.getSection(100020);

		System.out.println(response.toString());
	}
}

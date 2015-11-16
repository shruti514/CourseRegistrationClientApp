package org.courseregistration.client.client;

import java.util.List;

import org.courseregistration.client.resources.SectionResource;
import org.courseregistration.client.responses.SectionResponse;
import org.jboss.resteasy.client.ProxyFactory;

public class SectionClient {

	private static SectionResource resource = ProxyFactory.create(
			SectionResource.class,
			"http://localhost:8888/api.courseregistration/");

	public static void getSection() {

		// Section response = resource.getSection(100020);
		//
		// System.out.println(response.toString());

		getAllSections();
	}

	public static void getAllSections() {
		SectionResponse response = resource.getAllSections();

		// Content content = response.getContent();
		// List<Section> list = response.getContent();
		// List<Section> list = content.getSection();

		List<SectionResponse> contents = response.getContent();

		for (SectionResponse content : contents) {
			System.out.println(content.getSection().toString());
		}
	}
}
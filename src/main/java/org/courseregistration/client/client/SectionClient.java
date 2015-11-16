package org.courseregistration.client.client;

import java.util.List;

import org.courseregistration.client.model.Content;
import org.courseregistration.client.model.Section;
import org.courseregistration.client.resources.SectionResource;
import org.courseregistration.client.responses.SectionResponse;
import org.jboss.resteasy.client.ProxyFactory;

public class SectionClient {

	private static SectionResource resource = ProxyFactory.create(
			SectionResource.class,
			"http://localhost:8888/api.courseregistration/");

	public static void getSection() {

		Section response = resource.getSection(100020);

		System.out.println(response.toString());
	}

	public static void getAllSections() {
		SectionResponse response = resource.getAllSections();

		// Content content = response.getContent();
		// List<Section> list = response.getContent();
		// List<Section> list = content.getSection();

		List<Content> contents = response.getContent();

		for (Content content : contents) {
			System.out.println(content.getSection().toString());
		}
	}
}

package org.courseregistration.client.client;

import java.util.Scanner;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.model.Section;
import org.courseregistration.client.resources.SectionResource;
import org.courseregistration.client.responses.SectionResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class SectionClient {

	private SectionResource proxy = null;
	private ResteasyWebTarget target = null;

	Scanner reader = new Scanner(System.in);

	public void getConnection(UserContext userContext) throws ServerException {
		if (userContext != null) {
			target = HttpClientFactory.getWebTarget(userContext.getUsername(),
					userContext.getPassword());
		} else {
			target = HttpClientFactory.getWebTargetForAnonymousUser();
		}
		proxy = target.proxy(SectionResource.class);
	}

	public void closeConection() {
		try {
			if (!target.getResteasyClient().isClosed())
				target.getResteasyClient().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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

	public SectionResponse addSection() throws ServerException {

		// Section section = getNewSection();
		//
		// if (section != null) {
		// // section.setCourse(course);
		// Response response = proxy.addSection(section);
		// if (response.getStatus() == Response.Status.CREATED.getStatusCode())
		// {
		// return response.readEntity(SectionResponse.class);
		// }
		//
		// throwNewException(response);
		// }
		return null;
	}

	public SectionResponse deleteSection(Long id) throws ServerException {
		Response response = proxy.deleteSection(id);
		if (response.getStatus() == 200) {
			return response.readEntity(SectionResponse.class);
		}

		throwNewException(response);
		return null;
	}

	public Response updateSection(@PathParam("sectionId") long id,
			Section current) throws ServerException {
		Section section = updateForSection(current);

		if (section != null) {
			Response response = proxy.updateSection(id, section);
			if (response.getStatus() == 200) {
				System.out.println(response.toString());
				return response;
			}
			throwNewException(response);
		}
		return null;
	}

	private Section updateForSection(Section section) {

		try {

			System.out.println();
			System.out
					.println("___________________________________________________________________");
			System.out.println("Section update form");
			System.out
					.println("___________________________________________________________________");
			System.out.println("Please enter values for fields to update: ");
			String input = "";

			System.out.println("Price: [ " + section.getPrice() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setPrice(Integer.parseInt(input));

			System.out.println("Day of Week: [ " + section.getDayOfWeek()
					+ " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				section.setDayOfWeek(input);

			// System.out.println("Membership Plan [basic/premium]: [ "
			// + section.getPlan() + " ]:");
			// input = (reader.readLine());
			// if (!input.trim().isEmpty())
			//
			// section.setPlan(input);

			// System.out.println("Date of expiration[MM/yyyy]: [ "
			// + section.getExpirationDate() + " ]:");
			// input = (reader.readLine());
			// if (!input.trim().isEmpty())
			//
			// section.setExpirationDate(input);

			System.out.println("Do you want to Submit update? [y:n]: ");
			if (reader.nextLine().equalsIgnoreCase("y")) {
				System.out.println("You are about to Update above fields.");
				return section;
			} else {
				System.out.println("Successfully Cancelled.");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("You are not able to update as some values are either empty or not set properly.");
		return null;
	}

	public SectionResponse getSectionBySearch(int id) throws ServerException {
		// Response response = proxy.findSectionsQueryParams(coursename,
		// lastname, price, gteprice, lteprice, dayofweek, semester, coursecode,
		// uriInfo)
		// if (response.getStatus() == 200) {
		// return response.readEntity(SectionResponse.class);
		// }
		//
		// throwNewException(response);
		return null;
	}

	private void throwNewException(Response response) throws ServerException {
		String errorResponse = response.readEntity(String.class);
		target.getResteasyClient().close();
		System.out.println("Error:" + response.getStatus() + errorResponse);
		throw new ServerException(errorResponse);
	}

}

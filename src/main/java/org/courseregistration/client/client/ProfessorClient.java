package org.courseregistration.client.client;

import java.sql.Date;
import java.util.Scanner;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.courseregistration.client.HttpClientFactory;
import org.courseregistration.client.auth.UserContext;
import org.courseregistration.client.model.Address;
import org.courseregistration.client.model.Professor;
import org.courseregistration.client.resources.ProfessorResource;
import org.courseregistration.client.responses.ProfessorResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/** Created by SHITAL on 11/13/2015. */

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

	public void closeConnection() {
		try {
			if (!target.getResteasyClient().isClosed())
				target.getResteasyClient().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 1.See professor profile
	public ProfessorResponse getProfessor(int id) throws ServerException {
		Response response = proxy.getProfessor(id);
		if (response.getStatus() == 200) {
			return response.readEntity(ProfessorResponse.class);
		}

		throwNewException(response);
		return null;
	}

	public ProfessorResponse getAllProfessors() throws ServerException {
		Response response = proxy.getAllProfessors();

		if (response.getStatus() == 200) {
			return response.readEntity(ProfessorResponse.class);
		}

		throwNewException(response);
		return null;
	}

	public String addProfessor() throws ServerException {
		Professor professor = registrationForm();

		if (professor != null) {
			System.out
					.println("*******************************************************");
			System.out.println(professor.toString());
			System.out
					.println("*******************************************************");
			Response response = proxy.addProfessor(professor);
			if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
				return response.readEntity(String.class);
			}
			throwNewException(response);
		}
		return null;
	}

	private Professor registrationForm() {

		try {

			Professor professor = new Professor();
			Address address = new Address();
			System.out.println();
			System.out
					.println("___________________________________________________________________");
			System.out.println("Professor registration form");
			System.out
					.println("___________________________________________________________________");
			System.out.println("Please enter details: ");

			System.out.println("First name: ");
			professor.setFirstName(reader.nextLine());

			System.out.println("Last name: ");
			professor.setLastName(reader.nextLine());

            System.out.println("emailId: ");
			professor.setEmailId(reader.nextLine());

            System.out.println("Phone Number: ");
            professor.setPhoneNumber(reader.nextLine());

			System.out.println("Faculty type: ");
			professor.setFacultyType(reader.nextLine());

			System.out.println("Bio: ");
			professor.setBio(reader.nextLine());

			System.out.println("Years of experience: ");
			professor.setYearsOfExperience(Integer.parseInt(reader.nextLine()));

			System.out.println("Date of birth [yyyy-mm-dd]:: ");
			professor.setDateOfBirth(Date.valueOf(reader.nextLine()));

			System.out.println("Office hours from: [hh:MM:ss]: ");
			professor.setOfficeHoursFromTime(reader.nextLine());

			System.out.println("Office hours to: [hh:MM:ss]: ");
			professor.setOfficeHoursToTime(reader.nextLine());

			System.out.println("User Name: ");
			professor.setUsername(reader.nextLine());

			System.out.println("Address fields: ");

			System.out.println("Street Name: ");
			address.setStreetName(reader.nextLine());

			System.out.println("Apartment number: ");
			address.setAptNo(Integer.parseInt(reader.nextLine()));

			System.out.println("City: ");
			address.setCity(reader.nextLine());

			System.out.println("State: ");
			address.setState(reader.nextLine());

			System.out.println("Zip code : ");
			address.setZipcode(Integer.parseInt(reader.nextLine()));

			professor.setAddress(address);

			System.out.println("Do you want to Submit registration? [y:n]: ");
			if (reader.nextLine().equalsIgnoreCase("y")) {
				return professor;
			} else {
				System.out.println("Successfully Cancelled.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out
				.println("You are not registered as some values are either empty or not set properly.");
		return null;
	}

	public String deleteProfessor(Long id) throws ServerException {
		Response response = proxy.deleteProfessor(id);
		if (response.getStatus() == 200) {
			return response.readEntity(String.class);
		}
		throwNewException(response);
		return null;
	}

	// 2. update professor profile
	public String updateProfessor(long id,
			Professor current) throws ServerException {
		Professor professor = updateFormProfessor(current);
		if (professor != null) {
			Response response = proxy.updateProfessor(id,professor);
			if (response.getStatus() == 200) {
				return response.readEntity(String.class);
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

	private Professor updateFormProfessor(Professor professor) {

		try {
			System.out.println();
			System.out
					.println("___________________________________________________________________");
			System.out.println("Professor update form");
			System.out
					.println("___________________________________________________________________");
			System.out.println("Please enter values for fields to update: ");
			String input = "";

			System.out.println("Email-Id: [ " + professor.getEmailId() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				professor.setEmailId(input);

			System.out.println("Phone Number [ " + professor.getPhoneNumber()
					+ " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				professor.setPhoneNumber(input);

			System.out.println("Faculty Type [ " + professor.getFacultyType()
					+ " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				professor.setFacultyType(input);

			System.out.println("Bio [ " + professor.getBio() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				professor.setBio(input);

			System.out.println("Years of Experience : [ "
					+ professor.getYearsOfExperience() + " ]:");
			input = (reader.nextLine());
			if (!input.trim().isEmpty())
				professor.setYearsOfExperience(Integer.parseInt(input));

			System.out.println("Do you want to Submit update? [y:n]: ");
			if (reader.nextLine().equalsIgnoreCase("y")) {
				System.out.println("You are about to Update above fields.");
				return professor;
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
}

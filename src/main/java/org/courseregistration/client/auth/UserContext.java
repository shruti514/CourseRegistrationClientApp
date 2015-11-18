package org.courseregistration.client.auth;

import org.courseregistration.client.model.Professor;
import org.courseregistration.client.model.Student;

public class UserContext {

	private String username;
	private String password;

	private static UserContext userContext = null;

	private User loggedInUser;

	public static boolean isStudent() {
		return userContext.loggedInUser instanceof Student;
	}

	public static boolean isProfessor() {
		return userContext.loggedInUser instanceof Professor;
	}

	public String getUsername() {
		return userContext.username;
	}

	public String getPassword() {
		return userContext.password;
	}

	public User getLoggedInUser() {
		return userContext.loggedInUser;
	}

	private UserContext(final String username, final String password,
			final User user) {
		this.username = username;
		this.password = password;
		this.loggedInUser = user;
	}

	public static UserContext forUser(final String username,
			final String password, final User user) {
		if (userContext != null && userContext.username.equals(username)){
			return userContext;
		}
		userContext = new UserContext(username,password,user);
		return  userContext;
	}

	public static Student getStudent() throws Exception {
		if (isStudent())
			return (Student) userContext.loggedInUser;
		throw new Exception("Logged In User is not of type Student");
	}

	public static Professor getProfessor() throws Exception {
		if (isProfessor())
			return (Professor) userContext.loggedInUser;
		throw new Exception("Logged In User is not of type Professor");
	}

	public static void setUserContext(String username, String password,
			User user) {
		userContext.username = username;
		userContext.password = password;
		userContext.loggedInUser = user;

	}
}

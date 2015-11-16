package org.courseregistration.client.auth;

import org.courseregistration.client.model.Professor;
import org.courseregistration.client.model.Student;

public class UserContext {

    private final String  username;
    private final String password;

    private final User loggedInUser;

    public boolean isStudent(){
        return  loggedInUser instanceof Student;
    }

    public boolean isProfessor() {
        return loggedInUser instanceof Professor;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    private UserContext(final String username,final String password,final User user){
        this.username =username;
        this.password = password;
        this.loggedInUser = user;
    }

    public static UserContext forUser(final String username,final String password,final User user){
        return  new UserContext(username,password,user);
    }
}

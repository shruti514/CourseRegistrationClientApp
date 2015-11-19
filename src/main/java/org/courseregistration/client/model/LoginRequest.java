package org.courseregistration.client.model;


public class LoginRequest {

    private String username;
    private String password;

    /**
     * get user name
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * set user name
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * set password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

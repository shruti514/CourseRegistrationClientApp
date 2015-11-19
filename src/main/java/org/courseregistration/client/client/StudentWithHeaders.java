package org.courseregistration.client.client;

import org.courseregistration.client.responses.StudentResponse;

import javax.ws.rs.core.Response;

public class StudentWithHeaders {

    private final String lastModified;
    private final String etagValue;
    private final StudentResponse currentStudent;

    /**
     * define student with headers
     * @param lastModified
     * @param etagValue
     * @param currentStudent
     */
    private StudentWithHeaders(String lastModified, String etagValue, StudentResponse currentStudent) {
        this.lastModified = lastModified;
        this.etagValue = etagValue;
        this.currentStudent = currentStudent;
    }

    /**
     * retrieve student with headers
     * @param response
     * @return StudentWithHeaders
     */
    public static StudentWithHeaders getStudentWithHeaders(Response response){

        String lastModified = response.getHeaderString("Last-Modified");
        String etagValue = response.getHeaderString("ETag");
        StudentResponse studentResponse = response.readEntity(StudentResponse.class);

        return  new StudentWithHeaders(lastModified,etagValue,studentResponse);
    }

    /**
     * retrieve last modified
     * @return String
     */
    public String getLastModified() {
        return lastModified;
    }
    /**
     * retrieves the Etag value
     */
    public String getEtagValue() {
        return etagValue;
    }

    /**
     * retrieves the current student response
     * @return
     */
    public StudentResponse getCurrent() {
        return currentStudent;
    }
}

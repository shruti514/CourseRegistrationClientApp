package org.courseregistration.client.client;

import org.courseregistration.client.responses.StudentResponse;

import javax.ws.rs.core.Response;

public class StudentWithHeaders {

    private final String lastModified;
    private final String etagValue;
    private final StudentResponse currentStudent;

    private StudentWithHeaders(String lastModified, String etagValue, StudentResponse currentStudent) {
        this.lastModified = lastModified;
        this.etagValue = etagValue;
        this.currentStudent = currentStudent;
    }

    public static StudentWithHeaders getStudentWithHeaders(Response response){

        String lastModified = response.getHeaderString("Last-Modified");
        String etagValue = response.getHeaderString("ETag");
        StudentResponse studentResponse = response.readEntity(StudentResponse.class);

        return  new StudentWithHeaders(lastModified,etagValue,studentResponse);
    }


    public String getLastModified() {
        return lastModified;
    }

    public String getEtagValue() {
        return etagValue;
    }

    public StudentResponse getCurrent() {
        return currentStudent;
    }
}

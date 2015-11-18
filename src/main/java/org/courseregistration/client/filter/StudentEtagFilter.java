package org.courseregistration.client.filter;


import org.courseregistration.client.client.StudentWithHeaders;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

public class StudentEtagFilter implements ClientRequestFilter {

    private final StudentWithHeaders current;

    public StudentEtagFilter(StudentWithHeaders current){
        this.current = current;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (current != null) {
            String requestContextMethod = requestContext.getMethod();

            if ("GET".equals(requestContextMethod)) {
                requestContext.getHeaders().add("If-Modified-Since", current.getLastModified());
            } else if ("PUT".equals(requestContextMethod)) {
                requestContext.getHeaders().add("If-Match", current.getEtagValue());
                requestContext.getHeaders().add("If-Unmodified-Since", current.getLastModified());
            }
        }
    }
}

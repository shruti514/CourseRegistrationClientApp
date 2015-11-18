package org.courseregistration.client.client;


import org.courseregistration.client.responses.SectionResponse;

import javax.ws.rs.core.Response;

public class SectionWithHeaders {
    private final String lastModified;
    private final String etagValue;
    private final SectionResponse currentSectionResponse;


    private SectionWithHeaders(String lastModified, String etagValue, SectionResponse sectionResponse) {
        this.lastModified = lastModified;
        this.etagValue = etagValue;
        this.currentSectionResponse = sectionResponse;
    }

    public static SectionWithHeaders getSectionWithHeaders(Response response){

        String lastModified = response.getHeaderString("Last-Modified");
        String etagValue = response.getHeaderString("ETag");
        SectionResponse sectionResponse = response.readEntity(SectionResponse.class);

        return  new SectionWithHeaders(lastModified,etagValue,sectionResponse);
    }


    public String getLastModified() {
        return lastModified;
    }

    public String getEtagValue() {
        return etagValue;
    }

    public SectionResponse getCurrent() {
        return currentSectionResponse;
    }
}

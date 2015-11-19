package org.courseregistration.client.client;


import org.courseregistration.client.responses.SectionResponse;

import javax.ws.rs.core.Response;

public class SectionWithHeaders {
    private final String lastModified;
    private final String etagValue;
    private final SectionResponse currentSectionResponse;

    /**
     * defined section headers
     * @param lastModified
     * @param etagValue
     * @param sectionResponse
     */
    private SectionWithHeaders(String lastModified, String etagValue, SectionResponse sectionResponse) {
        this.lastModified = lastModified;
        this.etagValue = etagValue;
        this.currentSectionResponse = sectionResponse;
    }

    /**
     * Retrives sections with headers
     * @param response
     * @return
     */
    public static SectionWithHeaders getSectionWithHeaders(Response response){

        String lastModified = response.getHeaderString("Last-Modified");
        String etagValue = response.getHeaderString("ETag");
        SectionResponse sectionResponse = response.readEntity(SectionResponse.class);

        return  new SectionWithHeaders(lastModified,etagValue,sectionResponse);
    }

    /**
     * Returns the last modifies date in string
     * @return
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     * Retrieve Etag value
     * @return
     */
    public String getEtagValue() {
        return etagValue;
    }

    /**
     * Retrives the current section response
     */
    public SectionResponse getCurrent() {
        return currentSectionResponse;
    }
}

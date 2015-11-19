package org.courseregistration.client.model;

public class Link {
    private String rel;
    private String href;

    /**
     * get Rel
     * @return String
     */
    public String getRel() {
        return rel;
    }

    /**
     * set Rel
     * @param rel
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * get Href
     * @return String
     */
    public String getHref() {
        return href;
    }

    /**
     * set Href
     * @param href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * to string
     * @return
     */
    @Override
    public String toString() {
        return "Link{" +
                "rel='" + rel + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}

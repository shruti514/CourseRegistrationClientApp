package org.courseregistration.client.model;

public class Error {
    private String status;
    private String code;
    private String tittle;
    private String detail;
    private String source;
    private String link;

    /**
     * get status
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * set status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get code
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * set code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * get title
     * @return String
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * set title
     * @param tittle
     */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    /**
     * get detail
     * @return String
     */
    public String getDetail() {
        return detail;
    }

    /**
     * set detail
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * get source
     * @return String
     */
    public String getSource() {
        return source;
    }

    /**
     * set source
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * get link
     * @return String
     */
    public String getLink() {
        return link;
    }

    /**
     * set link
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
    }
}

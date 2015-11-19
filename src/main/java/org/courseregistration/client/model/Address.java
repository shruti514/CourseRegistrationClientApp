package org.courseregistration.client.model;

public class Address {

    private Long id;

    private String streetName;

    private Integer aptNo;

    private Integer zipcode;

    private String city;

    private String state;

    /**
     * get Id
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * set Id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get street name
     * @return String
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * set street name
     * @param streetName
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * get Aptno
     * @return
     */
    public Integer getAptNo() {
        return aptNo;
    }

    /**
     * set Aptno
     * @param aptNo
     */
    public void setAptNo(Integer aptNo) {
        this.aptNo = aptNo;
    }

    /**
     * get zipcode
     * @return Integer
     */
    public Integer getZipcode() {
        return zipcode;
    }

    /**
     * set zipcode
     * @param zipcode
     */
    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * get city
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * set city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * get state
     * @return String
     */
    public String getState() {
        return state;
    }

    /**
     * set state
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * equals
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Address))
            return false;

        Address address = (Address) o;

        if (!getId().equals(address.getId()))
            return false;
        if (!streetName.equals(address.streetName))
            return false;
        if (aptNo != null ? !aptNo.equals(address.aptNo)
                : address.aptNo != null)
            return false;
        if (!zipcode.equals(address.zipcode))
            return false;
        if (!city.equals(address.city))
            return false;
        return state.equals(address.state);

    }

    /**
     * obtain the hash code
     * @return int
     */
    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + streetName.hashCode();
        result = 31 * result + (aptNo != null ? aptNo.hashCode() : 0);
        result = 31 * result + zipcode.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    /**
     * convert to string datatype
     * @return String
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub

        StringBuilder builder = new StringBuilder();
        builder.append("\n\t[ Address: " + streetName);
        builder.append(" #" + aptNo);
        builder.append(", " + city);
        builder.append(" " + zipcode);
        builder.append(", " + state + " ]");
        return builder.toString();

    }
}

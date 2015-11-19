package org.courseregistration.client.model;

public class Page {
    private int size;
    private int totalElements;
    private int totalPages;
    private int number;

    /**
     * get size
     * @return int
     */
    public int getSize() {
        return size;
    }

    /**
     * set size
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * get total element
     * @return int
     */
    public int getTotalElements() {
        return totalElements;
    }

    /**
     * set total element
     * @param totalElements
     */
    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    /**
     * get total pages
     * @return int
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * set total pages
     * @param totalPages
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * get number
     * @return int
     */
    public int getNumber() {
        return number;
    }

    /**
     * set number
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * to string
     * @return
     */
    @Override
    public String toString() {
        return "Page{" +
                "size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", number=" + number +
                '}';
    }
}

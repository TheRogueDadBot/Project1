package com.software1.project1.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Class represents Outsourced part.
 * Subclass of Part
 */
public class Outsourced extends Part {
    /**
     * Company name associated with Part object
     */
    private StringProperty companyName;

    // Add constructor

    /**
     * Creats new instance of Outsourced part
     * @param name Part name
     * @param price Part price
     * @param stock Part stock
     * @param min Minimum stock for part
     * @param max Maximum stock for part
     * @param companyName Name of company
     */
    public Outsourced(String name, double price, int stock, int min, int max, String companyName) {
        super(name, stock, price, min, max);
        this.companyName = new SimpleStringProperty(companyName);
    }

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, stock, price, min, max);
        this.companyName = new SimpleStringProperty(companyName);
    }


    // Add setters and getters
    /**
     * Gets company name for InHouse object
     * @return String The company name
     */
    public String getCompanyName() {
        return companyName.get();
    }
    /**
     * Sets company name for InHouse object
     * @param companyName The new company name to set
     */
    public void setCompanyName(String companyName) {

        this.companyName.set(companyName);
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }
}
package com.software1.project1.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Class represents a product composted of multiple Part objects
 */
public class Product {
    // Add fields
    /**
     * A static integer that is incremented each time a new Product instance is created, providing a unique ID for each product.
     */
    private static int nextId = 1;

    /**
     * A list of Part objects that are associated with the product.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * An integer property representing the unique ID of the product.
     */
    private final IntegerProperty id;

    /**
     * A string property representing the name of the product.
     */
    private final StringProperty name;

    /**
     * A double property representing the price of the product.
     */
    private final DoubleProperty price;

    /**
     * An integer property representing the stock or inventory level of the product.
     */
    private final IntegerProperty stock;

    /**
     * An integer property representing the minimum allowable stock of the product.
     */
    private final IntegerProperty min;

    /**
     * An integer property representing the maximum allowable stock of the product.
     */
    private final IntegerProperty max;


    /**
     * Create new instance of Product
     * @param name Product name
     * @param price Product price
     * @param stock Product stock
     * @param min Minimum stock of Product
     * @param max Maximum stock of Product
     */
    public Product(String name, double price, int stock, int min, int max) {
        this.id = new SimpleIntegerProperty(nextId++);  // Auto-increment id when a new product is created
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    //Add methods

    /**
     * Remove Part from list of associated parts of Product
     * @param part Removed Part
     */
    public void removeAssociatedPart(Part part) {
        this.associatedParts.remove(part);
    }

    /**
     * Clear all associated parts from Product
     */
    public void clearAssociatedParts() {
        associatedParts.clear();
    }

    /**
     * Add specified Part to associated parts list of Product
     * @param part Added part
     */
    public void addAssociatedPart(Part part) {
        // Adds the provided Part object to the associatedParts list
        this.associatedParts.add(part);
    }

    /**
     * Deletes specified Part from associated parts list of Product
     * @param selectedAssociatedPart Deleted part
     * @return true if part was in list and removed, otherwise false
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        // Attempts to remove the provided Part object from the associatedParts list
        // Returns true if the part was in the list and was removed, false otherwise
        return this.associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Return list of all Parts associated with Product
     * @return List of all associated Parts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        // Returns the associatedParts list
        return this.associatedParts;
    }

    /**
     * Check if Product has associated Parts
     * @return true if Product has associated parts, otherwise false
     */
    public boolean hasAssociatedParts() {
        return !(this.associatedParts.isEmpty());
    }


//Add setters and getters
    /**
     * Getters and setters for the properties
     * @return Property value
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     * Sets associated parts for Product
     * @param associatedParts List of Part objects associated with Product
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public int getId() {
        return id.get();  // Use the get method to retrieve the value
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setName(String name) {
        this.name.set(name);  // Use the set method to assign the value
    }

    public String getName() {
        return name.get();  // Use the get method to retrieve the value
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getPrice() {
        return price.get();  // Use the get method to retrieve the value
    }

    public void setPrice(double price) {
        this.price.set(price);  // Use the set method to assign the value
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public int getStock() {
        return stock.get();  // Use the get method to retrieve the value
    }

    public void setStock(int stock) {
        this.stock.set(stock);  // Use the set method to assign the value
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public int getMin() {
        return min.get();  // Use the get method to retrieve the value
    }

    public void setMin(int min) {
        this.min.set(min);  // Use the set method to assign the value
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public int getMax() {
        return max.get();  // Use the get method to retrieve the value
    }

    public void setMax(int max) {
        this.max.set(max);  // Use the set method to assign the value
    }

    public IntegerProperty maxProperty() {
        return max;
    }
}
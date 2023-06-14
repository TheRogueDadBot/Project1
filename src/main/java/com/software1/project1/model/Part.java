package com.software1.project1.model;

import javafx.beans.property.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Creates a Part entity
 * Abstract base class for different types of parts
 */
public abstract class Part {
    /**
     * Static variable to keep track of next id for generating unique product or part IDs.
     */
    private static AtomicInteger nextId = new AtomicInteger();

    /**
     * Property to hold and manage the ID of the product or part.
     */
    private final IntegerProperty id;

    /**
     * Property to hold and manage the name of the product or part.
     */
    private final StringProperty name;

    /**
     * Property to hold and manage the stock or inventory level of the product or part.
     */
    private final IntegerProperty stock;

    /**
     * Property to hold and manage the price of the product or part.
     */
    private final DoubleProperty price;

    /**
     * Property to hold and manage the minimum stock level of the product or part.
     */
    private final IntegerProperty min;

    /**
     * Property to hold and manage the maximum stock level of the product or part.
     */
    private final IntegerProperty max;


    /**
     * Constructor for creating new parts, auto-incrememnting the ID.
     *
     * @param name  The name of the part.
     * @param stock The stock of the part.
     * @param price The price of the part.
     * @param min   The minimum quantity of the part.
     * @param max   The maximum quantity of the part.
     */

    public Part(String name, int stock, double price, int min, int max) {
        this.id = new SimpleIntegerProperty(nextId.incrementAndGet());
        this.name = new SimpleStringProperty(name);
        this.stock = new SimpleIntegerProperty(stock);
        this.price = new SimpleDoubleProperty(price);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    /**
     * Constructor for swappi;ng part types, keeping the original ID
     * @param id The part ID
     * @param name The name of the part
     * @param stock The stock of the part
     * @param price The price of the part
     * @param min The minimum quantity of the part.
     * @param max The maximum quantity of the part.
     */
    public Part(int id, String name, int stock, double price, int min, int max) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.stock = new SimpleIntegerProperty(stock);
        this.price = new SimpleDoubleProperty(price);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }


    /**
     * Getters and setters for the properties
     * @return Property value
     */

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getMin() {
        return min.get();
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    // Property accessors

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public DoubleProperty priceProperty() {
        return price;
    }
}
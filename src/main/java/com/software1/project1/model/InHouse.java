package com.software1.project1.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Class represents InHouse entity
 * Subclass of Part for parts created in-house
 */
public class InHouse extends Part {
    /**
     * Machine ID associated with Part object.
     */
    private IntegerProperty machineId;

    /**
     * Constructor for InHouse class
     * @param name Name of part
     * @param price Price of part
     * @param stock Stock of part
     * @param min Minimum quantity of part
     * @param max Maximum quantity of part
     * @param machineId ID of machine
     */
    public InHouse(String name, double price, int stock, int min, int max, int machineId) {
        super(name, stock, price, min, max);
        this.machineId = new SimpleIntegerProperty(machineId);    }

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, stock, price, min, max);
        this.machineId = new SimpleIntegerProperty(machineId);
    }


    //Add setters and getters
    /**
     * Gets machine ID for InHouse object
     * @return machine ID to get
     */
    public int getMachineId() {

        return machineId.get();
    }

    /**
     * Sets machine ID for InHouse object
     * @param machineId machine ID to set
     */
    public void setMachineId(int machineId) {
        this.machineId.set(machineId);
    }

    public IntegerProperty machineIdProperty() {
        return machineId;
    }
}
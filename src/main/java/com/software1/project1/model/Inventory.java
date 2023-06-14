package com.software1.project1.model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Class manages parts and products in inventory.
 */
public class Inventory {
    //List of all parts in the inventory
    /**
     * List of all parts in inventory
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    //List of all products in the inventory
    /**
     * List of all products in inventory
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Deles part from all products in the inventory
     * @param part Deleted part
     */
    public static void deletePartFromAllProducts(Part part) {
        for (Product product : getAllProducts()) {
            if (product.getAllAssociatedParts().contains(part)) {
                product.deleteAssociatedPart(part);
            }
        }
    }

    /**
     * Adds new part to inventory
     * @param newPart Added part
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds new product to inventory
     * @param newProduct Added product
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Looks up part in inventory by ID
     * @param partId Part ID
     * @return The part, otherwise null
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * Looks up product in inventory by ID
     * @param productId Product ID
     * @return The product otherwise null
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Looks up part in inventory by name
     * @param partName Part name
     * @return List of parts that match name
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(partName)) {
                foundParts.add(part);
            }
        }
        return foundParts;
    }

    /**
     * Looks up product in inventory by name
     * @param productName Product name
     * @return List of products that match name
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    /**
     * Update part in inventory at index
     * @param index Index of updated part
     * @param selectedPart New part data
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Update product in inventory at index
     * @param index Index of updated product
     * @param newProduct New product data
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Deletes part from inventory
     * @param selectedPart Deleted part
     * @return true if part was deleted, otherwise false
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Delets product from inventory
     * @param selectedProduct Deleted product
     * @return true if product was deleted, otherwise false.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * Return all parts in inventory
     * @return List of all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Return all products in inventory
     * @return List of all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}

/**
 * FUTURE ENHANCEMENT
 * I would think it to be helpful to the company to have Inventory Alerts for when a certain part were to drop below a
 * certain threshold, it would trigger an alert to order more of the particular part.
 * The application could check either when the application was booted, up, parts were added, removed or modified, or
 * at a given time every day.
 * I would have to create a method called 'checkStockLevels' or something of the sort.  It could base the threshold
 * on the minInventory for simplicity, or could create a whole separate threshold indicator.
 */
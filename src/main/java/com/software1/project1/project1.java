/*
FUTURE ENHANCEMENT
I think adding a supplier management system would help the manufacturing organization.  The ability to keep track of which parts
are being received from which supplier would help streamline keeping the inventory in stock.  Having a 'Supplier' class with contact information, prices,
and shipping information such as delivery time and cost would help to be able to find the supplier that best fit their needs.
In the application we could attach a supplier ID to individual parts, and when stock reached a certain number, could issue
an alert to reorder the parts from the supplier.  We would also need a new 'Supplier' form.
 */
package com.software1.project1;

import com.software1.project1.model.InHouse;
import com.software1.project1.model.Outsourced;
import com.software1.project1.model.Part;
import com.software1.project1.model.Product;
import com.software1.project1.model.Inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is for the main application and contains main method.
 * Entry point for the JavaFX application
 */
public class project1 extends Application {
    /**
     * Entry point for JavaFX applications.  Is abstract and must be overridden
     * @param primaryStage Is the main stage of application.
     * @throws Exception Is if there is an error loading the FXML file
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // The FXMLLoader loads an object hierarchy from an XML document and returns it.
        // The loaded object hierarchy can be used directly. We pass "/mainscreen.fxml" to the FXMLLoader's load() method to specify the XML document we want to load.
        Parent root = FXMLLoader.load(getClass().getResource("/mainscreen.fxml"));

        // Set the title for the application window (stage).
        primaryStage.setTitle("My Project 1 Application");

        // Create a new Scene with root as its root node. The scene is the content inside the stage (window).
        primaryStage.setScene(new Scene(root));

        // Display the primary stage.
        primaryStage.show();
    }

    /**
     * Entry point for Java applications.
     * @param args Are command line arguements passed to application.
     */
    public static void main(String[] args) {

        // Create instances of InHouse and Outsourced Parts with unique details.
        Part brakes = new InHouse("Brakes", 100.00, 10, 5, 15, 1234);
        Part tire = new Outsourced("Tire", 200.00, 20, 10, 30, "Company1");
        Part rim = new Outsourced("Rim", 500.00, 30, 15, 200, "Company2");
        Part seat = new InHouse("Seat", 300.00, 35, 25, 200, 1235);

        // Add the created Parts into the Inventory.
        Inventory.addPart(brakes);
        Inventory.addPart(tire);
        Inventory.addPart(rim);
        Inventory.addPart(seat);

        // Create instances of Product with unique details.
        Product mountainBike = new Product("Mountain Bike", 500.00, 5, 1, 10);
        Product roadBike = new Product("Road Bike", 400.00, 6, 1, 10);
        Product tricycle = new Product("Tricycle", 300.00, 7, 1, 10);
        Product unicycle = new Product("Unicycle", 200.00, 8, 1, 10);

        // Add associated Parts to each Product.
        mountainBike.addAssociatedPart(brakes);
        mountainBike.addAssociatedPart(tire);
        roadBike.addAssociatedPart(brakes);
        roadBike.addAssociatedPart(rim);
        tricycle.addAssociatedPart(brakes);
        tricycle.addAssociatedPart(seat);
        unicycle.addAssociatedPart(tire);
        unicycle.addAssociatedPart(seat);

        // Add the created Products into the Inventory.
        Inventory.addProduct(mountainBike);
        Inventory.addProduct(roadBike);
        Inventory.addProduct(tricycle);
        Inventory.addProduct(unicycle);

        // Launches the JavaFX application.
        launch(args);
    }
}

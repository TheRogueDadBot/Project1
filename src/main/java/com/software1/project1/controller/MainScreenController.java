package com.software1.project1.controller;

import com.software1.project1.model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for main screen of application.  Handles all user actions performed on main screen.
 */
public class MainScreenController implements Initializable {

    // Add UI Elements from FXML file
    /**
     * Button for adding a new Part.
     */
    @FXML
    private Button addPartBtn;

    /**
     * Button for deleting a Part.
     */
    @FXML
    private Button deletePartBtn;

    /**
     * Button for modifying a Part.
     */
    @FXML
    private Button modifyPartBtn;

    /**
     * Button for exiting the application.
     */
    @FXML
    private Button exitBtn;

    /**
     * Button for adding a new Product.
     */
    @FXML
    private Button addProductBtn;

    /**
     * Button for deleting a Product.
     */
    @FXML
    private Button deleteProductBtn;

    /**
     * Button for modifying a Product.
     */
    @FXML
    private Button modifyProductBtn;

    /**
     * TableView for displaying Parts.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * TableColumn for displaying Part ID.
     */
    @FXML
    private TableColumn<Part, Integer> partIdCol;

    /**
     * TableColumn for displaying Part Inventory Level.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryLevelCol;

    /**
     * TableColumn for displaying Part Name.
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * TableColumn for displaying Part Price.
     */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     * TableView for displaying Products.
     */
    @FXML
    private TableView<Product> productTableView;

    /**
     * TableColumn for displaying Product ID.
     */
    @FXML
    private TableColumn<Product, Integer> productIdCol;

    /**
     * TableColumn for displaying Product Inventory Level.
     */
    @FXML
    private TableColumn<Product, Integer> productInventoryLevelCol;

    /**
     * TableColumn for displaying Product Name.
     */
    @FXML
    private TableColumn<Product, String> productNameCol;

    /**
     * TableColumn for displaying Product Price.
     */
    @FXML
    private TableColumn<Product, Double> productPriceCol;

    /**
     * Label for the Inventory Management System.
     */
    @FXML
    private Label inventoryManagementSystemBtn;

    /**
     * Label for the Parts section.
     */
    @FXML
    private Label partsLbl;

    /**
     * Label for the Products section.
     */
    @FXML
    private Label productLbl;

    /**
     * TextField for searching Parts.
     */
    @FXML
    private TextField partSearchTxt;

    /**
     * TextField for searching Products.
     */
    @FXML
    private TextField productSearchTxt;

    /**
     * Stage for the current window.
     */
    private Stage stage;

    /**
     * Parent node for the scene graph.
     */
    private Parent scene;

    // Add method called when the Add Part button is clicked. It opens a new window for adding a part.
    /**
     * Add method called when the Add Part button is clicked. It opens a new window for adding a part.
     * @param event Is the Action Event that initiates the method.
     * @throws IOException If there is an error loading window
     */
    @FXML
    private void onActionAddPart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addpart.fxml"));
        Parent root = loader.load();

        // Get the controller and pass the ObservableList to it
        AddPartController controller = loader.getController();
        controller.setData(Inventory.getAllParts());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        System.out.println("Add Part button clicked!");
    }

    //Method called when Delete button is clicked.
    /**
     * Method called when Delete button is clicked.  Removes part from part tableview.  Runs checks.
     * Removes part from associated parts list
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    private void onActionDeletePart(ActionEvent event) {
        System.out.println("Delete Part button clicked!");
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        // Check if a part is selected
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();
        } else {
            // Confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part");
            alert.setHeaderText("Delete Part");
            alert.setContentText("Are you sure you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                // If confirmed, delete the part from partsTableView
                Inventory.deletePart(selectedPart);
                // Also, remove the part from all products' associated parts list
                for(Product product : Inventory.getAllProducts()) {
                    product.removeAssociatedPart(selectedPart);
                }
                partTableView.setItems(Inventory.getAllParts());
                System.out.println("Part deleted!");
            } else {
                System.out.println("Delete operation cancelled.");
            }
        }
    }

    // Method called when the Exit button is clicked. It closes the application.

    /**
     * Method called when the Exit button is clicked. It closes the application.
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    private void onActionExit(ActionEvent event) {
        System.out.println("Exit button clicked!");
        Platform.exit();
    }

    // Method called when the Modify Part button is clicked. It opens a new window for modifying a part.
    /**
     * Method called when the Modify Part button is clicked.  It opens a new window for modifying a part.
     * @param event Is the Action Event that initiates the method.
     * @throws IOException If there is error loading window
     */
    @FXML
    private void onActionModifyPart(ActionEvent event) throws IOException {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        // Check if a part is selected
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part to modify.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifypart.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the part to it
            ModifyPartController controller = loader.getController();
            controller.setPart(selectedPart);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("Modify Part button clicked!");
        }
    }

    // Method called when the Add Product button is clicked. It opens a new window for adding a product.
    /**
     * Method called when the Add Product button is clicked.  It opens a new window for adding a product.
     * @param event Is the Action Event that initiates the method.
     * @throws IOException If there is an error loading new window.
     */
    @FXML
    private void onActionAddProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addproduct.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        System.out.println("Add Product button clicked!");
    }

    // Method called when the Modify Product button is clicked. It opens a new window for modifying a product.

    /**
     * Method called when the Modify Product button is clicked.  It opens a new window for modifying a product.
     * @param event Is the Action Event that initiates the method.
     * @throws IOException If there is an error loading new window.
     */
    @FXML
    private void onActionModifyProduct(ActionEvent event) throws IOException {
        // Get the currently selected product from the table
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            // No product was selected; show an error message or simply return
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifyproduct.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        // Get the controller of the ModifyProduct view
        ModifyProductController controller = loader.getController();

        // Pass the selected product to the controller
        controller.setProduct(selectedProduct);

        // Show the ModifyProduct view
        stage.show();
        System.out.println("Modify Product button clicked!");
    }


    // Method called when the Delete Product button is clicked.

    /**
     * Method called when the Delete Product button is clicked.  Deletes selected product.  Runs checks.
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    private void onActionDeleteProduct(ActionEvent event) {
        System.out.println("Delete Product button clicked!");
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        // Check if a product is selected
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product to delete.");
            alert.showAndWait();
        } else {
            if (!selectedProduct.hasAssociatedParts()) {
                // Confirm deletion if there are no associated parts
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Product");
                alert.setHeaderText("Delete Product");
                alert.setContentText("Are you sure you want to delete this product?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    Inventory.deleteProduct(selectedProduct);
                    productTableView.setItems(Inventory.getAllProducts());
                    System.out.println("Product deleted!");
                } else {
                    System.out.println("Delete operation cancelled.");
                }
            } else {
                // Show error if there are associated parts
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cannot Delete Product");
                alert.setHeaderText("Cannot Delete Product");
                alert.setContentText("Cannot delete a product that has a part associated with it.");
                alert.showAndWait();
            }
        }
    }

    // Add Initialization method
    /**
     * Initializes the controller after root element is processed.
     * Sets up TableView for parts and products.  Add search functionality.
     * @param url Is location for relative paths for root objects
     * @param resourceBundle Are the resources to localize root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Parts: " + Inventory.getAllParts());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainscreen.fxml"));
        loader.setController(this);

        // Add cell value factories for part table columns
        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partPriceCol.setCellFactory(column -> {
            return new TableCell<Part, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(String.format("%.2f", item));
                    }
                }
            };
        });

        // Populate the product table view
        productTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productPriceCol.setCellFactory(column -> {
            return new TableCell<Product, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(String.format("%.2f", item));
                    }
                }
            };
        });

        // Add search bar for parts
        partSearchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.trim().toLowerCase();
            ObservableList<Part> filteredList = FXCollections.observableArrayList();

            for (Part part : Inventory.getAllParts()) {
                if (part.getName().toLowerCase().contains(searchText) ||
                        String.valueOf(part.getId()).equals(searchText)) {
                    filteredList.add(part);
                }
            }

            if (filteredList.isEmpty()) {
                // Display an error message if no parts match the search criteria
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Parts Found");
                alert.setHeaderText("No Parts Found");
                alert.setContentText("No parts found that match your search.");
                alert.showAndWait();
            } else {
                // Update the partTableView with the filtered list
                partTableView.setItems(filteredList);
            }
        });

        // Add search bar for products
        productSearchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.trim().toLowerCase();
            ObservableList<Product> filteredList = FXCollections.observableArrayList();

            for (Product product : Inventory.getAllProducts()) {
                if (product.getName().toLowerCase().contains(searchText) ||
                        String.valueOf(product.getId()).equals(searchText)) {
                    filteredList.add(product);
                }
            }

            if (filteredList.isEmpty()) {
                // Display an error message if no products match the search criteria
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Products Found");
                alert.setHeaderText("No Products Found");
                alert.setContentText("No products found that match your search.");
                alert.showAndWait();
            } else {
                // Update the productTableView with the filtered list
                productTableView.setItems(filteredList);
            }
        });
    }
}
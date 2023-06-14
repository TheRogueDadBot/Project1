package com.software1.project1.controller;

import com.software1.project1.model.Inventory;
import com.software1.project1.model.Part;
import com.software1.project1.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Controller for the Modify Product window
 * Modifies and existing Product and its associated parts
 */
public class ModifyProductController implements Initializable {

    /**
     * Label for adding product.
     */
    @FXML private Label addProductLbl;

    /**
     * Label for product ID.
     */
    @FXML private Label productIdLbl;

    /**
     * Label for product name.
     */
    @FXML private Label productNameLbl;

    /**
     * Label for product price.
     */
    @FXML private Label productPriceLbl;

    /**
     * Label for maximum number of products.
     */
    @FXML private Label productMaxLbl;

    /**
     * Label for minimum number of products.
     */
    @FXML private Label productMinLbl;

    /**
     * TextField for product ID.
     */
    @FXML private TextField productIdTxt;

    /**
     * TextField for product name.
     */
    @FXML private TextField productNameTxt;

    /**
     * TextField for product inventory level.
     */
    @FXML private TextField productInvTxt;

    /**
     * TextField for product price.
     */
    @FXML private TextField productPriceTxt;

    /**
     * TextField for maximum number of products.
     */
    @FXML private TextField productMaxTxt;

    /**
     * TextField for minimum number of products.
     */
    @FXML private TextField productMinTxt;

    /**
     * TextField for searching parts.
     */
    @FXML private TextField searchPartTxt;

    /**
     * TableView for displaying list of parts.
     */
    @FXML private TableView<Part> partTableView;

    /**
     * TableView for displaying list of associated parts of the product.
     */
    @FXML private TableView<Part> associatedPartTableView;

    /**
     * TableColumn for displaying part ID.
     */
    @FXML private TableColumn<Part, Integer> partIdCol;

    /**
     * TableColumn for displaying part inventory level.
     */
    @FXML private TableColumn<Part, Integer> partInventoryCol;

    /**
     * TableColumn for displaying part name.
     */
    @FXML private TableColumn<Part, String> partNameCol;

    /**
     * TableColumn for displaying part price.
     */
    @FXML private TableColumn<Part, Double> partPriceCol;

    /**
     * TableColumn for displaying associated part ID.
     */
    @FXML private TableColumn<Part, Integer> associatedPartIdCol;

    /**
     * TableColumn for displaying associated part inventory level.
     */
    @FXML private TableColumn<Part, Integer> associatedPartInventoryCol;

    /**
     * TableColumn for displaying associated part name.
     */
    @FXML private TableColumn<Part, String> associatedPartNameCol;

    /**
     * TableColumn for displaying associated part price.
     */
    @FXML private TableColumn<Part, Double> associatedPartPriceCol;

    /**
     * Button for adding product.
     */
    @FXML private Button addProductBtn;

    /**
     * Button for removing associated part.
     */
    @FXML private Button removeAssociatedPartBtn;

    /**
     * Button for saving the product.
     */
    @FXML private Button saveBtn;

    /**
     * Button for canceling the current action.
     */
    @FXML private Button cancelBtn;

    /**
     * ObservableList for holding the associated parts of the product.
     */
    private final ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    /**
     * The currently selected Product.
     */
    private Product currentProduct;

    //Add methods
    //Method for when Add Button is clicked.  Adds selected part from partTableView to associatedPartsList.

    /**
     * Method for when Add Button is clicked.  Adds selected part from partTableView to associatedPartsList.
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    private void onActionAddProduct(ActionEvent event) {
        // Get the selected part from the partTableView
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        // Check if a part is selected
        if (selectedPart != null) {
            // Add the selected part to the associated parts list
            associatedPartsList.add(selectedPart);
        } else {
            // Show an alert if no part is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Part Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part to add.");
            alert.showAndWait();
        }
    }

    //Method for when Remove Associated Part Button is clicked.  Removes part from associatedPartTableView and associatedPartsList

    /**
     * Method for when Remove Associated Part Button is clicked.  Removes part from associatedPartTableView and associatedPartsList
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    private void onActionRemoveAssociatedPart(ActionEvent event) {
        // Get the selected part
        Part selectedPart = associatedPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {
            // Confirm removal
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove Part");
            alert.setHeaderText("Remove Part");
            alert.setContentText("Are you sure you want to remove this associated part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                // Remove the selected part from the associated parts list
                associatedPartsList.remove(selectedPart);

                // Refresh the associated parts table view
                associatedPartTableView.setItems(associatedPartsList);
                System.out.println("Part removed from associated parts!");
            } else {
                System.out.println("Remove operation cancelled.");
            }
        } else {
            // Display an error message if no part is selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Part Selected");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part to remove.");
            alert.showAndWait();
        }
    }

    //Method for when Cancel button is clicked.  Closes the window.

    /**
     * Method for when Cancel button is clicked.  Closes the window.
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    private void onActionCancel(ActionEvent event) {
        // Get the current stage from the source of the ActionEvent
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current stage
        stage.close();
    }

    //Method for when Save Button is clicked.  Validates input fields, updates current product with new values.
    //Updates associated parts, removes original product from Inventory then adds updated product.  Closes the window.
    /**
     * Method for when Save Button is clicked.  Validates input fields, updates current product with new values.
     * Updates associated parts, removes original product from Inventory then adds updated product.  Closes the window.
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    private void onActionSaveProduct(ActionEvent event) {
        String errorMessage = "";

        String name = productNameTxt.getText();
        if (name == null || name.length() == 0) {
            errorMessage += "No valid product name!\n";
        }

        int stock = -1;
        try {
            stock = Integer.parseInt(productInvTxt.getText());
        } catch (NumberFormatException e) {
            errorMessage += "No valid inventory count (must be an integer)!\n";
        }

        double price = -1.0;
        try {
            price = Double.parseDouble(productPriceTxt.getText());
        } catch (NumberFormatException e) {
            errorMessage += "No valid product price (must be a number)!\n";
        }

        int min = -1, max = -1;
        try {
            min = Integer.parseInt(productMinTxt.getText());
            max = Integer.parseInt(productMaxTxt.getText());
        } catch (NumberFormatException e) {
            errorMessage += "No valid Min or Max (must be an integer)!\n";
        }

        if (!validateInventory(min, max, stock)) {
            errorMessage += "Inventory must be between Min and Max values.\n";
        }

        if (errorMessage.length() > 0) {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        } else {
            // Update the current product
            currentProduct.setName(name);
            currentProduct.setPrice(price);
            currentProduct.setStock(stock);
            currentProduct.setMin(min);
            currentProduct.setMax(max);

            // Clear current associated parts and add the new ones
            currentProduct.clearAssociatedParts();
            for (Part part : associatedPartsList) {
                currentProduct.addAssociatedPart(part);
            }

            // Remove the original product from the Inventory
            Inventory.deleteProduct(currentProduct);

            // Add the updated currentProduct back to the Inventory
            Inventory.addProduct(currentProduct);

            // Get the current stage from the source of the ActionEvent
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Close the current stage
            stage.close();
        }
    }

    //Method for validating inventory values

    /**
     * Validates inventory constraints.
     * @param min Is the minimum inventory
     * @param max Is the maximum inventory
     * @param inv Is the inventory to be checked
     * @return Indicates whether the inventory is valid.
     */
    private boolean validateInventory(int min, int max, int inv) {
        if (min >= max) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Min value cannot be greater than or equal to Max value.");
            alert.showAndWait();
            return false;
        }
        if (!(min <= inv && inv <= max)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Inventory should be between Min and Max values.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    //Method of setting up product

    /**
     * Method for setting up product to be modified
     * @param product Is the product to be modified.
     */
    public void setProduct(Product product) {
        // Save a reference to the product we're modifying
        this.currentProduct = product;

        // Populate the form with the current product's data
        productIdTxt.setText(Integer.toString(product.getId()));
        productNameTxt.setText(product.getName());
        productInvTxt.setText(Integer.toString(product.getStock()));
        productPriceTxt.setText(Double.toString(product.getPrice()));
        productMinTxt.setText(Integer.toString(product.getMin()));
        productMaxTxt.setText(Integer.toString(product.getMax()));

        // Populate associated parts list and table if any
        associatedPartsList.clear();
        associatedPartsList.addAll(product.getAssociatedParts());
        associatedPartTableView.setItems(associatedPartsList);

        // After modifying the product, re-sort the items in the product list
        FXCollections.sort(Inventory.getAllProducts(), Comparator.comparingInt(Product::getId));
    }

    //Initialization method
    /**
     * Initialize the controller class.
     * @param url Is location for relative paths for root objects
     * @param resourceBundle Are the resources to localize root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainscreen.fxml"));
        loader.setController(this);

        productIdTxt.setEditable(false);
        productIdTxt.setDisable(true);

        // Populate the associated parts table view
        associatedPartTableView.setItems(associatedPartsList);
        associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartPriceCol.setCellFactory(column -> {
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

        // Add part table view with inventory data
        // Add cell value factories for table columns
        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        /*RUNTIME ERROR.  I was running into the issue of the price 'Double' value simplifying to just one decimal place instead of two.
        I had to fix this by overriding the 'updateItem' method within the 'TableCell' with 'String.format(%.2f, item) to get price looking right.
        I also had to do this for the productPriceCol
         */

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

        //Add search bar
        searchPartTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<Part> filteredList = FXCollections.observableArrayList();

            for (Part part : Inventory.getAllParts()) {
                if (part.getName().toLowerCase().startsWith(newValue.toLowerCase())) {
                    filteredList.add(part);
                } else {
                    try {
                        int searchId = Integer.parseInt(newValue);
                        if (part.getId() == searchId) {
                            filteredList.add(part);
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }

            if (filteredList.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Parts Found");
                alert.setHeaderText("No Parts Found");
                alert.setContentText("No parts found that match your search.");
                alert.showAndWait();
            } else {
                partTableView.setItems(filteredList);
            }
        });

    }

    // Add observable lists for holding parts and products.
    private static ObservableList<Part> partsList = FXCollections.observableArrayList();
    private static ObservableList<Product> productsList = FXCollections.observableArrayList();
}
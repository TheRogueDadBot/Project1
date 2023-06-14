package com.software1.project1.controller;

import com.software1.project1.model.Inventory;
import com.software1.project1.model.Part;
import com.software1.project1.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the AddProduct view.  Handles user interactions.
 */
public class AddProductController implements Initializable {
        //Define labels, text fields, buttons and table views
        /**
         * Elements of GUI interface
         */
        /**
         * Label for the AddProduct view.
         */
        @FXML private Label addProductLbl;

        /**
         * Label for the Product ID field.
         */
        @FXML private Label productIdLbl;

        /**
         * Label for the Product Name field.
         */
        @FXML private Label productNameLbl;

        /**
         * Label for the Product Price field.
         */
        @FXML private Label productPriceLbl;

        /**
         * Label for the Product Max field.
         */
        @FXML private Label productMaxLbl;

        /**
         * Label for the Product Min field.
         */
        @FXML private Label productMinLbl;

        /**
         * Text field for the Product ID input.
         */
        @FXML private TextField productIdTxt;

        /**
         * Text field for the Product Name input.
         */
        @FXML private TextField productNameId;

        /**
         * Text field for the Product Inventory input.
         */
        @FXML private TextField productInvTxt;

        /**
         * Text field for the Product Price input.
         */
        @FXML private TextField productPriceTxt;

        /**
         * Text field for the Product Max input.
         */
        @FXML private TextField productMaxTxt;

        /**
         * Text field for the Product Min input.
         */
        @FXML private TextField productMinTxt;

        /**
         * Text field for the Search Part input.
         */
        @FXML private TextField searchPartTxt;

        /**
         * TableView for displaying the Part details.
         */
        @FXML private TableView<Part> partTableView;

        /**
         * TableView for displaying the Associated Part details.
         */
        @FXML private TableView<Part> associatedPartTableView;

        /**
         * TableColumn for displaying the Part ID.
         */
        @FXML private TableColumn<Part, Integer> partIdCol;

        /**
         * TableColumn for displaying the Associated Part ID.
         */
        @FXML private TableColumn<Part, Integer> associatedPartIdCol;

        /**
         * TableColumn for displaying the Part Inventory.
         */
        @FXML private TableColumn<Part, Integer> partInventoryCol;

        /**
         * TableColumn for displaying the Associated Part Inventory.
         */
        @FXML private TableColumn<Part, Integer> associatedPartInventoryCol;

        /**
         * TableColumn for displaying the Part Name.
         */
        @FXML private TableColumn<Part, String> partNameCol;

        /**
         * TableColumn for displaying the Associated Part Name.
         */
        @FXML private TableColumn<Part, String> associatedPartNameCol;

        /**
         * TableColumn for displaying the Part Price.
         */
        @FXML private TableColumn<Part, Double> partPriceCol;

        /**
         * TableColumn for displaying the Associated Part Price.
         */
        @FXML private TableColumn<Part, Double> associatedPartPriceCol;

        /**
         * Button for adding a Product.
         */
        @FXML private Button addProductBtn;

        /**
         * Button for removing an Associated Part.
         */
        @FXML private Button removeAssociatedPartBtn;

        /**
         * Button for saving the changes.
         */
        @FXML private Button saveBtn;

        /**
         * Button for cancelling the changes.
         */
        @FXML private Button cancelBtn;

        /**
         * List for holding the associated parts of a product.
         */
        private final ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

        /**
         * Current product being added or modified.
         */
        private Product currentProduct;

        /**
         * Auto incrementing product ID.
         */
        private static int autoProductId = 0;



        //Add methods
        //Method to add a selected part to the associated parts list
        /**
         *Method to add a selected part to the associated parts list.
         * @param event  Is the Action Event that initiates the method.
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

        //Method to remove a selected associated part
        /**
         * Method to remove a selected associated part
         * @param event Is the Action Event that initiates the method.
         */
        @FXML
        private void onActionRemoveAssociatedPart(ActionEvent event) {
                // Get the selected part
                Part selectedPart = associatedPartTableView.getSelectionModel().getSelectedItem();

                if (selectedPart != null) {
                        // Remove the selected part from the associated parts list
                        associatedPartsList.remove(selectedPart);

                        // Refresh the associated parts table view
                        associatedPartTableView.setItems(associatedPartsList);
                } else {
                        // Display an error message if no part is selected
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("No Part Selected");
                        alert.setHeaderText("No Part Selected");
                        alert.setContentText("Please select a part to remove.");
                        alert.showAndWait();
                }
        }

        //Method to cancel the addition of product and close current stage
        /**
         * Method to cnacel the addition of product and close current stage.
         * @param event Is the Action Event that initiates the method.
         */
        @FXML
        private void onActionCancel(ActionEvent event) {
                // Get the current stage from the source of the ActionEvent
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Close the current stage
                stage.close();
        }

        //Method to save product
        /**
         * Method to save product.
         * @param event Is the Action Event that initiates the method.
         */
        @FXML
        private void onActionSaveProduct(ActionEvent event) {
                String errorMessage = "";

                String name = productNameId.getText();
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
                        // Generate new id
                        int id = autoProductId++;

                        // Create a new product
                        Product newProduct = new Product(name, price, stock, min, max);

                        // Add associated parts to the new product
                        for (Part part : associatedPartsList) {
                                newProduct.addAssociatedPart(part);
                        }

                        // Add the new product to the inventory
                        Inventory.addProduct(newProduct);

                        // Close the window
                        onActionCancel(event);

                        // Get the current stage from the source of the ActionEvent
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        // Close the current stage
                        stage.close();
                }
        }

        //Method to validate the inventory
        /**
         * Validates the inventory by running checks. to see if inventory is between min and max
         * If valid, returns true
         * @param min Is the minimum inventory
         * @param max Is the maximum inventory
         * @param inv Is the inventory to be checked
         * @return Indicates whether the inventory is valid.
         */
        private boolean validateInventory(int min, int max, int inv) {
                //Checks if the inventory count is valid
                //If not, an Alert is shown to user
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

        // Method to set the current product and populate UI fields
        /**
         * Method to set the current product and populate UI fields
         * @param product Is the product to set as current product.
         */
        public void setCurrentProduct(Product product) {
                this.currentProduct = product;

                // Once the current product is set, populate your UI fields based on this product's information
                productIdTxt.setText(String.valueOf(product.getId()));
                productNameId.setText(product.getName());
                productInvTxt.setText(String.valueOf(product.getStock()));
                productPriceTxt.setText(String.valueOf(product.getPrice()));
                productMaxTxt.setText(String.valueOf(product.getMax()));
                productMinTxt.setText(String.valueOf(product.getMin()));

                // Also set the associated parts to the TableView
                associatedPartTableView.setItems(currentProduct.getAssociatedParts());
        }

        /**
     * @param url
     * @param resourceBundle
     */

    //Initialize the controller class

        /**
         * Initialize the controller class.  Populates table views and sets search bar functionality.
         * @param url Is location for relative paths for root objects
         * @param resourceBundle Are the resources to localize root object.
         */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            //Loads the main screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainscreen.fxml"));
            loader.setController(this);

            //Disables product ID text field and sets default text
            productIdTxt.setDisable(true);
            productIdTxt.setEditable(false);
            productIdTxt.setText("Auto Gen - Disabled");

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

        /**RUNTIME ERROR.  I was running into the issue of the price 'Double' value simplifying to just one decimal place instead of two.
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
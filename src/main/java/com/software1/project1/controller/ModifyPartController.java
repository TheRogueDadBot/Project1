package com.software1.project1.controller;

import com.software1.project1.model.InHouse;
import com.software1.project1.model.Inventory;
import com.software1.project1.model.Outsourced;
import com.software1.project1.model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Modify Part window
 * Modifies existing Part, either InHouse or Outsourced
 */
public class ModifyPartController implements Initializable {

    /**
     * The currently selected Part.
     */
    private Part currentPart;

    /**
     * Button to cancel the current action.
     */
    @FXML private Button cancelBtn;

    /**
     * Button to save the current Part.
     */
    @FXML private Button saveBtn;

    /**
     * RadioButton to indicate whether the current Part is an In-house Part.
     */
    @FXML private RadioButton inHouseRBtn;

    /**
     * RadioButton to indicate whether the current Part is an Outsourced Part.
     */
    @FXML private RadioButton outsourcedRBtn;

    /**
     * Label for inventory level.
     */
    @FXML private Label invLbl;

    /**
     * Label for machine ID or company name depending on part type.
     */
    @FXML private Label machineIdLbl;

    /**
     * Label for maximum number of parts.
     */
    @FXML private Label maxLbl;

    /**
     * Label for minimum number of parts.
     */
    @FXML private Label minLbl;

    /**
     * Label for the Modify Part view.
     */
    @FXML private Label modifyPartLbl;

    /**
     * Label for name of the part.
     */
    @FXML private Label nameLbl;

    /**
     * Label for part ID.
     */
    @FXML private Label partIdLdl;

    /**
     * Label for price of the part.
     */
    @FXML private Label priceLbl;

    /**
     * TextField for inventory level.
     */
    @FXML private TextField invTxt;

    /**
     * TextField for machine ID or company name depending on part type.
     */
    @FXML private TextField machineIdTxt;

    /**
     * TextField for maximum number of parts.
     */
    @FXML private TextField maxTxt;

    /**
     * TextField for minimum number of parts.
     */
    @FXML private TextField minTxt;

    /**
     * TextField for name of the part.
     */
    @FXML private TextField nameTxt;

    /**
     * TextField for part ID.
     */
    @FXML private TextField partIdTxt;

    /**
     * TextField for price of the part.
     */
    @FXML private TextField priceTxt;

    /**
     * TextField for company name (only applicable for Outsourced Parts).
     */
    @FXML private TextField companyNameTxt;

    //Add methods
    //Method for when Cancel button is clicked.  Closes the current window

    /**
     * Method for when Cancel button is clicked.  Closes the current window
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    void onActionCancel(ActionEvent event) {
        System.out.println("Cancel button clicked!");
        // Get the current stage from the source of the ActionEvent
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current stage
        stage.close();
    }

    //Method for when In-House Radio Button is clicked.  Displays machineIdTxt, changes machineIdLbl and hides companyNameTxt.

    /**
     * Method for when In-House Radio Button is clicked.  Displays machineIdIxt, changes machineIdLbl and hides companyNameTxt.
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    void onActionInHouse(ActionEvent event) {
        System.out.println("In-House radio button clicked!");
        machineIdLbl.setText("Machine ID");
        machineIdTxt.setVisible(true);
        companyNameTxt.setVisible(false);
    }

    //Method for when Outsourced Radio Button is clicked.  Hides machineIdTxt, changes machineIdLbl and displays companyNameTxt.

    /**
     * Method for when Outsourced Radio Button is clicked.  Hides machineIdTxt, changes machineIdLbl and displays companyNameTxt.
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    void onActionOutsourced(ActionEvent event) {
        System.out.println("Outsourced radio button clicked!");
        machineIdLbl.setText("Company Name");
        machineIdTxt.setVisible(false);
        companyNameTxt.setVisible(true);
    }

    //Method for when Save Button is clicked.  Validate each field.  Updates current Part and closes the window
    /**
     * Method for when Save Button is clicked.  Validate each field.  Updates current Part and closes the window
     * @param event Is the Action Event that initiates the method.
     */
    @FXML
    void onActionSavePart(ActionEvent event) {
        System.out.println("Save Part button clicked!");

        // Get the current part's ID
        int partId = currentPart.getId();

        String errorMessage = "";

        String name = nameTxt.getText();
        if (name == null || name.isEmpty()) {
            errorMessage += "No valid product name!\n";
        }

        int stock = -1;
        try {
            stock = Integer.parseInt(invTxt.getText());
        } catch (NumberFormatException e) {
            errorMessage += "No valid inventory count (must be an integer)!\n";
        }

        double price = -1.0;
        try {
            price = Double.parseDouble(priceTxt.getText());
        } catch (NumberFormatException e) {
            errorMessage += "No valid product price (must be a number)!\n";
        }

        int min = -1, max = -1;
        try {
            min = Integer.parseInt(minTxt.getText());
            max = Integer.parseInt(maxTxt.getText());
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
            // Fetch the index of currentPart from the inventory before updating the currentPart
            int index = Inventory.getAllParts().indexOf(currentPart);

            // Update the fields of currentPart
            currentPart.setName(name);
            currentPart.setStock(stock);
            currentPart.setPrice(price);
            currentPart.setMin(min);
            currentPart.setMax(max);

            if (currentPart instanceof InHouse) {
                if (outsourcedRBtn.isSelected()) {
                    // Switching from InHouse to Outsourced
                    String companyName = companyNameTxt.getText();
                    Outsourced outsourcedPart = new Outsourced(currentPart.getId(), name, price, stock, min, max, companyName);
                    currentPart = outsourcedPart;
                    // Update the part in the inventory
                    Inventory.updatePart(index, outsourcedPart);
                } else {
                    // InHouse part, update the machine ID
                    int machineId = Integer.parseInt(machineIdTxt.getText());
                    ((InHouse) currentPart).setMachineId(machineId);
                }
            } else if (currentPart instanceof Outsourced) {
                if (inHouseRBtn.isSelected()) {
                    // Switching from Outsourced to InHouse
                    int machineId = Integer.parseInt(machineIdTxt.getText());
                    InHouse inHousePart = new InHouse(currentPart.getId(), name, price, stock, min, max, machineId);
                    currentPart = inHousePart;
                    // Update the part in the inventory
                    Inventory.updatePart(index, inHousePart);
                } else {
                    // Outsourced part, update the company name
                    String companyName = companyNameTxt.getText();
                    ((Outsourced) currentPart).setCompanyName(companyName);
                }
            }

            // Get the current stage from the source of the ActionEvent
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Close the current stage
            stage.close();
        }
    }


    //Method to check inventory constraints

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

    /**
     * @param url
     * @param resourceBundle
     */

    //Initialization method
    /**
     * Initialize the controller class.
     * @param url Is location for relative paths for root objects
     * @param resourceBundle Are the resources to localize root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        companyNameTxt.setVisible(false);
        machineIdLbl.setText("Machine Id");
        machineIdTxt.setVisible(true);
        //Disable ID textfield
        partIdTxt.setDisable(true);
        partIdTxt.setEditable(false);
    }

    //Method to set the part that will be modified

    /**
     * Method sets the part to be modified.
     * @param part Is the part to be modified
     */
    public void setPart(Part part) {
        this.currentPart = part;

        partIdTxt.setText(Integer.toString(part.getId()));
        nameTxt.setText(part.getName());
        invTxt.setText(Integer.toString(part.getStock()));
        priceTxt.setText(Double.toString(part.getPrice()));
        minTxt.setText(Integer.toString(part.getMin()));
        maxTxt.setText(Integer.toString(part.getMax()));

        if (part instanceof InHouse) {
            inHouseRBtn.setSelected(true);
            machineIdTxt.setText(Integer.toString(((InHouse) part).getMachineId()));
            companyNameTxt.setText("");
            companyNameTxt.setVisible(false);
        } else if (part instanceof Outsourced) {
            outsourcedRBtn.setSelected(true);
            machineIdTxt.setText("");
            companyNameTxt.setText(((Outsourced) part).getCompanyName());
            companyNameTxt.setVisible(true);
        }
    }


}
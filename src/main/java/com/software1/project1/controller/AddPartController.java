package com.software1.project1.controller;

import com.software1.project1.model.InHouse;
import com.software1.project1.model.Outsourced;
import com.software1.project1.model.Part;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Controller class responsible for managing operations of the Add Part screen.
 */
public class AddPartController {
    //Define labels, text fields, buttons and a toggle group.
    /**
     * Label for the AddPart view.
     */
    @FXML
    private Label addPartLbl;

    /**
     * Label for the Part ID field.
     */
    private Label partIdLdl;

    /**
     * Label for the Part Name field.
     */
    @FXML
    private Label nameLbl;

    /**
     * Label for the Part Inventory field.
     */
    @FXML
    private Label invLbl;

    /**
     * Label for the Part Price field.
     */
    @FXML
    private Label priceLbl;

    /**
     * Label for the Part Maximum field.
     */
    @FXML
    private Label maxLbl;

    /**
     * Label for the Machine ID field.
     */
    @FXML
    private Label machineIdLbl;

    /**
     * Label for the Part Minimum field.
     */
    @FXML
    private Label minLbl;

    /**
     * Text field for the Part ID input.
     */
    @FXML
    private TextField partIdTxt;

    /**
     * Text field for the Part Name input.
     */
    @FXML
    private TextField nameTxt;

    /**
     * Text field for the Part Inventory input.
     */
    @FXML
    private TextField invTxt;

    /**
     * Text field for the Part Price input.
     */
    @FXML
    private TextField priceTxt;

    /**
     * Text field for the Part Maximum input.
     */
    @FXML
    private TextField maxTxt;

    /**
     * Text field for the Machine ID input.
     */
    @FXML
    private TextField machineIdTxt;

    /**
     * Text field for the Part Minimum input.
     */
    @FXML
    private TextField minTxt;

    /**
     * RadioButton for selecting In-House part type.
     */
    @FXML
    private RadioButton inHouseRBtn;

    /**
     * RadioButton for selecting Outsourced part type.
     */
    @FXML
    private RadioButton outsourcedRBtn;

    /**
     * Button for saving the changes.
     */
    @FXML
    private Button saveBtn;

    /**
     * Button for cancelling the changes.
     */
    @FXML
    private Button cancelBtn;

    /**
     * ToggleGroup for switching between In-House and Outsourced part types.
     */
    @FXML
    private ToggleGroup inHouseOutsourcedTG;

    /**
     * Text field for the Company Name input.
     */
    @FXML
    private TextField companyNameTxt;

    /**
     * The current part being added or modified.
     */
    private Part currentPart;

    /**
     * ObservableList for storing the part data.
     */
    private ObservableList<Part> data;

    /**
     * Auto-generate part ID.
     */
    private static AtomicInteger nextID = new AtomicInteger();


    //Add methods
    //Setter method for the ObservableList<Part> data
    /**
     * Setter for ObservableList data
     * @param data Is the dObservableList data
     */
    public void setData(ObservableList<Part> data) {
        this.data = data;
    }

    //Initialization method

    /**
     * Initialization method to set initial state of UI elements.
     */
    @FXML
    private void initialize() {
        // Make companyNameTxt invisible at the start, disable and make partIdTxt uneditable.
        companyNameTxt.setVisible(false);
        partIdTxt.setDisable(true);
        partIdTxt.setEditable(false);
        partIdTxt.setText("Auto Gen- Disabled");
    }
    //Method to populate the text fields with the current part's data

    /**
     * Method to populate the text fields with the current part's data.
     * @param part Is the current part.
     */
    public void setPart(Part part) {
        this.currentPart = part;

        partIdTxt.setText(Integer.toString(part.getId()));
        nameTxt.setText(part.getName());
        invTxt.setText(Integer.toString(part.getStock()));
        priceTxt.setText(Double.toString(part.getPrice()));
        minTxt.setText(Integer.toString(part.getMin()));
        maxTxt.setText(Integer.toString(part.getMax()));

        // Check if part is InHouse or Outsourced and populate the MachineID or CompanyName respectively
        if (part instanceof InHouse) {
            machineIdTxt.setText(Integer.toString(((InHouse) part).getMachineId()));
        } else if (part instanceof Outsourced) {
            companyNameTxt.setText(((Outsourced) part).getCompanyName());
        }
    }

    //Action Event method for InHouse RadioButton
    /**
     * Action Event method for InHouse Radio Button
     * @param event Is the ActionEvent
     */
    @FXML
    private void onActionInHouse(ActionEvent event) {
        // In-House radio button selected, handle accordingly
        machineIdLbl.setText("Machine ID");
        machineIdTxt.setVisible(true);
        companyNameTxt.setVisible(false);
    }

    //Action Event method for Outsourced RadioButton
    /**
     * Action Event method for Outsourced RadioButton
     * @param event Is the ActionEvent
     */
    @FXML
    private void onActionOutsourced(ActionEvent event) {
        // Outsourced radio button selected, handle accordingly
        machineIdLbl.setText("Company Name");
        machineIdTxt.setVisible(false);
        companyNameTxt.setVisible(true);
    }

    //Action Event method for Save Button
        /**RUNTIME ERROR
    Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
	at javafx.fxml/javafx.fxml.FXMLLoader$MethodHandler.invoke(FXMLLoader.java:1857)
	at javafx.fxml/javafx.fxml.FXMLLoader$ControllerMethodEventHandler.handle(FXMLLoader.java:1724)
	at javafx.base/com.sun.javafx.event.CompositeEventHandler.dispatchBubblingEvent(CompositeEventHandler.java:86)
	at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:234)
	at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:191)
	at javafx.base/com.sun.javafx.event.CompositeEventDispatcher.dispatchBubblingEvent(CompositeEventDispatcher.java:59)
	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:58)
	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at javafx.base/com.sun.javafx.event.EventUtil.fireEventImpl(EventUtil.java:74)
	at javafx.base/com.sun.javafx.event.EventUtil.fireEvent(EventUtil.java:49)
	at javafx.base/javafx.event.Event.fireEvent(Event.java:198)
	at javafx.graphics/javafx.scene.Node.fireEvent(Node.java:8792)
	at javafx.controls/javafx.scene.control.Button.fire(Button.java:203)
	at javafx.controls/com.sun.javafx.scene.control.behavior.ButtonBehavior.mouseReleased(ButtonBehavior.java:208)
	at javafx.controls/com.sun.javafx.scene.control.inputmap.InputMap.handle(InputMap.java:274)
	at javafx.base/com.sun.javafx.event.CompositeEventHandler$NormalEventHandlerRecord.handleBubblingEvent(CompositeEventHandler.java:247)
	at javafx.base/com.sun.javafx.event.CompositeEventHandler.dispatchBubblingEvent(CompositeEventHandler.java:80)
	at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:234)
	at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:191)
	at javafx.base/com.sun.javafx.event.CompositeEventDispatcher.dispatchBubblingEvent(CompositeEventDispatcher.java:59)
	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:58)
	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at javafx.base/com.sun.javafx.event.EventUtil.fireEventImpl(EventUtil.java:74)
	at javafx.base/com.sun.javafx.event.EventUtil.fireEvent(EventUtil.java:54)
	at javafx.base/javafx.event.Event.fireEvent(Event.java:198)
	at javafx.graphics/javafx.scene.Scene$MouseHandler.process(Scene.java:3897)
	at javafx.graphics/javafx.scene.Scene.processMouseEvent(Scene.java:1878)
	at javafx.graphics/javafx.scene.Scene$ScenePeerListener.mouseEvent(Scene.java:2623)
	at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:411)
	at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:301)
	at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
	at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler.lambda$handleMouseEvent$2(GlassViewEventHandler.java:450)
	at javafx.graphics/com.sun.javafx.tk.quantum.QuantumToolkit.runWithoutRenderLock(QuantumToolkit.java:424)
	at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler.handleMouseEvent(GlassViewEventHandler.java:449)
	at javafx.graphics/com.sun.glass.ui.View.handleMouseEvent(View.java:557)
	at javafx.graphics/com.sun.glass.ui.View.notifyMouse(View.java:943)
	at javafx.graphics/com.sun.glass.ui.mac.MacView.notifyMouse(MacView.java:127)
Caused by: java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at com.sun.javafx.reflect.Trampoline.invoke(MethodUtil.java:77)
	at jdk.internal.reflect.GeneratedMethodAccessor2.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at javafx.base/com.sun.javafx.reflect.MethodUtil.invoke(MethodUtil.java:275)
	at javafx.fxml/com.sun.javafx.fxml.MethodHelper.invoke(MethodHelper.java:84)
	at javafx.fxml/javafx.fxml.FXMLLoader$MethodHandler.invoke(FXMLLoader.java:1852)
	... 44 more
Caused by: java.lang.NullPointerException: Cannot invoke "javafx.collections.ObservableList.add(Object)" because "this.data" is null
	at com.software1.project1/com.software1.project1.controller.AddPartController.onActionSavePart(AddPartController.java:140)
	... 55 more

    *The issue arises when I attempt to save a new product.
    It looks like the root cause of the error is the 'NullPointerException'.  It explains that 'this.data' is null and is
    being used in 'AddPartController.onActionSavePart' on line 140.  It is attempting to call 'add(Object' on 'this.data'
    but because 'this.data' is 'null' it is unable to do so and we are getting the error.

    *To fix this, We need to locate where problem is occurring, which is in 'AddPartController.onActionSavePart' on line 140.
    Then we have find out where 'this.data' is initialized, which is in the MainScreenController.onActionAddPart' in the 'setData' method.
    Probably in troubleshooting another issue in working with the part list I passed 'null' to 'setData'.
    I needed to pass the 'Inventory.getAllParts()' to 'setData' instead of 'null'.
    After doing the above steps, the program now lets me add a new product when I click the save button.
     */

    /**
     * Action Event method for Save Button.  Collects input data and performs validation before saving.
     * Store as temporary variable until all checks are cleared.
     * @param event Is the ActionEvent
     * @return Whether part is saved or not.
     */
    @FXML
    private boolean onActionSavePart(ActionEvent event) {
        String errorMessage = "";
        Part tempPart = null;

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

        if (inHouseRBtn.isSelected()) {
            // Validate Machine ID
            String machineIdText = machineIdTxt.getText();
            if (!isInteger(machineIdText)) {
                errorMessage += "Invalid Machine ID (must be an integer)!\n";
            } else {
                InHouse part = new InHouse(name, price, stock, min, max, Integer.parseInt(machineIdText));
                // Do not add to list yet, instead store in a temporary variable
                tempPart = part;
            }
        } else if (outsourcedRBtn.isSelected()) {
            String companyName = companyNameTxt.getText();
            if (companyName == null || companyName.isEmpty()) {
                errorMessage += "No valid company name!\n";
            } else {
                Outsourced part = new Outsourced(name, price, stock, min, max, companyName);
                // Do not add to list yet, instead store in a temporary variable
                tempPart = part;
            }
        }

        if (!errorMessage.isEmpty()) {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }

// If no errors, add the part to the observable list
        if (tempPart != null) {
            data.add(tempPart);
        }
        // Get the current stage from the source of the ActionEvent
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current stage
        stage.close();

        return true;
    }

    /**
     * Checks if input is an integer
     * @param text is the input
     * @return boolean
     */

    private boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Validates the inventory by running checks. to see if inventory is between min and max
     * If valid, returns true
     * @param min Is the minimum inventory
     * @param max Is the maximum inventory
     * @param inv Is the inventory to be checked
     * @return Indicates whether the inventory is valid.
     */

    private boolean validateInventory(int min, int max, int inv) {
        // Checks if the inventory count is valid
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

    //Action Event method for Cancel Button

    /**
     * Handles the ActionEvent of Cancel Button.  Closes the current stage
     * @param event Is the Aciton Event that initiates method.
     */
    @FXML
    private void onActionCancel(ActionEvent event) {
        // Get the current stage from the source of the ActionEvent
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current stage
        stage.close();
    }
}
package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.NumberStringConverter;
import viewmodel.AddProductViewModel;

import javax.naming.Binding;
import javax.swing.text.View;
import java.io.IOException;
import java.time.LocalDate;

public class AddProductViewController extends ViewController
{
    @FXML private CheckBox frozen;
    @FXML private CheckBox seafood;
    @FXML private CheckBox diary;
    @FXML private CheckBox bakery;
    @FXML private CheckBox beverages;
    @FXML private CheckBox snacks;
    @FXML private CheckBox fruits;
    @FXML private CheckBox vegetables;
    @FXML private TextField productNameField;
    @FXML private TextField productNumberField;
    @FXML private TextField priceField;
    @FXML private DatePicker dateField;
    @FXML private TextField quantityField;
    @FXML private Label errorLabel;

    private AddProductViewModel addProductViewModel;


    protected void init()
    {
        addProductViewModel = getViewModelFactory().getAddProductViewModel();

        CheckBox [] checkBox = {frozen, seafood, diary, bakery, beverages, snacks, fruits, vegetables};

        for (int i =0; i < 8; i++)
        {

            CheckBox currentCheckBox = checkBox[i];

            EventHandler<ActionEvent> event = actionEvent -> {

                if (currentCheckBox.isSelected())
                {
                    addProductViewModel.addCategory(currentCheckBox.getText());
                }
                else
                {
                    addProductViewModel.removeCategory(currentCheckBox.getText());
                }
            };

            currentCheckBox.setOnAction(event);
        }

        productNameField.textProperty().bindBidirectional(addProductViewModel.getProductNameProperty());
        Bindings.bindBidirectional(productNumberField.textProperty(),addProductViewModel.getProductNumberProperty(),new NumberStringConverter());
        Bindings.bindBidirectional(priceField.textProperty(),addProductViewModel.getPriceProperty());
        Bindings.bindBidirectional(quantityField.textProperty(),addProductViewModel.getQuantityProperty(),new NumberStringConverter());
        dateField.valueProperty().bindBidirectional(addProductViewModel.getExpirationDateProperty());
        errorLabel.textProperty().bind(addProductViewModel.getErrorLabelProperty());

    }

    public void reset()
    {
        addProductViewModel.clear();

        CheckBox [] checkBox = {frozen, seafood, diary, bakery, beverages, snacks, fruits, vegetables};

        for (int i =0; i < 8; i++)
        {
            checkBox[i].setSelected(false);
        }

    }

    @FXML private void addProductButton()
    {

       addProductViewModel.addProduct();
        productNumberField.setText("");
        quantityField.setText("");
        dateField.valueProperty().set(null);
    }

    @FXML private void backButton(ActionEvent actionEvent) throws IOException
    {
        getViewHandler().openView("Employee");
    }
}

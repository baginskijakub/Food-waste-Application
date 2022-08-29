package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utility.NumberStringConverter;
import viewmodel.EditProductViewModel;
import viewmodel.PickUpViewModel;

import javax.swing.*;
import java.io.IOException;

public class EditProductViewController extends ViewController
{
  @FXML private TextField productNameField;
  @FXML private TextField productNumberField;
  @FXML private TextField priceField;
  @FXML private DatePicker dateField;
  @FXML private TextField quantityField;
  @FXML private Label errorLabel;
  @FXML private CheckBox frozen;
  @FXML private CheckBox seafood;
  @FXML private CheckBox diary;
  @FXML private CheckBox bakery;
  @FXML private CheckBox beverages;
  @FXML private CheckBox snacks;
  @FXML private CheckBox fruits;
  @FXML private CheckBox vegetables;

  private EditProductViewModel editProductViewModel;

  @Override protected void init()
  {
    editProductViewModel = getViewModelFactory().getEditProductViewModel();

    CheckBox [] checkBox = {frozen, seafood, diary, bakery, beverages, snacks, fruits, vegetables};

    for (int i =0; i < 8; i++)
    {

      CheckBox currentCheckBox = checkBox[i];

      EventHandler<ActionEvent> event = actionEvent -> {

        if (currentCheckBox.isSelected())
        {
          editProductViewModel.addCategory(currentCheckBox.getText());
        }
        else
        {
          editProductViewModel.removeCategory(currentCheckBox.getText());
        }
      };

      currentCheckBox.setOnAction(event);
    }

    productNameField.textProperty().bindBidirectional(editProductViewModel.getProductNameProperty());
    Bindings.bindBidirectional(priceField.textProperty(),editProductViewModel.getPriceProperty());
    Bindings.bindBidirectional(quantityField.textProperty(),editProductViewModel.getQuantityProperty(), new NumberStringConverter());
    errorLabel.textProperty().bind(editProductViewModel.getErrorLabelProperty());
    Bindings.bindBidirectional(productNumberField.textProperty(),editProductViewModel.getProductNumberProperty(), new NumberStringConverter());

    dateField.valueProperty().bindBidirectional(editProductViewModel.getExpirationDateProperty());



  }


  public void reset()
  {
    editProductViewModel.clear();


    CheckBox [] checkBox = {frozen, seafood, diary, bakery, beverages, snacks, fruits, vegetables};

    for (int i =0; i < 8; i++)
    {

      checkBox[i].setSelected(editProductViewModel.getPickedCategoryProperty()
          .contains(checkBox[i].getText()));
    }

  }

  @FXML private void backButton() throws IOException
  {
    getViewHandler().openView("EmployeeManageItems");
  }

  @FXML private void editProduct()
  {

    editProductViewModel.editProduct();
  }
}

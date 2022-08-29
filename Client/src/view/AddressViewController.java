package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.NumberStringConverter;
import view.ViewController;
import viewmodel.AddressViewModel;

import java.io.IOException;

public class AddressViewController extends ViewController
{
  @FXML private TextField addressPrimaryField;
  @FXML private TextField addressSecondaryField;
  @FXML private TextField cityField;
  @FXML private TextField postalCodeField;
  @FXML private Label errorLabel;

  private AddressViewModel addressViewModel;

  @Override protected void init()
  {
    addressViewModel = getViewModelFactory().getAddressViewModel();

    errorLabel.textProperty().bind(addressViewModel.getError());

    addressPrimaryField.textProperty()
        .bindBidirectional(addressViewModel.getAddress1());
    addressSecondaryField.textProperty()
        .bindBidirectional(addressViewModel.getAddress2());
    cityField.textProperty().bindBidirectional(addressViewModel.getCity());

    Bindings.bindBidirectional(postalCodeField.textProperty(),
        addressViewModel.getPostalCode(), new NumberStringConverter());
  }

  @FXML private void submitButton() throws IOException
  {

    if (addressViewModel.setDeliveryOptions())
    {
      getViewHandler().openView("Payment");
    }

  }

  @FXML private void goBackButton() throws IOException
  {
    getViewHandler().openView("Delivery");
  }

  public void reset()
  {
    addressViewModel.clear();
  }

}

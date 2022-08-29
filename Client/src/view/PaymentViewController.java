package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.NumberStringConverter;
import view.ViewController;
import viewmodel.PaymentViewModel;

import java.io.IOException;

public class PaymentViewController extends ViewController
{
  @FXML private Label titleLabel;
  @FXML private TextField nameField;
  @FXML private TextField numberField;
  @FXML private ComboBox<Number> monthField;
  @FXML private ComboBox<Number> yearField;
  @FXML private TextField securityField;
  @FXML private Label errorLabel;
  @FXML private Button payButton;

  private PaymentViewModel paymentViewModel;

  @Override protected void init()
  {
    paymentViewModel = getViewModelFactory().getPaymentViewModel();

    nameField.textProperty().bindBidirectional(paymentViewModel.getName());
    Bindings.bindBidirectional(numberField.textProperty(),
        paymentViewModel.getCardNo(), new NumberStringConverter());
    Bindings.bindBidirectional(securityField.textProperty(),
        paymentViewModel.getSecurityCode(), new NumberStringConverter());
    errorLabel.textProperty().bind(paymentViewModel.getError());

    monthField.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    yearField.getItems().addAll(2022, 2023, 2024, 2025, 2026, 2027, 2028);
    // limited choice on year selection, since there is only 2-3 years given for credit cards expiration

    monthField.valueProperty()
        .bindBidirectional(paymentViewModel.getMonthField());
    yearField.valueProperty()
        .bindBidirectional(paymentViewModel.getYearField());

    payButton.textProperty().bind(paymentViewModel.getPaymentCount());
  }

  @FXML private void clickToPayButton() throws IOException
  {
    if (paymentViewModel.setPaymentOptions())
    {
      getViewHandler().openView("Order");
    }

  }

  public void reset()
  {
    monthField.valueProperty().set(null);
    yearField.valueProperty().set(null);
    paymentViewModel.clear();
  }

  @FXML private void goBackButton() throws IOException
  {
    getViewHandler().openView("Delivery");
  }
}

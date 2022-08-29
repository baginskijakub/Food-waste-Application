package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewmodel.LoginViewModel;

import java.io.IOException;

public class LoginViewController extends ViewController
{
  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  @FXML private Label errorLabel;

  private LoginViewModel loginViewModel;

  protected void init()
  {
    loginViewModel = getViewModelFactory().getLoginViewModel();

    usernameField.textProperty()
        .bindBidirectional(loginViewModel.getUsernameProperty());
    passwordField.textProperty()
        .bindBidirectional(loginViewModel.getPasswordProperty());
    errorLabel.textProperty().bind(loginViewModel.getErrorLabelProperty());
  }

  public void reset()
  {
    loginViewModel.clear();
  }

  @FXML private void clickLogin() throws IOException
  {
    if (loginViewModel.login())
    {
      getViewHandler().openView("Employee");

    }

  }

  @FXML private void backButton() throws IOException
  {
    getViewHandler().openView("Shop");
  }
}

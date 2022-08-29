package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ModelEmployee;
import model.User;

public class LoginViewModel
{
  private StringProperty username;
  private StringProperty password;
  private StringProperty errorLabel;
  private ModelEmployee model;
  private UserViewState viewState;

  public LoginViewModel(ModelEmployee model, UserViewState userViewState)
  {
    this.model = model;
    viewState = userViewState;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty();
  }

  public void clear()
  {
    username.set("");
    password.set("");
    errorLabel.set("");
  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }

  public StringProperty getPasswordProperty()
  {
    return password;
  }

  public StringProperty getErrorLabelProperty()
  {
    return errorLabel;
  }

  public boolean login()
  {
    try
    {
      User user = model.getUser(username.get(), password.get());
      viewState.setUsername(username.get());
      viewState.setShopAddress(user.getShopAddress());
      return true;
    }
    catch (Exception e)
    {
      errorLabel.set(e.getMessage());
      username.set("");
      password.set("");
      return false;
    }
  }
}

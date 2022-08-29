package viewmodel;

import model.Product;

public class UserViewState
{
  private String username;
  private String shopAddress;

  public UserViewState()
  {
    username = null;
    shopAddress = null;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getUsername()
  {
    return username;
  }

  public void setShopAddress(String shopAddress)
  {
    this.shopAddress = shopAddress;
  }

  public String getShopAddress()
  {
    return shopAddress;
  }
}

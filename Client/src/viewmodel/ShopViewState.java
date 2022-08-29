package viewmodel;


public class ShopViewState
{
  private String shopAddress;

  public ShopViewState()
  {
    shopAddress = null;
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

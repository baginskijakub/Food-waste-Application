package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Product;
import model.Shop;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShopsViewModel implements PropertyChangeListener
{
  private StringProperty bagCounter;
  private ObservableList<ShopsTableVM> shops;
  private Model model;
  private ShopViewState shopViewState;

  public ShopsViewModel(Model model, ShopViewState shopViewState)
  {
    this.model = model;
    this.shopViewState = shopViewState;
    bagCounter = new SimpleStringProperty();
    shops = FXCollections.observableArrayList();
    this.model.addListener(this);

    update();
    clear();
  }

  public void update()
  {
    shops.clear();
    for (Shop shop : model.getAllShops())
    {
      add(shop);
    }
  }

  public void add(Shop shop)
  {

    shops.add(new ShopsTableVM(shop));
  }

  public void clear()
  {
    this.bagCounter.set("Bag (" + model.getQuantityOfItemsInBag() + ")");
    update();
  }

  public StringProperty getBagCounter()
  {
    return bagCounter;
  }

  public ObservableList<ShopsTableVM> getShops()
  {
    return shops;
  }

  public void chooseShop(ShopsTableVM shopsTableVM)
  {
    String shopAddress = shopsTableVM.getAddress().get();
    shopViewState.setShopAddress(shopAddress);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("StockUpdate"))
    {
      Platform.runLater(this::update);

    }
  }
}

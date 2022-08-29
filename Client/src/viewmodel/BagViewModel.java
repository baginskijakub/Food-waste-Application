package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ClientUserModel;
import model.Date;
import model.Item;
import model.ModelUser;

import java.util.Map;

public class BagViewModel
{
  private StringProperty errorProperty;
  private StringProperty priceProperty;
  private ObservableList<ItemsTableVM> items;
  private ClientUserModel model;
  private ShopViewState shopViewState;

  public BagViewModel(ClientUserModel model, ShopViewState shopViewState)
  {
    this.model = model;
    errorProperty = new SimpleStringProperty();
    priceProperty = new SimpleStringProperty();
    items = FXCollections.observableArrayList();
    this.shopViewState = shopViewState;

    update();
  }

  public void update()
  {
    items.clear();

    for (Map.Entry<Item, Integer> entry : model.getOrder().getItems()
        .entrySet())
    {
      int orderQuantity = entry.getValue();
      add(entry.getKey(), orderQuantity);
    }
  }

  private void add(Item item)
  {
    items.add(new ItemsTableVM(item, -1));
  }

  private void add(Item item, int orderQuantity)
  {
    items.add(new ItemsTableVM(item, orderQuantity));
  }

  public void clear()
  {
    errorProperty.set("");
    priceProperty.set(
        "Total price: " + model.getOrder().getTotalPrice() + "DKK");
    update();
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public StringProperty priceProperty()
  {
    return priceProperty;
  }

  public ObservableList<ItemsTableVM> getItemsList()
  {
    return items;
  }

  public void removeItemFromBag(ItemsTableVM itemsTableVM)
  {
    String[] dateString = itemsTableVM.getDateProperty().get().split("-");

    Item item = model.getSpecificItem(model.getOrder().getShopAddress(),
        new Date(Integer.parseInt(dateString[0]),
            Integer.parseInt(dateString[1]), Integer.parseInt(dateString[2])),
        itemsTableVM.getIdProperty().get());

    model.removeItemFromBag(item);
    clear();
  }

  public boolean validateCheckout()
  {
    return model.getOrder().getItems().size() > 0;
  }
}

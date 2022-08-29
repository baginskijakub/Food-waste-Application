package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class EmployeeManageItemsViewModel implements PropertyChangeListener
{
  private ObservableList<ItemsTableVM> itemsTableVM;
  private ModelEmployee modelEmployee;
  private UserViewState userViewState;
  private ItemViewState itemViewState;

  public EmployeeManageItemsViewModel(Model model, UserViewState userViewState,
      ItemViewState itemViewState)
  {
    this.itemsTableVM = FXCollections.observableArrayList();
    this.itemViewState = itemViewState;
    this.modelEmployee = model;

    this.userViewState = userViewState;
    model.addListener(this);

  }

  public void update()
  {
    itemsTableVM.clear();

    for (Item item : modelEmployee.getAllItemsFromShop(
        userViewState.getShopAddress()))
    {
      add(item);
    }
  }

  public void chooseItem(ItemsTableVM itemsTableVM)
  {
    String[] dateString = itemsTableVM.getDateProperty().get().split("-");

    Item item = modelEmployee.getSpecificItem(userViewState.getShopAddress(),
        new Date(Integer.parseInt(dateString[0]),
            Integer.parseInt(dateString[1]), Integer.parseInt(dateString[2])),
        itemsTableVM.getIdProperty().get());
    itemViewState.setItem(item);
  }

  private void add(Item item)
  {
    itemsTableVM.add(new ItemsTableVM(item, -1));
  }

  public void removeItem(ItemsTableVM itemsTableVM)
  {

    String[] dateString = itemsTableVM.getDateProperty().get().split("-");

    modelEmployee.removeItem(userViewState.getShopAddress(),
        new Date(Integer.parseInt(dateString[0]),
            Integer.parseInt(dateString[1]), Integer.parseInt(dateString[2])),
        itemsTableVM.getIdProperty().get());
  }

  public ObservableList<ItemsTableVM> getItems()
  {
    return itemsTableVM;
  }

  public void clear()
  {
    update();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

      if (evt.getPropertyName().equals("StockUpdate"))
      {
        if (userViewState.getShopAddress() != null)
        {
          Platform.runLater(() -> {
            update();
          });
        }
    }
  }
}

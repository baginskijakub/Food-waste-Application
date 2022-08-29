package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Model;
import model.ModelUser;
import model.Order;

import java.util.Map;

public class OrderViewModel
{
  private ModelUser model;
  private ObservableList<OrderTableVM> orderItems;
  private StringProperty orderTitle;
  private OrderViewState orderViewState;

  public OrderViewModel(Model model, OrderViewState orderViewState)
  {
    this.model = model;
    this.orderViewState = orderViewState;
    this.orderItems = FXCollections.observableArrayList();
    orderTitle = new SimpleStringProperty("Order summary");
  }


    public void update()
    {
        Order order = orderViewState.getOrder();

        orderItems.clear();
        for (Map.Entry<Item, Integer> entry : order.getItems().entrySet())
        {
            int orderQuantity = entry.getValue();
            add(entry.getKey(), orderQuantity);
        }
    }

    public void add(Item item, int quantity)
    {
            orderItems.add(new OrderTableVM(item, quantity));

    }

    public void clear()
    {

        update();
    }

    public ObservableList<OrderTableVM> getOrderItems()
    {
        return orderItems;
    }

    public StringProperty getOrderTitle()
    {
        return orderTitle;
    }
}

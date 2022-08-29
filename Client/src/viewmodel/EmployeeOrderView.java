package viewmodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Item;
import model.Model;
import model.ModelEmployee;
import model.Order;

import java.awt.*;
import java.util.Map;

public class EmployeeOrderView
{

  private StringProperty orderLabel;
  private StringProperty orderDescription;
  private ObservableList<EmployeeOrderTableVM> employeeOrders;
  private ModelEmployee model;
  private OrderViewState orderViewState;

  public EmployeeOrderView(Model model, OrderViewState orderViewState)
  {
    this.model = model;
    this.orderViewState = orderViewState;
    orderLabel = new SimpleStringProperty("Order summary");
    orderDescription = new SimpleStringProperty();
    employeeOrders = FXCollections.observableArrayList();

  }

  public void clear()
  {
    orderDescription.set(orderViewState.getOrder().getOrderDescription());
    update();
  }

  public void update()
  {
    employeeOrders.clear();
    for (Map.Entry<Item, Integer> entry : orderViewState.getOrder().getItems()
        .entrySet())
    {
      add(entry.getKey(), entry.getValue());
    }
  }

  public void add(Item item, int quantity)
  {
    employeeOrders.add(new EmployeeOrderTableVM(item, quantity));
  }

  public StringProperty getOrderDescription()
  {
    return orderDescription;
  }

  public StringProperty getOrderLabel()
  {
    return orderLabel;
  }

  public ObservableList<EmployeeOrderTableVM> getEmployeeOrders()
  {
    return employeeOrders;
  }

  public void completeOrder()
  {
    Order order = orderViewState.getOrder();
    String[] dateString = order.getDate().toString().split("-");

    int day = Integer.parseInt(dateString[2]);
    int month = Integer.parseInt(dateString[1]);
    int year = Integer.parseInt(dateString[0]);

    model.removeOrder(order.getShopAddress(), day, month, year, order.getHour(),
        order.getMinute(), order.getSecond(), order.getOrderDescription());
  }

}
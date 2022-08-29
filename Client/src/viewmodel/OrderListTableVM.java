package viewmodel;

import javafx.beans.property.*;
import model.Order;

public class OrderListTableVM
{
  private StringProperty time;
  private StringProperty date;
  private DoubleProperty totalPrice;
  private StringProperty deliveryOptions;

  public OrderListTableVM(Order order)
  {
    time = new SimpleStringProperty(order.toStringTime());
    date = new SimpleStringProperty(order.getDate().toString());
    totalPrice = new SimpleDoubleProperty(order.getTotalPrice());
    deliveryOptions = new SimpleStringProperty(order.getOrderDescription());
  }

  public StringProperty getTime()
  {
    return time;
  }

  public StringProperty getDate()
  {
    return date;
  }

  public DoubleProperty getTotalPrice()
  {
    return totalPrice;
  }

  public StringProperty getDeliveryOptions()
  {
    return deliveryOptions;
  }
}
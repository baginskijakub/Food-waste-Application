package viewmodel;

import model.Order;

public class OrderViewState
{
  private Order order;

  public OrderViewState()
  {
    this.order= null;
  }

  public Order getOrder()
  {
    return order;
  }

  public void setOrder(Order order)
  {
    this.order = order;
  }
}

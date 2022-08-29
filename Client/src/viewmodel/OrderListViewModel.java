package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class OrderListViewModel
{
  private ObservableList<OrderListTableVM> orders;
  private ModelEmployee model;
  private UserViewState userViewState;
  private OrderViewState orderViewState;

  public OrderListViewModel(Model model, UserViewState userViewState, OrderViewState orderViewState)
  {
    this.model = model;
    this.userViewState = userViewState;
    this.orderViewState = orderViewState;
    orders = FXCollections.observableArrayList();
  }

  public void remove(OrderListTableVM orderListTableVM)
  {
    String[] timeString = orderListTableVM.getTime().get().split(":");
    String[] dateString = orderListTableVM.getDate().get().split("-");

    int day = Integer.parseInt(dateString[0]);
    int month = Integer.parseInt(dateString[1]);
    int year = Integer.parseInt(dateString[2]);
    int hour = Integer.parseInt(timeString[0]);
    int minute = Integer.parseInt(timeString[1]);
    int second = Integer.parseInt(timeString[2]);

    model.removeOrder(userViewState.getShopAddress(), day,month,year,hour,minute,second, orderListTableVM.getDeliveryOptions().get());
    update();
  }

  public void update()
  {
    orders.clear();
    for (Order order : model.getOrderList(userViewState.getShopAddress()))
    {
      if (!order.isCompleted())
      {
        add(order);
      }
    }
  }

  public void add(Order order)
  {
    orders.add(new OrderListTableVM(order));
  }

  public ObservableList<OrderListTableVM> getOrders()
  {
    return orders;
  }

  public void clear()
  {
    update();
  }

  public void chooseOrder(OrderListTableVM orderListTableVM)
  {

    orderViewState.setOrder(findOrder(orderListTableVM));
  }

  private Order findOrder(OrderListTableVM orderListTableVM)
  {
    String[] timeString = orderListTableVM.getTime().get().split(":");
    String[] dateString = orderListTableVM.getDate().get().split("-");

    int day = Integer.parseInt(dateString[0]);
    int month = Integer.parseInt(dateString[1]);
    int year = Integer.parseInt(dateString[2]);
    int hour = Integer.parseInt(timeString[0]);
    int minute = Integer.parseInt(timeString[1]);
    int second = Integer.parseInt(timeString[2]);

    Order order = model.getOrder(userViewState.getShopAddress(), day,month,year,hour,minute,second, orderListTableVM.getDeliveryOptions().get());

    return order;
  }
}
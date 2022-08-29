package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Order;
import view.ViewController;
import viewmodel.OrderListTableVM;
import viewmodel.OrderListViewModel;

import java.io.IOException;

public class OrderListViewController extends ViewController
{
    @FXML private TableView<OrderListTableVM> table;
    @FXML private TableColumn<OrderListTableVM, String> time;
    @FXML private TableColumn<OrderListTableVM, Number> totalPrice;
    @FXML private TableColumn<OrderListTableVM, String> dateOfOrder;
    @FXML private TableColumn<OrderListTableVM, String> address;

    private OrderListViewModel orderListViewModel;

    @Override
    protected void init()
    {
        orderListViewModel = getViewModelFactory().getOrderListViewModel();


        time.setCellValueFactory(cellData -> cellData.getValue().getTime());
        totalPrice.setCellValueFactory(cellData -> cellData.getValue().getTotalPrice());
        dateOfOrder.setCellValueFactory(cellData -> cellData.getValue().getDate());
        address.setCellValueFactory(cellData -> cellData.getValue().getDeliveryOptions());

        table.setItems(orderListViewModel.getOrders());

    }

    @FXML private void goBackButton() throws IOException
    {
        getViewHandler().openView("Employee");
    }

    @FXML private void removeButton() throws IOException
    {
        OrderListTableVM orderListTableVM = table.getSelectionModel()
            .getSelectedItem();
        if (orderListTableVM != null)
        {
            orderListViewModel.remove(orderListTableVM);
        }

    }

    @FXML private void viewButton() throws IOException
    {

        OrderListTableVM orderListTableVM = table.getSelectionModel()
            .getSelectedItem();
        if (orderListTableVM != null)
        {
            orderListViewModel.chooseOrder(orderListTableVM);
            getViewHandler().openView("EmployeeOrderOverview");
        }
    }

    public void reset()
    {
        orderListViewModel.clear();
    }
}

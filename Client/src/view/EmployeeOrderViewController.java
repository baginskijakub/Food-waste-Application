package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ViewController;
import viewmodel.EmployeeOrderTableVM;
import viewmodel.EmployeeOrderView;
import viewmodel.OrderListTableVM;

import java.io.IOException;

public class EmployeeOrderViewController extends ViewController
{
    @FXML private TableView<EmployeeOrderTableVM> table;
    @FXML private TableColumn<EmployeeOrderTableVM, String> nameOfProduct;
    @FXML private TableColumn<EmployeeOrderTableVM, Number> quantity;
    @FXML private TableColumn<EmployeeOrderTableVM, String> expiration;
    @FXML private TableColumn<EmployeeOrderTableVM, Number> price;
    @FXML private Label orderLabel;
    @FXML private TextArea textArea;

    private EmployeeOrderView employeeOrderViewModel;

    @Override
    protected void init()
    {
        employeeOrderViewModel = getViewModelFactory().getEmployeeOrderView();



        nameOfProduct.setCellValueFactory(cellData -> cellData.getValue().getProductName());
        quantity.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
        expiration.setCellValueFactory(cellData -> cellData.getValue().getDate());
        price.setCellValueFactory(cellData -> cellData.getValue().getPrice());

        table.setItems(employeeOrderViewModel.getEmployeeOrders());

        orderLabel.textProperty().bind(employeeOrderViewModel.getOrderLabel());
        textArea.textProperty().bind(employeeOrderViewModel.getOrderDescription());
        textArea.setEditable(false);
    }

    @FXML private void goBackButton() throws IOException
    {
        getViewHandler().openView("EmployeeViewOrderList");
    }

    @FXML private void removeButton() throws IOException
    {
        employeeOrderViewModel.completeOrder();
        getViewHandler().openView("EmployeeViewOrderList");
    }
    
     public void reset()
    {
        employeeOrderViewModel.clear();
    }
}

package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import view.ViewController;
import viewmodel.OrderTableVM;
import viewmodel.OrderViewModel;

import java.io.IOException;

public class OrderViewController extends ViewController
{
    @FXML private TableView<OrderTableVM> table;
    @FXML private TableColumn<OrderTableVM, String> nameOfProduct;
    @FXML private TableColumn<OrderTableVM, Number> quantity;
    @FXML private TableColumn<OrderTableVM, String> expiration;
    @FXML private TableColumn<OrderTableVM, Number> price;
    @FXML private Label orderLabel;

    private OrderViewModel orderViewModel;

    @Override
    protected void init()
    {
        orderViewModel = getViewModelFactory().getOrderViewModel();

        orderLabel.textProperty().bind(orderViewModel.getOrderTitle());

        quantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        nameOfProduct.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        expiration.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        table.setItems(orderViewModel.getOrderItems());
    }

    @FXML private void homeButton() throws IOException
    {
        getViewHandler().openView("Shop");
    }

    public void reset()
    {
        orderViewModel.clear();
    }
}

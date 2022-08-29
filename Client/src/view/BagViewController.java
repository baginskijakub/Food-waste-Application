package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.BagViewModel;
import viewmodel.ItemsTableVM;

import java.io.IOException;

public class BagViewController extends ViewController
{
    @FXML private TableView<ItemsTableVM> table;
    @FXML private TableColumn<ItemsTableVM, String> nameOfProduct;
    @FXML private TableColumn<ItemsTableVM, Number> productId;
    @FXML private TableColumn<ItemsTableVM, String> expiration;
    @FXML private TableColumn<ItemsTableVM, Number> quantity;
    @FXML private TableColumn<ItemsTableVM, Number> price;
    @FXML private Label totalPriceLabel;

    private BagViewModel bagViewModel;

    protected void init()
    {

        bagViewModel = getViewModelFactory().getBagViewModel();

        nameOfProduct.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        expiration.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        quantity.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        price.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        productId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());

        table.setItems(bagViewModel.getItemsList());

        totalPriceLabel.textProperty().bindBidirectional(bagViewModel.priceProperty());

    }

    @FXML private void goToCheckOut() throws IOException
    {
        if (bagViewModel.validateCheckout())
        {
            getViewHandler().openView("Delivery");
        }
    }

    public void reset()
    {
        bagViewModel.clear();
        table.setItems(bagViewModel.getItemsList());
    }

    @FXML private void goBackButton() throws IOException
    {
        getViewHandler().openView("Shop");
    }


    @FXML private void loginButton() throws IOException
    {
        getViewHandler().openView("Login");
    }

    @FXML private void removeButton()
    {
        ItemsTableVM itemsTableVM = table.getSelectionModel().getSelectedItem();

        if(itemsTableVM != null)
        {
            bagViewModel.removeItemFromBag(itemsTableVM);
        }

    }
}

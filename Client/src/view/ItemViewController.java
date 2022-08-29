package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.ItemsTableVM;
import viewmodel.ItemsViewModel;

import java.io.IOException;

public class ItemViewController extends ViewController
{
    @FXML private Label productNameLabel;
    @FXML private Label productIdLabel;
    @FXML private TableView<ItemsTableVM> listOfItems;
    @FXML private TableColumn<ItemsTableVM, String> nameOfProduct;
    @FXML private TableColumn<ItemsTableVM, String> expiration;
    @FXML private TableColumn<ItemsTableVM, Number> quantity;
    @FXML private TableColumn<ItemsTableVM, Number> price;
    @FXML private Label errorLabel;
    @FXML private Button openBagView;

    private ItemsViewModel itemViewModel;

    protected void init()
    {
        itemViewModel = getViewModelFactory().getItemsViewModel();

        productNameLabel.textProperty().bindBidirectional(itemViewModel.getProductNameProperty());
        productIdLabel.textProperty().bindBidirectional(itemViewModel.getProductIdProperty());

        nameOfProduct.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        expiration.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        quantity.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        price.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

        listOfItems.setItems(itemViewModel.getItems());

        errorLabel.textProperty().bind(itemViewModel.getErrorProperty());
        openBagView.textProperty().bindBidirectional(itemViewModel.getBagCounterProperty());
    }

    @FXML private void addingButton() throws IOException
    {
        ItemsTableVM selectedItem = listOfItems.getSelectionModel()
            .getSelectedItem();
        if (selectedItem != null)
        {
            if (itemViewModel.addToBag(selectedItem))
            {
                getViewHandler().openView("Product");
            }
        }

    }

    @FXML private void openBag() throws IOException
    {
        getViewHandler().openView("Bag");
    }

    @FXML private void goBackButton() throws IOException
    {
        getViewHandler().openView("Product");
    }

    public void reset()
    {
        itemViewModel.clear();
        listOfItems.setItems(itemViewModel.getItems());
    }


    @FXML private void loginButton() throws IOException
    {
        getViewHandler().openView("Login");
    }
}

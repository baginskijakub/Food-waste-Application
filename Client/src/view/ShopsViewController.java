package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import view.ViewController;
import viewmodel.ProductsTableVM;
import viewmodel.ShopsTableVM;
import viewmodel.ShopsViewModel;


import java.io.IOException;

public class ShopsViewController extends ViewController
{
    @FXML private TableView<ShopsTableVM> table;
    @FXML private TableColumn<ShopsTableVM, String> shopName;
    @FXML private TableColumn<ShopsTableVM, String> addressName;
    @FXML private TableColumn<ShopsTableVM, Number> availableProducts;
    @FXML private Button bagButton;

    private ShopsViewModel shopsViewModel;

    @Override
    protected void init()
    {
        shopsViewModel = getViewModelFactory().getShopsViewModel();
        shopName.setCellValueFactory(cellData -> cellData.getValue().getName());
        addressName.setCellValueFactory(cellData -> cellData.getValue().getAddress());
        availableProducts.setCellValueFactory(cellData -> cellData.getValue().getAvailableProducts());

        table.setItems(shopsViewModel.getShops());
        bagButton.textProperty().bindBidirectional(shopsViewModel.getBagCounter());


        table.setOnMouseClicked(event -> {

            ShopsTableVM selectedShop = table.getSelectionModel().getSelectedItem();
            if (selectedShop != null)
            {
                shopsViewModel.chooseShop(selectedShop);
                try
                {
                    getViewHandler().openView("Product");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    @FXML private void checkBagStatus() throws IOException
    {
        getViewHandler().openView("Bag");
    }

    @FXML private void loginButton() throws IOException
    {
        getViewHandler().openView("Login");
    }

     public void reset()
     {
         shopsViewModel.clear();
         table.setItems(shopsViewModel.getShops());
     }
}

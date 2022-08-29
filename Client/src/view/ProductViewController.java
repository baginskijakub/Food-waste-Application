package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.ProductsTableVM;
import viewmodel.ProductsViewModel;

import java.io.IOException;


public class ProductViewController extends ViewController
{
    @FXML private CheckBox frozen;
    @FXML private CheckBox seafood;
    @FXML private CheckBox diary;
    @FXML private CheckBox bakery;
    @FXML private CheckBox beverages;
    @FXML private CheckBox snacks;
    @FXML private CheckBox fruits;
    @FXML private CheckBox vegetables;
    @FXML private TableView<ProductsTableVM> table;
    @FXML private TableColumn<ProductsTableVM, String> productName;
    @FXML private TableColumn<ProductsTableVM, Number> productId;
    @FXML private TableColumn<ProductsTableVM, Number> quantity;
    @FXML private TableColumn<ProductsTableVM, Number> price;
    @FXML private Button bagButton;

    private ProductsViewModel productViewModel;

    public ProductViewController()
    {

    }

    protected void init()
    {
        productViewModel = getViewModelFactory().getProductsViewModel();

        CheckBox [] checkBox = {frozen, seafood, diary, bakery, beverages, snacks, fruits, vegetables};

        for (int i =0; i < 8; i++)
        {

            CheckBox currentCheckBox = checkBox[i];

            EventHandler<ActionEvent> event = actionEvent -> {

                if (currentCheckBox.isSelected())
                {
                    productViewModel.addCategory(currentCheckBox.getText());
                    productViewModel.update();
                }
                else
                {
                    productViewModel.removeCategory(currentCheckBox.getText());
                    productViewModel.update();
                }
            };

            currentCheckBox.setOnAction(event);
        }

        bagButton.textProperty().bindBidirectional(productViewModel.getBagCounterProperty());

        quantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        price.setCellValueFactory(cellData -> cellData.getValue().lowestPriceProperty());
        productName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        productId.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        table.setItems(productViewModel.getProducts());

        table.setOnMouseClicked(event -> {

            ProductsTableVM selectedProduct = table.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                productViewModel.chooseProduct(selectedProduct);
                try
                {
                    getViewHandler().openView("Item");
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

    public void reset()
    {
        productViewModel.clear();
        table.setItems(productViewModel.getProducts());
    }

    @FXML private void loginButton() throws IOException
    {
        getViewHandler().openView("Login");
    }

    @FXML private void backButton() throws IOException
    {
        getViewHandler().openView("Shop");
    }



}

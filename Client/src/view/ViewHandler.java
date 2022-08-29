package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler extends ViewCreator
{
    private Stage primaryStage;
    private Scene currentScene;
    private ViewModelFactory viewModelFactory;
    private ViewController viewController;

    public ViewHandler(ViewModelFactory viewModelFactory)
    {
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) throws IOException
    {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region());
        openView("Shop");
    }

    public void openView(String id) throws IOException
    {
        Region root = null;
        ViewController viewController;
        switch (id)
        {
            case "Product":
                viewController = getViewController("ProductsView");
                root = viewController.getRoot();
                break;
            case "Item":
                viewController = getViewController("ItemView");
                root = viewController.getRoot();
                break;
            case "Bag":
                viewController = getViewController("BagView");
                root = viewController.getRoot();
                break;
            case "Login":
                viewController = getViewController("LoginView");
                root = viewController.getRoot();
                break;
            case "AddProduct":
                viewController = getViewController("AddProductView");
                root = viewController.getRoot();
                break;
            case "Employee":
                viewController = getViewController("EmployeeView");
                root = viewController.getRoot();
                break;
            case "Shop":
                viewController = getViewController("ShopsView");
                root = viewController.getRoot();
                break;
            case "Delivery":
                viewController = getViewController("DeliveryView");
                root = viewController.getRoot();
                break;
            case "Pickup":
                viewController = getViewController("PickUpView");
                root = viewController.getRoot();
                break;
            case "Payment":
                viewController = getViewController("PaymentView");
                root = viewController.getRoot();
                break;
            case "Order":
                viewController = getViewController("OrderView");
                root = viewController.getRoot();
                break;
            case "Address":
                viewController = getViewController("AddressView");
                root = viewController.getRoot();
                break;
            case "EmployeeViewOrderList":
                viewController = getViewController("OrderListView");
                root = viewController.getRoot();
                break;
            case "EmployeeOrderOverview":
                viewController = getViewController("EmployeeOrderView");
                root = viewController.getRoot();
                break;
            case "EmployeeManageItems":
                viewController = getViewController("EmployeeManageItemsView");
                root = viewController.getRoot();
                break;
            case "EditProduct":
                viewController = getViewController("EditProductView");
                root = viewController.getRoot();
                break;
        }
        currentScene.setRoot(root);

        String title = "";
        if (root.getUserData() != null)
        {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    protected void initViewController (ViewController controller, Region root)
    {
        controller.init(this, viewModelFactory, root);
    }
}

package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.Client;
import model.ClientUserModel;
import model.ModelUser;
import model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ProductsViewModel implements PropertyChangeListener
{
    private ClientUserModel model;
    private ObservableList<ProductsTableVM> products;
    private ObservableList<String> pickedCategory;
    private StringProperty bagCounter;
    private ProductViewState productViewState;
    private ShopViewState shopViewState;

    public ProductsViewModel(ClientUserModel model, ProductViewState productViewState, ShopViewState shopViewState)
    {
        this.model = model;
        this.shopViewState = shopViewState;
        this.productViewState = productViewState;
        this.products = FXCollections.observableArrayList();
        this.pickedCategory = FXCollections.observableArrayList();
        this.bagCounter = new SimpleStringProperty();
        this.model.addListener(this);


    }

    public void update()
    {
        if(shopViewState.getShopAddress() != null)
        {
            products.clear();
            ArrayList<String> categoriesNames = new ArrayList<>(pickedCategory);
            for (Product product : model.getProductsByCategory(shopViewState.getShopAddress(),categoriesNames))
            {
                add(product);
            }
        }

    }

    public void add(Product product)
    {
        double lowestPrice = model.getLowestPriceOfProduct(shopViewState.getShopAddress(),product);
        int quantity = model.getQuantityOfCertainProduct(shopViewState.getShopAddress(),product);
        if (quantity != 0)
        {
            products.add(new ProductsTableVM(product, lowestPrice, quantity));
        }

    }


    public void clear()
    {
        this.bagCounter.set("Bag (" + model.getQuantityOfItemsInBag()+")");
        update();
    }

    public void addCategory(String s)
    {
        pickedCategory.add(s);
    }

    public void removeCategory(String s)
    {
        pickedCategory.remove(s);
    }

    public ObservableList<ProductsTableVM> getProducts()
    {
        return products;
    }

    public ObservableList<String> getPickedCategory()
    {
        return pickedCategory;
    }

    public StringProperty getBagCounterProperty()
    {
        return bagCounter;
    }

    public void remove(Product product)
    {

        for (int i = 0; i < products.size(); i++)
        {
            if (products.get(i).getId() == product.getProductID())
            {
                products.remove(i);
                break;
            }
        }
    }

    public void chooseProduct(ProductsTableVM productsTableVM)
    {
        Product product = model.getProduct(shopViewState.getShopAddress(),productsTableVM.getId());
        productViewState.setProduct(product);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("StockUpdate"))
        {
            Platform.runLater(this::update);

        }
    }
}

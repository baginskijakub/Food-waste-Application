package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ItemsViewModel implements PropertyChangeListener
{
    private ClientUserModel model;
    private StringProperty errorProperty;
    private StringProperty productIdProperty;
    private StringProperty productNameProperty;
    private ObservableList<ItemsTableVM> items;
    private StringProperty bagCounter;
    private ProductViewState productViewState;
    private ShopViewState shopViewState;

    public ItemsViewModel(ClientUserModel model, ProductViewState productViewState, ShopViewState shopViewState)
    {
        this.model = model;
        this.shopViewState = shopViewState;
        this.productViewState = productViewState;
        errorProperty = new SimpleStringProperty();
        items = FXCollections.observableArrayList();
        bagCounter = new SimpleStringProperty();
        productIdProperty = new SimpleStringProperty();
        productNameProperty = new SimpleStringProperty();
        this.model.addListener(this);

        clear();

    }

    public void clear()
    {
        errorProperty.set("");
        bagCounter.set("Bag ("+model.getQuantityOfItemsInBag()+")");
        if (productViewState.getProduct() != null)
        {
            productIdProperty.set(String.valueOf(productViewState.getProduct().getProductID()));
            productNameProperty.set(productViewState.getProduct().getProductName());
            update(productViewState.getProduct());
        }
    }

    public void update(Product product)
    {
        if (shopViewState.getShopAddress() != null)
        {
            items.clear();
            for (Item item : model.getItemsByProduct(shopViewState.getShopAddress(),product))
            {
                add(item);
            }
        }
        }


    public void add(Item item)
    {

        items.add(new ItemsTableVM(item, -1));
    }

    public StringProperty getProductNameProperty()
    {
        return productNameProperty;
    }

    public StringProperty getProductIdProperty()
    {
        return productIdProperty;
    }

    public boolean addToBag(ItemsTableVM selectedItem)
    {
        String[] dateString = selectedItem.getDateProperty().get().split("-");

        Item item = model.getSpecificItem(shopViewState.getShopAddress(),new Date(Integer.parseInt(dateString[0]),
            Integer.parseInt(dateString[1]), Integer.parseInt(dateString[2])), productViewState.getProduct().getProductID());
        if (model.getOrder().getShopAddress() == null || model.getOrder().getShopAddress().equals(shopViewState.getShopAddress()))
        {
            model.addItemToOrder(shopViewState.getShopAddress(), item);
            bagCounter.set("Bag ("+model.getQuantityOfItemsInBag()+")");
        }


        return (item.getQuantity() - 1) == 0;
    }

    public StringProperty getErrorProperty()
    {
        return errorProperty;
    }

    public ObservableList<ItemsTableVM> getItems()
    {
        return items;
    }

    public StringProperty getBagCounterProperty()
    {
        return bagCounter;
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("StockUpdate"))
        {
            Platform.runLater(() -> {
                update(productViewState.getProduct());
            });

        }
    }
}

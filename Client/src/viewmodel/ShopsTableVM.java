package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Shop;

public class ShopsTableVM
{
    private StringProperty name;
    private StringProperty address;
    private IntegerProperty availableProducts;

    public ShopsTableVM(Shop shop)
    {
        name = new SimpleStringProperty(shop.getName());
        address = new SimpleStringProperty(shop.getAddress());
        availableProducts = new SimpleIntegerProperty(shop.getQuantityOfAllItemsInShop());
    }

    public StringProperty getName()
    {
        return name;
    }

    public StringProperty getAddress()
    {
        return address;
    }

    public IntegerProperty getAvailableProducts()
    {
        return availableProducts;
    }
}

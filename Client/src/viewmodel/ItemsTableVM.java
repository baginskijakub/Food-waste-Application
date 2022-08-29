package viewmodel;

import javafx.beans.property.*;
import model.Item;

public class ItemsTableVM
{
    private StringProperty name;
    private IntegerProperty id;
    private StringProperty date;
    private IntegerProperty quantity;
    private DoubleProperty price;

    public ItemsTableVM(Item item, int orderQuantity)
    {
        name = new SimpleStringProperty(item.getProduct().getProductName());
        id = new SimpleIntegerProperty(item.getProduct().getProductID());
        date = new SimpleStringProperty(item.getExpirationDate().toString());
        quantity = new SimpleIntegerProperty(orderQuantity == -1 ? item.getQuantity() : orderQuantity);
        price = new SimpleDoubleProperty(item.getCurrentPrice());
    }

    public StringProperty getNameProperty()
    {
        return name;
    }

    public StringProperty getDateProperty()
    {
        return date;
    }

    public IntegerProperty getQuantityProperty()
    {
        return quantity;
    }

    public DoubleProperty getPriceProperty()
    {
        return price;
    }

    public IntegerProperty getIdProperty()
    {
        return id;
    }
}

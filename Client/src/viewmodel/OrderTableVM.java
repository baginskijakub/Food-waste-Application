package viewmodel;

import javafx.beans.property.*;
import model.Item;
import model.Order;

public class OrderTableVM
{
    private StringProperty productName;
    private IntegerProperty quantity;
    private StringProperty date;
    private DoubleProperty price;

    public OrderTableVM(Item item, int quantity)
    {
        productName = new SimpleStringProperty(item.getProduct().getProductName());
        this.quantity = new SimpleIntegerProperty(quantity);
        date = new SimpleStringProperty(item.getExpirationDate().toString());
        price = new SimpleDoubleProperty(item.getCurrentPrice());
    }

    public String getProductName()
    {
        return productName.get();
    }

    public StringProperty productNameProperty()
    {
        return productName;
    }

    public int getQuantity()
    {
        return quantity.get();
    }

    public IntegerProperty quantityProperty()
    {
        return quantity;
    }

    public String getDate()
    {
        return date.get();
    }

    public StringProperty dateProperty()
    {
        return date;
    }

    public double getPrice()
    {
        return price.get();
    }

    public DoubleProperty priceProperty()
    {
        return price;
    }
}

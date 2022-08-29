package viewmodel;

import javafx.beans.property.*;
import model.Product;

public class ProductsTableVM
{
    private StringProperty name;
    private IntegerProperty id;
    private IntegerProperty quantity;
    private DoubleProperty lowestPrice;

    public ProductsTableVM(Product product , double lowestPrice, int quantity)
    {
        name = new SimpleStringProperty(product.getProductName());
        id = new SimpleIntegerProperty(product.getProductID());
        this.quantity = new SimpleIntegerProperty(quantity);
        this.lowestPrice = new SimpleDoubleProperty(lowestPrice);
    }

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public int getId()
    {
        return id.get();
    }

    public IntegerProperty idProperty()
    {
        return id;
    }

    public int getQuantity()
    {
        return quantity.get();
    }

    public IntegerProperty quantityProperty()
    {
        return quantity;
    }

    public Double getLowestPrice()
    {
        return lowestPrice.get();
    }

    public DoubleProperty lowestPriceProperty()
    {
        return lowestPrice;
    }
}

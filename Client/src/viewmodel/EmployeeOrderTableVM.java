package viewmodel;

import javafx.beans.property.*;
import model.Item;
import model.Product;

public class EmployeeOrderTableVM
{
  private StringProperty productName;
  private IntegerProperty quantity;
  private StringProperty date;
  private DoubleProperty price;

  public EmployeeOrderTableVM(Item item, int quantity)
  {
    this.productName = new SimpleStringProperty(item.getProduct().getProductName());
    this.quantity = new SimpleIntegerProperty(quantity);
    this.date = new SimpleStringProperty(item.getExpirationDate().toString());
    this.price = new SimpleDoubleProperty(item.getCurrentPrice());
  }

  public StringProperty getProductName()
  {
    return productName;
  }


  public IntegerProperty getQuantity()
  {
    return quantity;
  }


  public StringProperty getDate()
  {
    return date;
  }


  public DoubleProperty getPrice()
  {
    return price;
  }

}
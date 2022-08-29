package model;

import java.io.Serializable;

public class Item implements Serializable
{
  private double defaultPrice;
  private double currentPrice;
  private Date expirationDate;
  private Product product;
  private int quantity;

  public Item(Product product, double price, Date expirationDate, int quantity)
  {
    if(product == null || quantity < 0 || price < 0 || expirationDate == null)
    {
      throw new IllegalArgumentException("Enter correct data");
    }
    this.product = product;
    this.defaultPrice = price;
    this.currentPrice = price;
    this.expirationDate = expirationDate;
    this.quantity = quantity;
    updatePrice();
  }

  public synchronized double getDefaultPrice()
  {
    return defaultPrice;
  }

  public synchronized void setDefaultPrice(double defaultPrice)
  {
    this.defaultPrice = defaultPrice;
    updatePrice();
  }

  public synchronized void setCurrentPrice(double currentPrice)
  {
    this.currentPrice = currentPrice;
  }

  public synchronized void setQuantity(int quantity)
  {
    if (quantity < this.quantity)
    {
      while(this.quantity <= 0)
      {
        try
        {
          wait();
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }

    this.quantity = quantity;
    notifyAll();
  }

  public synchronized void setExpirationDate(Date expirationDate)
  {
    this.expirationDate = expirationDate;
  }

  public synchronized int getQuantity()
  {
    return quantity;
  }

  public synchronized Product getProduct()
  {
    return product;
  }

  public synchronized void updatePrice()
  {

    double temp = 0.5;
    if(expirationDate.daysBetween(new Date()) <= 10)
    {
      temp = (double) expirationDate.daysBetween(new Date()) /20;
    }

    this.currentPrice = Math.round((defaultPrice * (0.5 + temp)) * 100.0) / 100.0;
  }

  public synchronized double getCurrentPrice()
  {
    return currentPrice;
  }

  public synchronized Date getExpirationDate()
  {
    return expirationDate;
  }

  public synchronized boolean equals(Object obj)
  {
    if(!(obj instanceof Item))
    {
      return false;
    }
    Item other = (Item) obj;
    return this.expirationDate.equals(other.expirationDate) && product.equals(other.product);
  }

  @Override public synchronized int hashCode()
  {
    return product.getProductID() + expirationDate.toString().hashCode();
  }

  public String toString()
  {
    return product.getProductID() + " - " + product.getProductName() + ", date: " + expirationDate;
  }
}

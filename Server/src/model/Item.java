package model;

import java.io.Serializable;

/**
 * A class representing an Item. Has default price, current price, expiration date, product and quantity.
 */
public class Item implements Serializable
{
  private double defaultPrice;
  private double currentPrice;
  private Date expirationDate;
  private Product product;
  private int quantity;

  /**
   * A 4 argument constructor taking product, price, expiration date, quantity.
   *
   * @param product
   * @param price default price that will be dynamically adjusted and set to surrent price.
   * @param expirationDate
   * @param quantity
   *
   * @throws IllegalArgumentException if product = null, quantity < 0, price < 0 or expiration date = null
   */
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

  /**
   * Getter for defaultPrice
   * @return default price of type double
   */
  public synchronized double getDefaultPrice()
  {
    return defaultPrice;
  }

  /**
   * Setter for defaultPrice
   * @param defaultPrice
   */
  public synchronized void setDefaultPrice(double defaultPrice)
  {
    this.defaultPrice = defaultPrice;
    updatePrice();
  }

  /**
   * Setter for currentPrice
   * @param currentPrice
   */
  public synchronized void setCurrentPrice(double currentPrice)
  {
    this.currentPrice = currentPrice;
  }

  /**
   * Setter for quantity
   * @param quantity
   */
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

  /**
   * Setter for expiration date
   * @param expirationDate
   */
  public synchronized void setExpirationDate(Date expirationDate)
  {
    this.expirationDate = expirationDate;
  }

  /**
   * Getter for quantity
   * @return
   */
  public synchronized int getQuantity()
  {
    return quantity;
  }

  /**
   * Getter for product
   * @return
   */
  public synchronized Product getProduct()
  {
    return product;
  }

  /**
   * A method that updates current price according to how many days are there to expiration.
   */
  public synchronized void updatePrice()
  {
    double temp = 0.5;
    if(expirationDate.daysBetween(new Date()) <= 10)
    {
      temp = (double) expirationDate.daysBetween(new Date()) /20;
    }

    this.currentPrice = Math.round((defaultPrice * (0.5 + temp)) * 100.0) / 100.0;
  }

  /**
   * Getter for current Price
   * @return
   */
  public synchronized double getCurrentPrice()
  {
    return currentPrice;
  }

  /**
   * Getter for expiration date
   * @return
   */
  public synchronized Date getExpirationDate()
  {
    return expirationDate;
  }

  /**
   * Compares passed variable with this object.
   *
   * @param obj
   * @return true if passes object is the same as this object
   */
  public synchronized boolean equals(Object obj)
  {
    if(!(obj instanceof Item))
    {
      return false;
    }
    Item other = (Item) obj;
    return this.expirationDate.equals(other.expirationDate) && product.equals(other.product);
  }

  /**
   * A method hashing values
   * @return
   */
  @Override public synchronized int hashCode()
  {
    return product.getProductID() + expirationDate.toString().hashCode();
  }
}

package model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * A class representing an order
 */
public class Order implements Serializable
{
  private HashMap<Item, Integer> items;
  private Date date;
  private String addressLinePrimary;
  private String addressLineSecondary;
  private String city;
  private int postalCode;
  private boolean isCompleted;
  private double totalPrice;

  private String pickUpTime;
  private LocalTime localTime;

  private String cardName;
  private long cardNumber;
  private int expirationMonth;
  private int expirationYear;
  private int securityCode;
  private String email;
  private String shopAddress;

  /**
   * A constructor taking no arguments
   */
  public Order()
  {
    items = new HashMap<>();
    date = new Date();
    addressLinePrimary = null;
    addressLineSecondary = null;
    city = null;
    postalCode = 0;
    pickUpTime = null;
    email = null;
    cardName = null;
    cardNumber = 0;
    expirationMonth = 0;
    expirationYear = 0;
    securityCode = 0;
    shopAddress = null;
    localTime = LocalTime.now();
    isCompleted = false;
    totalPrice = 0;
  }

  /**
   * A method for getting current time as String
   * @return
   */
  public String toStringTime()
  {
    String s = "";

    if (localTime.getHour() < 10)
    {
      s += "0";
    }
    s += localTime.getHour() + ":";

    if (localTime.getMinute() < 10)
    {
      s += "0";
    }
    s += localTime.getMinute() + ":";
    if (localTime.getSecond() < 10)
    {
      s += "0";
    }
    s += localTime.getSecond();

    return s;
  }

  /**
   * A method for setting localTime variable to current time
   * @param timeString
   */
  public void setLocalTime(String timeString)
  {
    localTime = LocalTime.parse(timeString);
  }

  /**
   * A three argument constructor taking date, isCompleted and totalPrice.
   * @param date
   * @param isCompleted
   * @param totalPrice
   */
  public Order(Date date, boolean isCompleted, double totalPrice)
  {
    items = new HashMap<>();
    this.date = date;
    addressLinePrimary = null;
    addressLineSecondary = null;
    city = null;
    postalCode = 0;
    pickUpTime = null;
    email = null;
    cardName = null;
    cardNumber = 0;
    expirationMonth = 0;
    expirationYear = 0;
    securityCode = 0;
    shopAddress = null;
    localTime = null;
    this.isCompleted = isCompleted;
    this.totalPrice = totalPrice;
  }

  /**
   * A method that returns Items in from the order as a HashMap with Integer as key
   * @return
   */
  public HashMap<Item, Integer> getItems()
  {
    return items;
  }

  /**
   * A setter for shop address
   * @param shopAddress
   */
  public void setShopAddress(String shopAddress)
  {
    this.shopAddress = shopAddress;
  }

  /**
   * A getter for checking if order has been completed
   * @return
   */
  public boolean isCompleted()
  {
    return isCompleted;
  }

  /**
   * A setter for isCompleted
   * @param completed
   */
  public void setCompleted(boolean completed)
  {
    isCompleted = completed;
  }

  /**
   * A getter for shopAddress
   * @return
   */
  public String getShopAddress()
  {
    return shopAddress;
  }

  /**
   * A method that adds and item to an order
   * @param item
   */
  public void addItem(Item item)
  {
    if (items.containsKey(item))
    {

      items.put(item, items.get(item) + 1);
    }
    else
    {
      items.put(item, 1);
    }
    totalPrice = calculateTotalPrice();
  }

  /**
   * A method that adds and Item to order with certain quantity
   * @param item
   * @param quantity
   */
  public void addItem(Item item, int quantity)
  {

    items.put(item, quantity);
  }

  /**
   * A setter for email
   * @param email
   * @throws IllegalArgumentException if email does not contain '@'
   */
  public void setEmail(String email)
  {
    if (email == null || !email.contains("@"))
    {
      throw new IllegalArgumentException("Enter valid email");
    }
    this.email = email;
  }

  /**
   * A setter for address line 1, address line 2, city and postal code
   * @param addressLinePrimary
   * @param addressLineSecondary
   * @param city
   * @param postalCode
   */
  public void setDelivery(String addressLinePrimary,
      String addressLineSecondary, String city, int postalCode)
  {
    if (addressLinePrimary == null || city == null || postalCode < 1)
    {
      throw new IllegalArgumentException("Check input fields.");
    }
    else
    {
      this.addressLinePrimary = addressLinePrimary;
      this.addressLineSecondary = addressLineSecondary;
      this.city = city;
      this.postalCode = postalCode;
    }
  }

  /**
   * A method setting pickup time
   * @param pickUpTime
   */
  public void setDelivery(String pickUpTime)
  {
    if (pickUpTime == null)
    {
      throw new IllegalArgumentException("Please, select pick-up time.");
    }
    else
    {
      this.pickUpTime = pickUpTime;
    }
  }

  /**
   * A method setting payment information
   * @param cardName
   * @param cardNumber
   * @param expirationMonth
   * @param expirationYear
   * @param securityCode
   */
  public void setPayment(String cardName, long cardNumber, int expirationMonth,
      int expirationYear, int securityCode)
  {
    if (cardNumber < 0 || cardName == null || expirationMonth > 12
        || expirationMonth < 0 || expirationYear > 2030 || expirationYear < 2022
        || securityCode > 999 || securityCode < 0)
    {
      throw new IllegalArgumentException();
    }
    else
    {
      this.cardName = cardName;
      this.cardNumber = cardNumber;
      this.expirationMonth = expirationMonth;
      this.expirationYear = expirationYear;
      this.securityCode = securityCode;
    }
  }

  /**
   * Getter for hour
   * @return
   */
  public int getHour()
  {
    return localTime.getHour();
  }
  /**
   * Getter for minute
   * @return
   */
  public int getMinute()
  {
    return localTime.getMinute();
  }
  /**
   * Getter for second
   * @return
   */
  public int getSecond()
  {
    return localTime.getSecond();
  }
  /**
   * Getter for postal code
   * @return
   */
  public int getPostalCode()
  {
    return postalCode;
  }
  /**
   * Getter for first address line
   * @return
   */
  public String getAddressLinePrimary()
  {
    return addressLinePrimary;
  }
  /**
   * Getter for second address line
   * @return
   */
  public String getAddressLineSecondary()
  {
    return addressLineSecondary;
  }
  /**
   * Getter for city
   * @return
   */
  public String getCity()
  {
    return city;
  }
  /**
   * Getter for email
   * @return
   */
  public String getEmail()
  {
    return email;
  }
  /**
   * Getter for pickup time
   * @return
   */
  public String getPickUpTime()
  {
    return pickUpTime;
  }
  /**
   * A method removing an Item from order
   * @return
   */
  public void removeItem(Item item)
  {
    if (items.get(item) == 1)
    {
      items.remove(item);
    }
    else
    {
      items.put(item, items.get(item) - 1);
    }

    totalPrice = calculateTotalPrice();
  }
  /**
   * Getter for date
   * @return
   */
  public Date getDate()
  {
    return date;
  }
  /**
   * Getter for total price
   * @return
   */
  public double getTotalPrice()
  {
    return totalPrice;
  }
  /**
   * A method calculating total price
   * @return
   */
  private double calculateTotalPrice()
  {
    double sum = 0;

    for (Map.Entry<Item, Integer> entry : items.entrySet())
    {
      sum += entry.getValue() * entry.getKey().getCurrentPrice();
    }
    return sum;
  }
  /**
   * Getter for quantity of all items in order
   * @return
   */
  public int getQuantityOfItemsInOrder()
  {
    int sum = 0;

    for (Map.Entry<Item, Integer> entry : items.entrySet())
    {
      sum += entry.getValue();
    }
    return sum;
  }
  /**
   * Getter for order description
   * @return
   */
  public String getOrderDescription()
  {
    if (addressLinePrimary == null)
    {
      return "Pick-up time: " + pickUpTime;
    }
    else
    {
      return "Address: " + getAddressLinePrimary() + ", "
          + getAddressLineSecondary() + ", " + getPostalCode() + " " + getCity()
          + ", email: " + getEmail();
    }
  }
  /**
   * Compares passed variable with this object.
   *
   * @param obj
   * @return true if passes object is the same as this object
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Order))
    {
      return false;
    }
    Order other = (Order) obj;
    return this.items.equals(other.items) && this.date.equals(other.date);
  }
}

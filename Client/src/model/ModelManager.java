package model;
import mediator.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class implementing Model interface, connecting all classes in the Model package, executing all important actions related
 * to the system, usually by delegating them to Client class that will call them on server side
 */
public class ModelManager implements Model, PropertyChangeListener
{

  private PropertyChangeSupport property;
  private Client client;
  private Order order;
  /**
   * O argument constructor, creating new order, creating class related to RMI connection and adding itself as a listener to this class,
   * establishing itself as a subject in observer pattern
   */
  public ModelManager() throws ClassNotFoundException, IOException
  {
    client = new Client();
    property = new PropertyChangeSupport(this);
    client.addListener(this);
    order = new Order();
  }

  @Override public ArrayList<Product> getAllProducts(String address)
  {
    return client.getAllProducts(address);
  }

  @Override public ArrayList<Item> getAllItemsFromShop(String address)
  {
    return client.getAllItemsFromShop(address);
  }

  @Override public void updateItem(String shopAddress, String previousDate,
      int previousNumber, Date date, ArrayList<Category> categories,
      long newNumber, String newName, double newPrice, int newQuantity)
  {
    client.updateItem(shopAddress, previousDate, previousNumber, date, categories, newNumber, newName, newPrice, newQuantity);
  }

  @Override public ArrayList<Product> getProductsByCategory(String address,
      ArrayList<String> categories)
  {
    return client.getProductsByCategory(address, categories);
  }

  @Override public Order getOrder()
  {
    return order;
  }

  @Override public void clearBag()
  {
    for(Map.Entry<Item, Integer> entry : order.getItems().entrySet())
    {
      client.removeItemFromOrder(order.getShopAddress(), entry.getKey(), entry.getValue());
    }
  }

  @Override public void removeItemFromBag(Item item)
  {
    order.removeItem(item);
    client.removeItemFromOrder(order.getShopAddress(), item, 1);
    if (order.getItems().size() == 0)
    {
      order.setShopAddress(null);
    }
  }

  @Override public int getQuantityOfItemsInBag()
  {
    return order.getQuantityOfItemsInOrder();
  }

  @Override public void removeItem(String address, Date expirationDate,
      int productID)
  {

    client.removeItem(address, expirationDate, productID);

  }

  @Override public void completeOrder(String address, Order order)
  {
    client.completeOrder(address, order);
    this.order = new Order();
  }

  @Override public void addItemToOrder(String address,Item item)
  {

    if (order.getItems().size() == 0)
    {
      order.setShopAddress(address);
    }
    order.addItem(item);
    client.addItemToOrder(address,item);


  }

  @Override public void removeItemFromOrder(String address, Item item, int quantityOfItem)
  {
    order.removeItem(item);
    client.removeItemFromOrder(address, item,quantityOfItem);
  }

  @Override public void setDelivery(String pickUpTime)
  {
    order.setDelivery(pickUpTime);
  }

  @Override public void setDelivery(String addressLinePrimary,
      String addressLineSecondary, String city, int postalCode)
  {
    order.setDelivery(addressLinePrimary, addressLineSecondary, city, postalCode);
  }

  @Override public void setEmail(String email)
  {
    order.setEmail(email);
  }

  @Override public void setPayment(String cardName, long cardNumber,
      int expirationMonth, int expirationYear, int securityCode)
  {
    order.setPayment(cardName, cardNumber, expirationMonth, expirationYear, securityCode);
  }

  @Override public Product getProduct(String address, int productNumber)
  {
    return client.getProduct(address, productNumber);
  }

  @Override public ArrayList<Item> getItemsByProduct(String address, Product product)
  {
    return client.getItemsByProduct(address, product);
  }

  @Override public double getLowestPriceOfProduct(String address, Product product)
  {
    return client.getLowestPriceOfProduct(address, product);
  }

  @Override public int getQuantityOfCertainProduct(String address, Product product)
  {
    return client.getQuantityOfCertainProduct(address, product);
  }

  @Override public Item getSpecificItem(String address, Date expirationDate, int productId)
  {
    return client.getSpecificItem(address, expirationDate, productId);
  }

  @Override public ArrayList<Shop> getAllShops()
  {
    return client.getAllShops();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  @Override public void addUser(String username, String password)
  {
    client.addUser(username, password);
  }

  @Override public User getUser(String username, String password)
  {
    return client.getUser(username,password);
  }

  @Override public void addItem(String address, String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories)
  {
    client.addItem(address, productName, productID, price, expirationDate, quantity,
        categories);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    property.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
  }

  @Override public Order getOrder(String shopAddress ,int day, int month, int year, int hour,
      int minute, int second, String deliveryOptions)
  {
    return client.getOrder(shopAddress, day, month, year, hour, minute, second, deliveryOptions);
  }

  @Override public ArrayList<Order> getOrderList(String shopAddress)
  {
    return client.getOrderList(shopAddress);
  }

  @Override public void removeOrder(String shopAddress, int day, int month, int year, int hour,
      int minute, int second, String deliveryOptions)
  {
    client.removeOrder(shopAddress, day, month, year, hour, minute, second, deliveryOptions);
  }
}

package mediator;

import model.*;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements RemoteModel, PropertyChangeListener
{
  private Model model;
  private PropertyChangeHandler<Item, String> property;

  public Server(Model model) throws IOException
  {

    this.model = model;
    property = new PropertyChangeHandler<>(this,true);
    this.model.addListener(this);
    startRegistry();
    startServer();
  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this,0);
    Naming.rebind("foodWaste", this);
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("dfafdaf");
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    property.firePropertyChange(evt.getPropertyName(), null, "New stock");
  }

  @Override public Product getProduct(String address, int productNumber) throws RemoteException
  {
    return model.getProduct(address, productNumber);
  }

  @Override public void addItemToOrder(String address, Item item) throws RemoteException
  {
    model.addItemToOrder(address,item);
  }

  @Override public void completeOrder(String address, Order order) throws RemoteException
  {
    model.completeOrder(address, order);
  }

  @Override public void removeItemFromOrder(String address, Item item,int quantityOfItem) throws RemoteException
  {
    model.removeItemFromOrder(address, item,quantityOfItem);
  }

  @Override public ArrayList<Item> getItemsByProduct(String address, Product product)
      throws RemoteException
  {
    return model.getItemsByProduct(address, product);
  }

  @Override public ArrayList<Product> getAllProducts(String address) throws RemoteException
  {
    return model.getAllProducts(address);
  }

  @Override public ArrayList<Product> getProductsByCategory(
      String address, ArrayList<String> categories) throws RemoteException
  {
    return model.getProductsByCategory(address, categories);
  }

  @Override public double getLowestPriceOfProduct(String address, Product product)
      throws RemoteException
  {
    return model.getLowestPriceOfProduct(address, product);
  }

  @Override public int getQuantityOfCertainProduct(String address, Product product)
      throws RemoteException
  {

    return model.getQuantityOfCertainProduct(address, product);

  }

  @Override public Item getSpecificItem(String address, Date expirationDate, int productId)
      throws RemoteException
  {
    return model.getSpecificItem(address, expirationDate,productId);
  }

  @Override public void addUser(String username, String password)
  {
    model.addUser(username, password);
  }

  @Override public User getUser(String username, String password)
  {
    return model.getUser(username, password);
  }

  @Override public void addItem(String address, String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories)
  {
    model.addItem(address,productName, productID, price, expirationDate, quantity, categories);
  }

  @Override public ArrayList<Shop> getAllShops() throws RemoteException
  {
    return model.getAllShops();
  }

  @Override public boolean addListener(GeneralListener<Item, String> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Item, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener,propertyNames);
  }

  @Override public Order getOrder(String shopAddress, int day, int month,
      int year, int hour, int minute, int second, String deliveryOptions)
  {
    return model.getOrder(shopAddress, day, month, year, hour, minute, second, deliveryOptions);
  }

  @Override public void removeOrder(String shopAddress, int day, int month,
      int year, int hour, int minute, int second, String deliveryOptions)
  {

    model.removeOrder(shopAddress, day, month, year, hour, minute, second, deliveryOptions);
  }

  @Override public ArrayList<Order> getOrderList(String shopAddress)
  {

    return model.getOrderList(shopAddress);
  }

  @Override public void removeItem(String address, Date expirationDate,
      int productID) throws RemoteException
  {
    model.removeItem(address, expirationDate, productID);
  }

  @Override public ArrayList<Item> getAllItemsFromShop(String address)
      throws RemoteException
  {
    return model.getAllItemsFromShop(address);
  }

  @Override public void updateItem(String shopAddress, String previousDate,
      int previousNumber, Date date, ArrayList<Category> categories,
      long newNumber, String newName, double newPrice, int newQuantity)
      throws RemoteException
  {
    model.updateItem(shopAddress, previousDate, previousNumber, date, categories, newNumber, newName, newPrice, newQuantity);
  }

}

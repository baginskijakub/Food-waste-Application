package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class  Client implements RemoteListener<Item, String>, ModelEmployee, ModelUser
{
    private RemoteModel server;
    private PropertyChangeSupport property;

    public Client() throws IOException
    {
      try
      {
        server = (RemoteModel) Naming.lookup("rmi://localhost:1099/foodWaste");
      }
      catch (Exception e)
      {

        e.printStackTrace();
      }
      UnicastRemoteObject.exportObject(this, 0);
      server.addListener(this);
      property = new PropertyChangeSupport(this);
    }



   @Override public void removeItemFromOrder(String address, Item item,int quantityOfItem)
    {
      try
      {
        server.removeItemFromOrder(address, item, quantityOfItem);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

  @Override public void removeItem(String address, Date expirationDate,
      int productID)
  {
      try
      {
        server.removeItem(address, expirationDate, productID);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
  }

  @Override public Product getProduct(String address, int productNumber)
  {
    try
    {
      return server.getProduct(address, productNumber);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public ArrayList<Item> getItemsByProduct(String address,
      Product product)
  {
    try
    {
      return server.getItemsByProduct(address, product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public double getLowestPriceOfProduct(String address,
      Product product)
  {
    try
    {
      return server.getLowestPriceOfProduct(address, product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return 0;
    }
  }

  @Override public int getQuantityOfCertainProduct(String address,
      Product product)
  {
    try
    {
      return server.getQuantityOfCertainProduct(address, product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return 0;
    }
  }

  @Override public Item getSpecificItem(String address, Date expirationDate,
      int productId)
  {
    try
    {
      return server.getSpecificItem(address, expirationDate, productId);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public ArrayList<Shop> getAllShops()
  {
    try
    {
      return server.getAllShops();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public void addItemToOrder(String address, Item item)
    {
      try
      {
        server.addItemToOrder(address,item);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

  @Override public ArrayList<Product> getAllProducts(String address)
  {
    try
    {
      return server.getAllProducts(address);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public ArrayList<Item> getAllItemsFromShop(String address)
  {
    try
    {
      return server.getAllItemsFromShop(address);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public void updateItem(String shopAddress, String previousDate,
      int previousNumber, Date date, ArrayList<Category> categories,
      long newNumber, String newName, double newPrice, int newQuantity)
  {
    try
    {
      server.updateItem(shopAddress, previousDate, previousNumber, date, categories, newNumber, newName, newPrice, newQuantity);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public ArrayList<Product> getProductsByCategory(String address,
      ArrayList<String> categories)
  {
    try
    {
      return server.getProductsByCategory(address, categories);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }


  public void completeOrder(String address, Order order)
    {
      try
      {
        server.completeOrder(address, order);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

    @Override public void propertyChange(ObserverEvent<Item, String> event)
        throws RemoteException
    {
      property.firePropertyChange(event.getPropertyName(),event.getValue1(),event.getValue2());
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
    try
    {
      server.addUser(username,password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public User getUser(String username, String password)
  {
    try
    {
      return server.getUser(username,password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public void addItem(String address, String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories)
  {
    try
    {
      server.addItem(address, productName, productID, price, expirationDate, quantity, categories);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }


  @Override public Order getOrder(String shopAddress, int day, int month, int year, int hour,
      int minute, int second, String addressLinePrimary)
  {
    try
    {
      return server.getOrder(shopAddress, day, month, year, hour, minute, second, addressLinePrimary);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public ArrayList<Order> getOrderList(String shopAddress)
  {
    try
    {
      return server.getOrderList(shopAddress);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public void removeOrder(String shopAddress, int day, int month, int year, int hour,
      int minute, int second, String addressLinePrimary)
  {
    try
    {
      server.removeOrder(shopAddress, day, month, year, hour, minute, second, addressLinePrimary);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }




}




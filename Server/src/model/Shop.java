package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a shop having name, address, productlist, itemlist and orderlist
 */
public class Shop implements Serializable
{
  private String name;
  private String address;
  private ProductList productList;
  private ItemList itemList;
  private ArrayList<Order> orderList;

  /**
   * A 4 argument constructor taking name, address, item list and product list.
   * @param name
   * @param address
   * @param itemList
   * @param productList
   */
  public Shop(String name, String address, ItemList itemList, ProductList productList)
  {
   this.address = address;
   this.name = name;
   this.productList = productList;
   this.itemList = itemList;
   this.orderList = new ArrayList<>();


  }

  /**
   * A getter for shop name
   * @return
   */
  public String getName()
  {
    return name;
  }

  /**
   * A setter for order list
   * @param orderList
   */
  public void setOrderList(ArrayList<Order> orderList)
  {
    this.orderList = orderList;
  }

  /**
   * A getter for shop address
   * @return
   */
  public String getAddress()
  {
    return address;
  }

  /**
   * A getter for product list
   * @return
   */
  public ProductList getProductList()
  {
    return productList;
  }
  /**
   * A getter for item list
   * @return
   */
  public ItemList getItemList()
  {
    return itemList;
  }
  /**
   * A getter for item list
   * @return ArrayList od type Item
   */
  public ArrayList<Item> getAllItems()
  {
    return itemList.getItems();
  }

  /**
   * A getter for product list
   * @return ArrayList od type Product
   */
  public ArrayList<Product> getAllProducts()
  {
    return productList.getAllProducts();
  }
  /**
   * A getter for products having one of passed categories
   * @return ArrayList od type Product
   */
  public ArrayList<Product> getProductsByCategory(
      ArrayList<String> categories)
  {
    return productList.getProductsByCategory(categories);
  }
  /**
   * A getter for product with a given product ID
   * @return Product with a given id
   */
  public Product getProduct(int productNumber)
  {
    return productList.getProduct(productNumber);
  }
  /**
   * A getter for items being a certain type of product
   * @return ArrayList od type Item
   */
  public ArrayList<Item> getItemsByProduct(Product product)
  {
    return itemList.getItems(product);
  }
  /**
   * A method returning the lowest price of an Item being a certain type of product
   * @return lowest price
   */
  public double getLowestPriceOfProduct(Product product)
  {
    ArrayList<Item> items = itemList.getItems(product);
    double lowestSum;
    if (items.size() != 0)
    {
      lowestSum = items.get(0).getCurrentPrice();

      for (int i = 0; i < items.size(); i++)
      {
        if (items.get(i).getCurrentPrice() < lowestSum)
        {
          lowestSum = items.get(i).getCurrentPrice();
        }
      }
    }
    else
    {
      lowestSum = 0;
    }
    return lowestSum;
  }

  /**
   * A getter for quantity of a Product
   * @param product
   * @return  quantity of all items being a certain type of product summed up
   */
  public int getQuantityOfCertainProduct(Product product)
  {

    int sum = 0;
    ArrayList<Item> items = itemList.getItems(product);
    for (int i = 0; i < items.size(); i++)
    {
      sum += items.get(i).getQuantity();
    }
    return sum;
  }

  /**
   * A getter for a specific Item having a certain product ID and expiration date
   * @param expirationDate
   * @param productId
   * @return
   */
  public Item getSpecificItem(Date expirationDate, int productId)
  {
    ArrayList<Item> items = itemList.getItems(getProduct(productId));
    for (Item item : items)
    {
      if (item.getExpirationDate().equals(expirationDate))
      {
        return item;
      }
    }
    return null;
  }

  /**
   * A method adding an Item that is type of a certain product that does not exist
   * @param item
   * @param product
   */
  public void addItem(Item item, Product product)
  {
    itemList.addItem(item);
    productList.addProduct(product);
  }
  /**
   * A method adding an Item that is type of a certain product that does exist
   * @param item
   */
  public void addItem(Item item)
  {
    itemList.addItem(item);
  }
  /**
   * A getter for quantity of all item in shop
   *
   */
  public int getQuantityOfAllItemsInShop()
  {
    return itemList.getQuantityOfAllItems();
  }

  /**
   * A method adding an order to list of orders
   * @param order
   */
  public void addOrder(Order order)
  {
    orderList.add(order);
  }

  /**
   * A method getting an order from order list
   * @param day
   * @param month
   * @param year
   * @param hour
   * @param minute
   * @param second
   * @param addressLinePrimary
   * @return
   */
  public Order getOrder(int day, int month, int year, int hour,
      int minute, int second, String addressLinePrimary)
  {
    Date date = new Date();
    date.set(day, month, year);
    for(Order o : orderList)
    {
      if(o.getDate().equals(date) && hour == o.getHour() && minute == o.getMinute() && second == o.getSecond() && o.getOrderDescription().equals(o.getOrderDescription()))
      {
        return o;
      }
    }
    throw new IllegalArgumentException("Order does not exist.");
  }

  /**
   * A method removing an order from order list
   * @param day
   * @param month
   * @param year
   * @param hour
   * @param minute
   * @param second
   * @param addressLinePrimary
   */
  public void removeOrder(int day, int month, int year, int hour,
      int minute, int second, String addressLinePrimary)
  {
    Date date = new Date();
    date.set(day, month, year);
    for(Order o : orderList)
    {
      if(o.getDate().equals(date) && hour == o.getHour() && minute == o.getMinute() && second == o.getSecond() && o.getOrderDescription().equals(o.getOrderDescription()))
      {
        o.setCompleted(true);
      }
    }
  }

  /**
   * A getter for orderList
   * @return
   */
  public ArrayList<Order> getOrderList()
  {
    return orderList;
  }
}

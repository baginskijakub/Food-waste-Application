package model;

import java.util.ArrayList;

/**
 * A class representing a list of shops
 */
public class ShopList
{
  private ArrayList<Shop> list;

  /**
   * A zero argument constructor creating a new list of shops
   */
  public ShopList()
  {
    this.list = new ArrayList<>();
  }

  /**
   * A getter for all shops
   * @return ArrayList of type shop
   */
  public ArrayList<Shop> getShops()
  {
    return list;
  }

  /**
   * A getter for a shop having passed address as an instance variable
   * @param address
   * @return
   */
  public Shop getShop(String address)
  {
    for (Shop s : list)
    {
      if (s.getAddress().equals(address))
      {
        return s;
      }
    }
    return null;
  }

  /**
   * A method adding a shop to the list
   * @param shop
   */
  public void addShop(Shop shop)
  {
    list.add(shop);
  }

  /**
   * A getter for all products in a given shop
   * @param address
   * @return
   */
  public ArrayList<Product> getAllProducts(String address)
  {
    return getShop(address).getAllProducts();
  }

  /**
   * A getter for a product in a given shop with a given product number
   * @param address
   * @param productNumber
   * @return
   */
  public Product getProduct(String address, int productNumber)
  {
    return getShop(address).getProduct(productNumber);
  }

  /**
   * A getter for items in a given shop being a certain type of product
   * @param address
   * @param product
   * @return ArrayList of type Item
   */
  public ArrayList<Item> getItemsByProduct(String address, Product product)
  {
    return getShop(address).getItemsByProduct(product);
  }

  /**
   * A getter for the lowest price of a product in a given shop
   * @param address
   * @param product
   * @return
   */
  public double getLowestPriceOfProduct(String address, Product product)
  {
    return getShop(address).getLowestPriceOfProduct(product);
  }

  /**
   * A getter for quantity of a certain product in a given shop
   * @param address
   * @param product
   * @return
   */
  public int getQuantityOfCertainProduct(String address, Product product)
  {

    return getShop(address).getQuantityOfCertainProduct(product);
  }

  /**
   * A getter for a specific item in a given shop having a certain product Id and expiration date
   * @param address
   * @param expirationDate
   * @param productId
   * @return
   */
  public Item getSpecificItem(String address, Date expirationDate,
      int productId)
  {

    return getShop(address).getSpecificItem(expirationDate, productId);
  }

  /**
   * A getter for products from a shop having one or more of passed categories
   * @param address
   * @param categories
   * @return
   */
  public ArrayList<Product> getProductsByCategory(String address,
      ArrayList<String> categories)
  {
    return getShop(address).getProductsByCategory(categories);
  }

  /**
   * A method adding item not being an existing type of product to a given shop
   * @param address
   * @param item
   * @param product
   */
  public void addItem(String address, Item item, Product product)
  {
    getShop(address).addItem(item, product);
  }
  /**
   * A method adding item being an existing type of product to a given shop
   * @param address
   * @param item
   */
  public void addItem(String address, Item item)
  {
    getShop(address).addItem(item);
  }
}

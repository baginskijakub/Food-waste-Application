package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface containing methods related functionalities to of the system created for the shop's customers
 */
public interface ModelUser extends UnnamedPropertyChangeSubject
{
  /**
   * Method returning all products from a certain shop
   *
   * @param address String containing address of the shop
   * @return list of all products located at certain shop
   */
  ArrayList<Product> getAllProducts(String address);
  /**
   * Method returning list of all products from a certain shop filtered by certain set of categories
   *
   * @param address    String containing address of the shop
   * @param categories list of categories from which the product should have at least one
   * @return list products filtered by categories and located at certain shop
   */
  ArrayList<Product> getProductsByCategory(String address,
      ArrayList<String> categories);
  /**
   * Method responsible for setting status of the order as completed and saving it into database
   *
   * @param address String containing address of the shop where order was created
   * @param order   order which status should be changed and saved
   */
  void completeOrder(String address, Order order);
  /**
   * Method updating stock of the shop after adding new item to order
   *
   * @param address String containing address of the shop where order is being completed
   * @param item    item that was added to order
   */
  void addItemToOrder(String address, Item item);
  /**
   * Method updating stock of the shop after removing item from order
   *
   * @param address        String containing address of the shop where order is being completed
   * @param item           item that was removed from order
   * @param quantityOfItem number representing how many items should be returned to the stock
   */
  void removeItemFromOrder(String address, Item item, int quantityOfItem);
  /**
   * Method return certain product at certain shop
   *
   * @param address       String containing address of the shop where product is located
   * @param productNumber number representing product number
   * @return product located at shops at passed address with passed product number
   */
  Product getProduct(String address, int productNumber);
  /**
   * Method returning all items of certain product located at certain shop
   *
   * @param address String containing address of the shop where items are located
   * @param product product object representing "type" of item
   * @return list of items being passed product located at passed shop address
   */
  ArrayList<Item> getItemsByProduct(String address, Product product);
  /**
   * Method returning the lowest price from items that are certain product
   *
   * @param address String containing address of the shop where items are located
   * @param product product object representing "type" of item
   * @return double being the lowest price from list of all prices of items that are "type" of passed product
   */
  double getLowestPriceOfProduct(String address, Product product);
  /**
   * Method returning the sum of quantity from items that are certain product
   *
   * @param address String containing address of the shop where items are located
   * @param product product object representing "type" of item
   * @return number being the sum of quantity of all items that are "type" of passed product
   */
  int getQuantityOfCertainProduct(String address, Product product);
  /**
   * Getter for specific item in the system (unique by combination of shop address, product id and expiration date)
   *
   * @param address        shop address where item is located
   * @param expirationDate expiration date of item
   * @param productId      number of product of which type is item
   * @return specific item matching passed values
   */
  Item getSpecificItem(String address, Date expirationDate, int productId);
  /**
   * Method returning all shops in the system
   *
   * @return list of all shops in the system
   */
  ArrayList<Shop> getAllShops();

}

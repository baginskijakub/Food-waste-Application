package model;

import java.util.ArrayList;

/**
 * Interface containing methods related functionalities to of the system created for the shop's employees
 */
public interface ModelEmployee
{
  /**
   * Method adding employee to the list of employees
   * @param username username of new employee
   * @param password password of new employee
   */
  void addUser(String username, String password);
  /**
   * Method getting certain employee from the list of employees in the system
   * @param username String with username of employee
   * @param password String with password of employee
   * @return User object with user matching passed credentials
   */
  User getUser(String username, String password);
  /**
   * Method adding new item to the system, saving it into database
   * @param address address of shop where item should be added
   * @param productName name of product of which type is item
   * @param productID number of product of which type is item
   * @param price price of an item (without discount)
   * @param expirationDate expiration date of an item
   * @param quantity quantity of an item
   * @param categories list of categories to which item belongs
   */
  void addItem(String address, String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories);

  /**
   * Getter for specific item in the system (unique by combination of shop address, product id and expiration date)
   * @param address shop address where item is located
   * @param expirationDate expiration date of item
   * @param productId number of product of which type is item
   * @return specific item matching passed values
   */
  Item getSpecificItem(String address, Date expirationDate, int productId);

  /**
   * Method returning certain order from a system
   * @param shopAddress address at which shop order was created
   * @param day day at which order was created
   * @param month month at which order was created
   * @param year year at which order was created
   * @param hour hour at which order was created
   * @param minute minute at which order was created
   * @param second second at which order was created
   * @param deliveryOptions delivery option that was chosen during creation of an order
   * @return certain order matching passed parameters
   */
  Order getOrder(String shopAddress, int day, int month, int year, int hour, int minute, int second, String deliveryOptions);
  /**
   * Getter for all orders made at certain shop
   * @param shopAddress shop address where orders where created
   * @return all orders that were created at certain shop
   */
  ArrayList<Order> getOrderList(String shopAddress);
  /**
   * Method "removing" order from system (changing its status to completed), saving the changes to database
   * @param shopAddress address at which shop order was created
   * @param day day at which order was created
   * @param month month at which order was created
   * @param year year at which order was created
   * @param hour hour at which order was created
   * @param minute minute at which order was created
   * @param second second at which order was created
   * @param deliveryOptions delivery option that was chosen during creation of an order
   */
  void removeOrder(String shopAddress, int day, int month, int year, int hour, int minute, int second, String deliveryOptions);
  /**
   * Method removing item from system (decreasing its quantity by 1), saving the changes to database
   * @param address shop address where item is located
   * @param expirationDate expiration date of item
   * @param productID number of product of which type is item
   */
  void removeItem(String address, Date expirationDate, int productID);
  /**
   * Getter for all items at certain shop
   * @param address shop address where items are located
   * @return all items matching passed address
   */
  ArrayList<Item> getAllItemsFromShop(String address);
  /**
   * Method updating item in a shop, saving changes to database
   * @param shopAddress address of shop at which item is located
   * @param previousDate old expiration date of an item
   * @param previousNumber new number of a product of which type is item
   * @param date new expiration date of an item
   * @param categories new set of categories of an item
   * @param newNumber new number of a product of which type is item
   * @param newName new name of a product of which type is item
   * @param newPrice new price of an item (before discount)
   * @param newQuantity new quantity of an item
   */
  void updateItem(String shopAddress, String previousDate, int previousNumber,
      Date date, ArrayList<Category> categories, long newNumber, String newName, double newPrice,
      int newQuantity);
}

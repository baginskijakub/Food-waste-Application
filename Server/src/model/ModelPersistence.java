package model;

import java.util.ArrayList;

/**
 * Interface containing methods related to persistence of the system (in this version, persistence is achieved with use of database)
 */
public interface ModelPersistence
{
  /**
   * Method saving new order
   *
   * @param address shop address where order was made
   * @param order   order that should be saved
   */
  void save(String address, Order order);
  /**
   * Method saving new item
   *
   * @param address shop address where order was made
   * @param item    item that should be saved
   */
  void save(String address, Item item);
  /**
   * Method saving new product
   *
   * @param product product that should be saved
   */
  void save(Product product);
  /**
   * Method saving updates to already existing item (only used when updating values of item not related to the product)
   *
   * @param address shop address where the item is located at
   * @param item    item which parameters are updated and needs to be saved
   */
  void update(String address, Item item);
  /**
   * Method saving updates to already existing item (method used when drastic changes are made to item, for example - new product number assigned to it)
   *
   * @param address              shop address where the item is located at
   * @param item                 item which parameters are updated and needs to be saved
   * @param expirationDateFromDb previous expiration date of an item (allows locating item in a stored data)
   * @param isProductChanged     indicator whether some property related to product was changed in an item
   * @param productNumberFromDb  previous product number of a product of which type is item (allows locating item in a stored data)
   */
  void update(String address, Item item, Date expirationDateFromDb,
      int productNumberFromDb, boolean isProductChanged);
  /**
   * Method saving order after is has been completed (saving its new status)
   *
   * @param order order that has to have its new status saved
   */
  void updateCompletedOrder(Order order);
  /**
   * Method loading data about users (employees)
   *
   * @return UserList containing all existing users
   */
  UserList loadUsers();
  /**
   * Method loading data about shops (with their items and products)
   *
   * @return ShopList with complete data about existing shops in the system
   */
  ShopList loadShops();
  /**
   * Method loading data about orders from certain shop
   *
   * @param address shop address where orders were created
   * @return ArrayList containing orders made in shop matching passed value
   */
  ArrayList<Order> loadOrdersFromShop(String address);

}

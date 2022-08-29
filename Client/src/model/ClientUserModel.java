package model;

/**
 * Interface containing methods only running on client side (are not used on server side), as their purpose is handling current order of the client (it is handled by client and only sent to server after it is finalized or to update the stock for other users).
 * Also extending ModelUser interface
 */
public interface ClientUserModel extends ModelUser
{
  /**
   * Setter for delivery options of an order when pick-up delivery type is chosen
   * @param pickUpTime String containing time at which order will be picked up by client
   */
  void setDelivery(String pickUpTime);
  /**
   * Setter for delivery options of an order when "delivery to address" is chosen as delivery type
   * @param addressLinePrimary String containing primary line of client's address
   * @param addressLineSecondary String containing secondary line of client's address (optional)
   * @param city String containing name of the city
   * @param postalCode number representing client's postal code
   */
  void setDelivery(String addressLinePrimary, String addressLineSecondary, String city, int postalCode);
  /**
   * Setter for email in order
   * @param email String containing client's email
   */
  void setEmail(String email);
  /**
   * Method validating client's payment info (it is placeholder in this version of the system, to showcase the flow of an app)
   * @param cardName name of the owner of the card
   * @param cardNumber number of the client's card
   * @param expirationMonth number representing month in which the card expires
   * @param expirationYear number representing year in which the card expires
   * @param securityCode 3 digits' number on the back of the card
   */
  void setPayment(String cardName, long cardNumber, int expirationMonth, int expirationYear, int securityCode);
  /**
   * Getter for current client's order
   * @return client's current order
   */
  Order getOrder();
  /**
   * Method running when client suddenly closes the app, clears the bag of a client, ensuring that all items return to the stock by calling server
   */
  void clearBag();
  /**
   * Method removing item from a client's current order, calling the server to update stock
   * @param item item object that will be removed from an order
   */
  void removeItemFromBag(Item item);
  /**
   * Method returning sum of items' quantities in a current client's order
   * @return number representing total sum of quantity of items in an order
   */
  int getQuantityOfItemsInBag();
}

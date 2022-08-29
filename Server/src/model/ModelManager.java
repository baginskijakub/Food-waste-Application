package model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Class implementing Model interface, connecting all classes in the Model package, executing all important actions related
 * to the system, "brain" of the whole application
 */
public class ModelManager implements Model
{
  private PropertyChangeSupport property;
  private ShopList shopList;
  private ModelPersistence modelPersistence;
  private UserList userList;

  /**
   * O argument constructor, loading information from database into instance variables that will be used while displaying data to client,
   * establishing this class as subject in observer pattern
   * @throws ClassNotFoundException if the class tries to load in a class that does not exist in a system
   */

  public ModelManager() throws ClassNotFoundException
  {
    this.modelPersistence = new ModelDatabase(this);

    this.shopList = modelPersistence.loadShops();

    for (Shop shop : getAllShops())
    {
      shop.setOrderList(modelPersistence.loadOrdersFromShop(shop.getAddress()));

    }

    userList = modelPersistence.loadUsers();
    property = new PropertyChangeSupport(this);
  }

  @Override public ArrayList<Product> getAllProducts(String address)
  {
    return shopList.getAllProducts(address);
  }

  @Override public ArrayList<Product> getProductsByCategory(String address,
      ArrayList<String> categories)
  {
    return shopList.getProductsByCategory(address,categories);
  }

  @Override public void completeOrder(String address, Order order)
  {
    shopList.getShop(address).addOrder(order);
    modelPersistence.save(address, order);
  }

  @Override public void addItemToOrder(String address, Item item)
  {
    Item item1 = getSpecificItem(address, item.getExpirationDate(), item.getProduct().getProductID());
    item1.setQuantity(item1.getQuantity() - 1);

    property.firePropertyChange("StockUpdate", null, 1);

  }

  @Override public void removeItemFromOrder(String address, Item item,int quantityOfItem)
  {

    Item item1 = getSpecificItem(address, item.getExpirationDate(), item.getProduct().getProductID());
    item1.setQuantity(item1.getQuantity() + quantityOfItem);

    property.firePropertyChange("StockUpdate", null, 1);
  }

  @Override public Product getProduct(String address, int productNumber)
  {
    return shopList.getProduct(address, productNumber);
  }

  @Override public ArrayList<Item> getItemsByProduct(String address,
      Product product)
  {
    return shopList.getItemsByProduct(address, product);
  }

  @Override public double getLowestPriceOfProduct(String address,
      Product product)
  {
    return shopList.getLowestPriceOfProduct(address, product);
  }

  @Override public int getQuantityOfCertainProduct(String address,
      Product product)
  {
    return shopList.getQuantityOfCertainProduct(address, product);
  }

  @Override public Item getSpecificItem(String address, Date expirationDate,
      int productId)
  {
    return shopList.getSpecificItem(address, expirationDate, productId);
  }

  @Override public ArrayList<Shop> getAllShops()
  {
    return shopList.getShops();
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
    userList.addUser(username, password);
  }

  @Override public User getUser(String username, String password)
  {
    return userList.getUser(username, password);
  }

  @Override public void addItem(String address, String productName,
      int productID, double price, Date expirationDate, int quantity,
      ArrayList<Category> categories)
  {
    if (expirationDate.isBefore(new Date()))
    {
      throw new IllegalArgumentException("Expiration date cannot be before today's date");
    }
    Product searchedProduct = getProduct(address,productID);


    if(searchedProduct == null)
    {
      Product product = new Product(productName, productID, categories);
      modelPersistence.save(product);
      Item item = new Item(product, price, expirationDate, quantity);
      modelPersistence.save(address, item);
      shopList.addItem(address,item,product);
    }
    else
    {
      if (!searchedProduct.getProductName().equals(productName) ||
          !searchedProduct.getCategories().containsAll(categories))
      {
        throw new IllegalStateException("There already exists product with such product id");
      }
      Item searchedItem = getSpecificItem(address,expirationDate, productID);
      if (searchedItem == null)
      {
        Item item = new Item(getProduct(address,productID), price, expirationDate, quantity);
        modelPersistence.save(address,item);
        shopList.addItem(address,item);
      }
      else
      {
        searchedItem.setCurrentPrice(price);
        searchedItem.setQuantity(quantity);
        modelPersistence.update(address,searchedItem);
      }
    }

    property.firePropertyChange("StockUpdate", null, 1);

    Log.getInstance(new Date().getDatabaseFormat()).addLog( address+ " - item was added to database");

  }

  @Override public Order getOrder(String shopAddress, int day, int month, int year, int hour,
      int minute, int second, String deliveryOptions)
  {
    return shopList.getShop(shopAddress).getOrder(day,month,year,hour,minute,second,deliveryOptions);
  }

  @Override public void removeOrder(String shopAddress, int day, int month, int year, int hour,
      int minute, int second, String deliveryOptions)
  {

    shopList.getShop(shopAddress).removeOrder(day,month,year,hour,minute,second,deliveryOptions);
    modelPersistence.updateCompletedOrder(getOrder(shopAddress, day, month, year, hour, minute, second, deliveryOptions));

    Log.getInstance(new Date().getDatabaseFormat()).addLog(shopAddress+ "- order was completed");
  }

  @Override public ArrayList<Order> getOrderList(String shopAddress)
  {
    return shopList.getShop(shopAddress).getOrderList();

  }

  @Override public void removeItem(String address, Date expirationDate, int productID)
  {
    Item item  = shopList.getSpecificItem(address, expirationDate, productID);
    if (item.getQuantity() != 0)
    {
      item.setQuantity(item.getQuantity() - 1);

      modelPersistence.update(address, item);

      property.firePropertyChange("StockUpdate", null, 1);
      property.firePropertyChange("RemoveItemFromShop", null, 1);

      Log.getInstance(new Date().getDatabaseFormat()).addLog(address +", " + item + " - item was removed by 1 from database");

    }

  }

  @Override public ArrayList<Item> getAllItemsFromShop(String address)
  {
    return shopList.getShop(address).getAllItems();
  }

  @Override public void updateItem(String shopAddress, String previousDate,
      int previousNumber, Date date, ArrayList<Category> categories,
      long newNumber, String newName, double newPrice, int newQuantity)
  {
    Item item = getSpecificItem(shopAddress,new Date(previousDate), previousNumber);
    boolean changedProduct = false;

    Product product = getProduct(shopAddress, previousNumber);

    if (!product.getProductName().equals(newName))
    {
      product.setProductName(newName);
      changedProduct = true;
    }
    else if(previousNumber != newNumber)
    {
      product.setProductID((int)newNumber);
      changedProduct = true;
    }
    else if(!product.getCategories().equals(categories))
    {
      product.setCategories(categories);
      changedProduct = true;
    }

    item.setDefaultPrice(newPrice);
    item.setQuantity(newQuantity);
    item.setExpirationDate(date);


    modelPersistence.update(shopAddress,item, new Date(previousDate), previousNumber, changedProduct);
    property.firePropertyChange("StockUpdate", null, 1);
    Log.getInstance(new Date().getDatabaseFormat()).addLog(shopAddress + ", " + item+ " - item was updated in database");
  }
}

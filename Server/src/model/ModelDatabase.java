package model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class implementing ModelPersistence interface, ensuring persistence of the system by creating sql statements and running them in relational database (written in postgres)
 */
public class ModelDatabase implements ModelPersistence
{

  private MyDatabasev2 db;
  private static final String DRIVER = "org.postgresql.Driver";
  private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
  private static final String USER = "postgres";
  private static final String PASSWORD = "tom2002";
  private Model model;

  /**
   * 1 argument constructor, setting model instance variable and setting up connection with database using class MyDatabaseV2
   *
   * @param model model from which data is saved into database and into which data is loaded from database
   * @throws ClassNotFoundException if the class tries to load in a class that does not exist in a system
   */

  public ModelDatabase(Model model) throws ClassNotFoundException
  {
    this.db = new MyDatabasev2(DRIVER, URL, USER, PASSWORD);
    this.model = model;
  }

  @Override public void save(String address, Order order)
  {
    try
    {
      String deliveryType;
      String deliveryDetails;

      if (order.getPickUpTime() == null)
      {
        deliveryType = "delivery";
        deliveryDetails = order.getAddressLinePrimary() + ", "
            + order.getAddressLineSecondary() + ", " + order.getPostalCode()
            + ", " + order.getCity();
      }
      else
      {
        deliveryType = "pick-up";
        deliveryDetails = order.getPickUpTime();
      }

      String sql =
          "INSERT INTO food_waste.orders (total_price, date, email, shop_address, delivery_type, delivery_details, is_completed, time)"
              + " VALUES (? , ? , ? , ? , ? , ? , ?, ?)";
      Object[] updateResult = db.update(sql, order.getTotalPrice(),
          java.sql.Date.valueOf(order.getDate().getDatabaseFormat()),
          order.getEmail(), address, deliveryType, deliveryDetails,
          order.isCompleted(), order.toStringTime());

      ResultSet keys = (ResultSet) updateResult[1];

      int orderNumber = -1;
      if (keys.next())
      {
        orderNumber = keys.getInt(1);
      }

      for (Map.Entry<Item, Integer> entry : order.getItems().entrySet())
      {
        sql = "SELECT item.item_number FROM food_waste.item WHERE item.product_number = ? AND item.expiration_date = ?";

        Item item = entry.getKey();
        int quantity = entry.getValue();
        ArrayList<Object[]> results = db.query(sql,
            item.getProduct().getProductID(), java.sql.Date.valueOf(
                item.getExpirationDate().getDatabaseFormat()));
        int itemId = (int) results.get(0)[0];
        sql =
            "INSERT INTO food_waste.order_item (order_number, item_number, quantity_of_item)"
                + " VALUES (? , ? , ?)";
        db.update(sql, orderNumber, itemId, quantity);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw new IllegalStateException(
          "Please check the info you have provided");

    }
  }

  @Override public void save(String address, Item item)
  {
    try
    {
      String sql =
          "INSERT INTO food_waste.item (quantity_in_stock, price, expiration_date, product_number, shop_address)"
              + " VALUES (? , ? , ? , ? , ?)";
      Object[] updateResult = db.update(sql, item.getQuantity(),
          item.getDefaultPrice(),
          java.sql.Date.valueOf(item.getExpirationDate().getDatabaseFormat()),
          item.getProduct().getProductID(), address);

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

  }

  /**
   * Supporting method saving categories of the product into database
   *
   * @param product Product object which categories have to be saved
   */

  private void saveCategories(Product product)
  {
    for (int i = 0; i < product.getCategories().size(); i++)
    {
      String sql =
          "INSERT INTO food_waste.category (product_number, category_name)"
              + " VALUES (? , ? )";
      try
      {
        db.update(sql, product.getProductID(),
            product.getCategories().get(i).getName());
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public void save(Product product)
  {
    try
    {
      String sql = "SELECT product.product_number, product.name FROM food_waste.product WHERE product.product_number = ?";

      ArrayList<Object[]> results = db.query(sql, product.getProductID());

      if (results.size() == 0)
      {
        sql = "INSERT INTO food_waste.product (product_number, name)"
            + " VALUES (? , ? )";
        Object[] updateResult = db.update(sql, product.getProductID(),
            product.getProductName());

        saveCategories(product);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void update(String address, Item item)
  {
    String sql = "UPDATE food_waste.item SET quantity_in_stock = ? , price = ? WHERE product_number = ? AND expiration_date = ? AND shop_address = ?";

    try
    {
      db.update(sql, item.getQuantity(), item.getDefaultPrice(),
          item.getProduct().getProductID(),
          java.sql.Date.valueOf(item.getExpirationDate().getDatabaseFormat()),
          address);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void update(String address, Item item,
      Date expirationDateFromDb, int productNumberFromDb,
      boolean isProductChanged)
  {
    String sql = "UPDATE food_waste.item SET quantity_in_stock = ? , price = ?, expiration_date = ? WHERE product_number = ? AND expiration_date = ? AND shop_address = ?";

    Product product = item.getProduct();

    try
    {
      db.update(sql, item.getQuantity(), item.getDefaultPrice(),
          java.sql.Date.valueOf(item.getExpirationDate().getDatabaseFormat()),
          productNumberFromDb,
          java.sql.Date.valueOf(expirationDateFromDb.getDatabaseFormat()),
          address);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    if (isProductChanged)
    {
      if (productNumberFromDb != product.getProductID())
      {
        save(product);

        sql = "UPDATE food_waste.item SET product_number = ? WHERE product_number = ?";

        try
        {
          db.update(sql, product.getProductID(), productNumberFromDb);
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }

        sql = "DELETE FROM food_waste.product WHERE product_number = ?";

        try
        {
          db.update(sql, productNumberFromDb);
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
      else
      {
        sql = "UPDATE food_waste.product SET name = ? WHERE product_number = ?";
        try
        {
          db.update(sql, product.getProductName(), product.getProductID());

          sql = "DELETE FROM food_waste.category WHERE product_number = ?";

          db.update(sql, product.getProductID());
          saveCategories(product);
        }

        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
    }

  }

  @Override public void updateCompletedOrder(Order order)
  {
    String sql = "UPDATE food_waste.orders SET is_completed = ? WHERE date = ? AND time = ? AND total_price = ? AND delivery_type = ?";
    String deliveryType;
    if (order.getOrderDescription().contains("Address"))
    {
      deliveryType = "delivery";

    }
    else
    {
      deliveryType = "pick-up";
    }
    try

    {
      db.update(sql, true,
          java.sql.Date.valueOf(order.getDate().getDatabaseFormat()),
          order.toStringTime(), order.getTotalPrice(), deliveryType);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public UserList loadUsers()
  {

    UserList list = new UserList();
    String sql = "SELECT employee.username, employee.hash_password, employee.shop_address FROM food_waste.employee";

    try
    {
      ArrayList<Object[]> results = db.query(sql);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        User user = new User((String) result[0], (int) result[1]);
        user.setShopAddress((String) result[2]);
        list.addUser(user);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return list;
  }

  @Override public ShopList loadShops()
  {
    ShopList shopList = new ShopList();

    String sql = "SELECT shop.name, shop.address FROM food_waste.shop";

    try
    {
      ArrayList<Object[]> results = db.query(sql);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        Object[] itemAndProductList = loadItemsAndProductsFromShop(
            (String) result[1]);
        ProductList productList = (ProductList) itemAndProductList[1];
        ItemList itemList = (ItemList) itemAndProductList[0];
        Shop shop = new Shop((String) result[0], (String) result[1], itemList,
            productList);

        shopList.addShop(shop);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return shopList;
  }

  /**
   * Supporting method loading singular product from database
   *
   * @param productNumber number representing product number
   * @return Product object created from data from database with passed product number
   */
  private Product loadProduct(int productNumber)
  {
    Product product = null;
    String sql = "SELECT product.product_number, product.name FROM food_waste.product WHERE product.product_number = ?";

    try
    {
      ArrayList<Object[]> results = db.query(sql, productNumber);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        ArrayList<Category> categories = new ArrayList<>();
        sql = "SELECT category.category_name FROM food_waste.category WHERE category.product_number = ?";
        ArrayList<Object[]> categoriesResults = db.query(sql, productNumber);
        for (int j = 0; j < categoriesResults.size(); j++)
        {
          Object[] categoriesFromDatabase = categoriesResults.get(j);
          categories.add(new Category((String) categoriesFromDatabase[0]));
        }

        product = new Product((String) result[1], (int) result[0], categories);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return product;
  }

  /**
   * Supporting method loading itemList and productList from certain shop from database
   *
   * @param address String containing address of the shop
   * @return itemList with items created from data from database and productList with products created from data from database, both located at passed shop address
   */
  private Object[] loadItemsAndProductsFromShop(String address)
  {
    ItemList itemList = new ItemList();
    ProductList productList = new ProductList();

    String sql = "SELECT item.product_number,  item.price, item.quantity_in_stock, item.expiration_date FROM food_waste.item WHERE item.shop_address = ?";

    try
    {
      ArrayList<Object[]> results = db.query(sql, address);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        Product product = loadProduct((int) result[0]);
        productList.addProduct(product);
        itemList.addItem(
            new Item(product, ((BigDecimal) result[1]).doubleValue(),
                new Date((result[3]).toString()), (int) result[2]));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return new Object[] {itemList, productList};
  }

  public ArrayList<Order> loadOrdersFromShop(String address)
  {
    ArrayList<Order> list = new ArrayList<>();
    String sql = "SELECT orders.order_number, orders.total_price, orders.email, orders.date, orders.delivery_type, orders.delivery_details, orders.is_completed, orders.time, orders.shop_address FROM food_waste.orders WHERE orders.shop_address = ?";

    try
    {
      ArrayList<Object[]> results = db.query(sql, address);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);

        Order order = new Order(new Date((result[3]).toString()),
            (boolean) result[6], ((BigDecimal) result[1]).doubleValue());
        order.setLocalTime((String) result[7]);
        order.setShopAddress((String) result[8]);
        order.setEmail((String) result[2]);
        if ((result[4]).equals("pick-up"))
        {
          order.setDelivery((String) result[5]);
        }
        else
        {
          String addressCombined = (String) result[5];
          String[] words = addressCombined.split("[,]+");
          if (words.length == 4)
          {
            order.setDelivery(words[0].trim(), words[1].trim(), words[3].trim(),
                Integer.parseInt(words[2].trim()));
          }
          else
          {
            order.setDelivery(words[0], null, words[2],
                Integer.parseInt(words[1]));
          }

        }

        sql = "SELECT order_item.item_number, order_item.quantity_of_item FROM food_waste.order_item WHERE order_item.order_number = ?";
        ArrayList<Object[]> itemsInOrder = db.query(sql, result[0]);

        for (Object[] itemInOrder : itemsInOrder)
        {
          sql = "SELECT item.expiration_date, item.product_number FROM food_waste.item WHERE item.item_number = ?";
          ArrayList<Object[]> specificItem = db.query(sql, itemInOrder[0]);
          Item item = model.getSpecificItem(address,
              new Date(specificItem.get(0)[0].toString()),
              (int) specificItem.get(0)[1]);
          order.addItem(item, (int) itemInOrder[1]);
        }

        list.add(order);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return list;
  }

}

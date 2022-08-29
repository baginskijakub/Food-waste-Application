package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of products
 */
public class ProductList implements Serializable
{
  private ArrayList<Product> products;

  /**
   * A zero argument constructor creating new ArrayList.
   */
  public ProductList()
  {
    this.products = new ArrayList<>();
  }

  /**
   * A method adding a product to the productList
   * @param product
   */
  public void addProduct(Product product)
  {
    products.add(product);
  }

  /**
   * A getter for product with a given product number
   * @param productNumber
   * @return
   */
  public Product getProduct(int productNumber)
  {
    for (int i = 0; i < products.size(); i++)
    {
      if (products.get(i).getProductID() == productNumber)
      {
        return products.get(i);
      }
    }
    return null;
  }

  /**
   * A getter for all products
   * @return ArrayList of type Product
   */
  public ArrayList<Product> getAllProducts()
  {
    return products;
  }

  /**
   * A method returning all products in an ArrayList that have one or more of categories passed as an argument in an ArrayList
   * @param categories ArrayList of type category
   * @return ArrayList of type product
   */
  public ArrayList<Product> getProductsByCategory(ArrayList<String> categories)
  {
    if (categories.size() == 0)
    {
      return getAllProducts();
    }

    ArrayList<Product> temp = new ArrayList<>();
    for(int i = 0; i < products.size(); i++)
    {
      for(int j = 0; j < categories.size(); j++)
      {
        if(products.get(i).getCategories().contains(new Category(categories.get(j))))
        {
          if(!(temp.contains(products.get(i))))
          {
            temp.add(products.get(i));
          }
        }
      }
    }
    return temp;
  }
  /**
   * Compares passed variable with this object.
   *
   * @param obj
   * @return true if passes object is the same as this object
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof ProductList))
    {
      return false;
    }
    ProductList other = (ProductList) obj;
    return this.products.equals(other.products);
  }

  /**
   * A getter for number od products in product list
   * @return
   */
  public int size()
  {
    return products.size();
  }
}

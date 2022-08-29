package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a Product having product name, product id and categories.
 */
public class Product implements Serializable
{
  private String productName;
  private int productID;
  private ArrayList<Category> categories;

  /**
   * A three argument constructor taking product name, product id and categories as an ArrayList
   * @param productName
   * @param productID
   * @param categories
   */
  public Product(String productName, int productID, ArrayList<Category> categories)
  {
    if (categories.size() == 0)
    {
      throw new IllegalArgumentException("Product has to have at least 1 category");
    }

    if (productName == null || productID <= 0)
    {
      throw new IllegalArgumentException("Enter correct data");
    }

    this.productID = productID;
    this.productName = productName;
    this.categories = categories;
  }

  /**
   * A method returning productName
   * @return
   */
  @Override public String toString()
  {
    return productName;
  }
  /**
   * A getter for productID
   * @return
   */
  public int getProductID()
  {
    return productID;
  }
  /**
   * A setter for productID
   * @return
   */
  public void setProductID(int productID)
  {
    this.productID = productID;
  }
  /**
   * A getter for productName
   * @return
   */
  public String getProductName()
  {
    return productName;
  }
  /**
   * A getter for categories
   * @return
   */
  public ArrayList<Category> getCategories()
  {
    return categories;
  }
  /**
   * A setter for productName
   * @return
   */
  public void setProductName(String productName)
  {
    this.productName = productName;
  }
  /**
   * A getter for categories taking ArrayList of category type as a parameter
   * @return
   */
  public void setCategories(ArrayList<Category> categories)
  {
    this.categories = categories;
  }
  /**
   * A method checking if this product has a passed category
   * @return
   */
  public boolean hasCategory(String category)
  {
    boolean cond = false;
    for(Category c : categories)
    {
      if(c.getName().equals(category))
      {
        cond = true;
      }
    }
    return cond;
  }
  /**
   * Compares passed variable with this object.
   *
   * @param obj
   * @return true if passes object is the same as this object
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Product))
    {
      return false;
    }
    Product other = (Product) obj;
    return this.productName.equals(other.productName) && this.productID == other.productID;
  }
}

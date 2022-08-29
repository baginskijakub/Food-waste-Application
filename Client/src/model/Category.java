package model;

import java.io.Serializable;

/**
 * This class represents a category that is one of the pre-coded values in NAMES instance variable.
 */
public class Category implements Serializable
{
  private static final String[] NAMES = {"Frozen Food", "Meat & Seafood", "Diary", "Bakery", "Beverages", "Snacks", "Fruits", "Vegetables"};
  private String name;

  /**
   * A one argument constructor validating if passed variable exists as a pre-coded value.
   * @param name the category type
   *
   * @throws IllegalArgumentException if a category does not exist as a pre-coded variable.
   */
  public Category(String name)
  {
    boolean cond = false;

    for(String s : NAMES)
    {
      if(name.equals(s))
      {
        cond = true;
      }
    }

    if(cond)
    {
      this.name = name;
    }
    else
    {
      throw new IllegalArgumentException("Such category does not exist");
    }
  }

  /**
   * Getter for name
   * @return category name as a String
   */
  public String getName()
  {
    return name;
  }

  /**
   * Compares passed variable with this object.
   *
   * @param obj
   * @return true if passes object is the same as this object
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Category))
    {
      return false;
    }
    Category other = (Category) obj;
    return this.name.equals(other.name);
  }
}

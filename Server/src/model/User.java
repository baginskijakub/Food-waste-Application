package model;

import java.io.Serializable;

/**
 * Class representing employee in the system
 */
public class User implements Serializable
{
  private String username;
  private int hashPassword;
  private String shopAddress;

  /**
   * 2 arguments constructor, setting username, taking password as a string and storing hash value of it and setting shopAddress to null
   *
   * @param username String containing username of the employee
   * @param password String containing password of the employee
   * @throws IllegalArgumentException if constraints regarding username and password were not met
   */
  public User(String username, String password)
  {
    if (username == null || password == null || username.length() <= 8
        || password.length() <= 8 || username.length() >= 20
        || password.length() >= 20)
    {
      throw new IllegalArgumentException(
          "Username and password needs to have between 8 and 20 characters");
    }
    this.username = username;
    this.hashPassword = hash(password);
    shopAddress = null;
  }

  /**
   * 2 arguments constructor, taking username and hash value of password.
   * Used while loading employees' data from database
   *
   * @param username String containing username of the employee
   * @param hashValue number representing hash value of the password of employee
   */
  public User(String username, int hashValue)
  {
    this.username = username;
    this.hashPassword = hashValue;
    shopAddress = null;
  }

  /**
   * Getter for shop address
   *
   * @return String containing shop address where employee works
   */
  public String getShopAddress()
  {
    return shopAddress;
  }

  /**
   * Setter for shop address
   *
   * @param shopAddress String containing shop address where employee works
   */

  public void setShopAddress(String shopAddress)
  {
    this.shopAddress = shopAddress;
  }

  /**
   * Getter for username
   *
   * @return String containing username of employee
   */

  public String getUsername()
  {
    return username;
  }

  /**
   * Getter for hash value of password
   *
   * @return hash value of password
   */

  public int getHashPassword()
  {
    return hashPassword;
  }

  /**
   * Comparing user and obj Object whether they are equal
   *
   * @param obj Object compared with user
   * @return true if user and obj are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof User))
    {
      return false;
    }
    User other = (User) obj;
    return this.hashPassword == other.hashPassword && this.username.equals(
        other.username);
  }

  /**
   * Private method used to obtain hash value of employee's password
   *
   * @param password String containing password that will be hashed
   * @return hash value representing the password
   */

  private int hash(String password)
  {
    return password.hashCode();
  }
}

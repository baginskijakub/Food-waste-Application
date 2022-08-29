package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class responsible for storing list of all employees (called users here) in the system
 */
public class UserList implements Serializable
{
  private ArrayList<User> list;

  /**
   * 0 argument constructor, initializing array list
   * */
  public UserList()
  {
    this.list = new ArrayList<>();
  }

  /**
   * Method getting the user with certain credentials
   * @param username String containing username
   * @param password String containing password
   * @return user that matches passed credentials
   * @throws IllegalArgumentException if user with such credentials does not exist
   */

  public User getUser(String username, String password)
  {
    for(User u : list)
    {
      if(u.getHashPassword() == password.hashCode() && u.getUsername().equals(username))
      {
        return u;
      }
    }
    throw new IllegalArgumentException("Such user does not exist, check username and password.");
  }

  /**
   * Method adding user to the list, first validating his credentials
   * @param username username of new user
   * @param password String containing password of the new user
   */
  public void addUser(String  username, String password)
  {
    if(isLegalUsername(username))
    {
      list.add(new User(username, password));
    }
    else
    {
      throw new IllegalArgumentException("Such username already exists.");
    }
  }

  /**
   * Method adding user to the list, without validation, used while loading data from database (data already validated)
   * @param user user that we want to add to the list
   */

  public void addUser(User user)
  {

      list.add(user);

  }

  /**
   * Method validating username of user
   * @param username String containing username we want to validate
   * @return true if username is validated, otherwise false
   */

  public boolean isLegalUsername(String username)
  {
    for(User u : list)
    {
      if(u.getUsername().equals(username))
      {
        throw new IllegalArgumentException("Such username already exists.");
      }
    }
    return true;
  }
}

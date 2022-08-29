package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of items
 */
public class ItemList implements Serializable
{
  private ArrayList<Item> list;

  /**
   * A zero argument constructor taking no arguments.
   */
  public ItemList()
  {
    this.list = new ArrayList<>();
  }

  /**
   * A getter for items.
   * @return ArrayList of Items
   */
  public ArrayList<Item> getItems()
  {
    return list;
  }

  /**
   * A method adding an Item to the list
   * @param item
   */
  public void addItem(Item item)
  {
    list.add(item);
  }

  /**
   * A method removing an Item from the list
   * @param item
   */
  public void removeItem(Item item)
  {
    list.remove(item);
  }

  /**
   * A getter for all items of a certain product
   * @param product
   * @return ArrayList of items which are items of a product that have been passed as an argument
   */
  public ArrayList<Item> getItems(Product product)
  {
    ArrayList<Item> temp = new ArrayList<>();
    for(Item i : list)
    {
      if(i.getProduct().equals(product))
      {
        temp.add(i);
      }
    }
    return temp;
  }

  /**
   * A getter returning quantity of all items summed up.
   * @return
   */
  public int getQuantityOfAllItems()
  {
    int sum = 0;

    for (Item item : list)
    {
      sum += item.getQuantity();
    }

    return sum;
  }
}

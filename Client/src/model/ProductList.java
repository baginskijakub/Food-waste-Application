package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductList implements Serializable
{
  private ArrayList<Product> products;

  public ProductList()
  {
    this.products = new ArrayList<>();
  }

  public void addProduct(Product product)
  {
    products.add(product);
  }

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

  public ArrayList<Product> getAllProducts()
  {
    return products;
  }

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

  public boolean equals(Object obj)
  {
    if(!(obj instanceof ProductList))
    {
      return false;
    }
    ProductList other = (ProductList) obj;
    return this.products.equals(other.products);
  }

  public int size()
  {
    return products.size();
  }
}

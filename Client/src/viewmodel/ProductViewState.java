package viewmodel;

import model.Product;

public class ProductViewState
{
  private Product product;

  public ProductViewState()
  {
    product = null;
  }

  public void setProduct(Product product)
  {
    this.product = product;
  }

  public Product getProduct()
  {
    return product;
  }
}

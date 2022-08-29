package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditProductViewModel
{
  private StringProperty productName;
  private LongProperty productNumber;
  private StringProperty price;
  private ObjectProperty<LocalDate> expirationDate;
  private IntegerProperty quantity;
  private StringProperty errorLabel;
  private ObservableList<String> pickedCategory;
  private ModelEmployee modelEmployee;
  private UserViewState userViewState;
  private ItemViewState itemViewState;

  public EditProductViewModel(Model model, UserViewState userViewState,
      ItemViewState itemViewState)
  {
    this.modelEmployee = model;
    this.userViewState = userViewState;
    this.itemViewState = itemViewState;
    productName = new SimpleStringProperty();
    productNumber = new SimpleLongProperty();
    price = new SimpleStringProperty();
    expirationDate = new SimpleObjectProperty<>();
    quantity = new SimpleIntegerProperty();
    errorLabel = new SimpleStringProperty();
    pickedCategory = FXCollections.observableArrayList();
  }

  public void addCategory(String category)
  {
    pickedCategory.add(category);
  }

  public void removeCategory(String category)
  {
    pickedCategory.remove(category);
  }

  public void clear()
  {
    productName.set(itemViewState.getItem().getProduct().getProductName());
    productNumber.set(itemViewState.getItem().getProduct().getProductID());
    price.set(String.valueOf(itemViewState.getItem().getDefaultPrice()));
    quantity.set(itemViewState.getItem().getQuantity());
    errorLabel.set("");
    expirationDate.set(LocalDate.parse(
        itemViewState.getItem().getExpirationDate().getDatabaseFormat()));
    pickedCategory.clear();

    for (Category category : itemViewState.getItem().getProduct().getCategories())
    {
      pickedCategory.add(category.getName());
    }

  }

  public void editProduct()
  {

    String previousDate = itemViewState.getItem().getExpirationDate()
        .getDatabaseFormat();
    int previousNumber = itemViewState.getItem().getProduct().getProductID();



    ArrayList<Category> categories = new ArrayList<>();
    for (String s : pickedCategory)
    {
      categories.add(new Category(s));
    }
    try
    {
      LocalDate localdate = expirationDate.get();
      Date date = new Date(localdate.getDayOfMonth(), localdate.getMonthValue(),
          localdate.getYear());

      modelEmployee.updateItem(userViewState.getShopAddress(), previousDate,
          previousNumber, date, categories, productNumber.get(),
          productName.get(), Double.parseDouble(price.get()), quantity.get());

      errorLabel.set("Item was updated");
      itemViewState.setItem(modelEmployee.getSpecificItem(userViewState.getShopAddress(), date, (int) productNumber.get()));
    }
    catch (Exception e)
    {
      errorLabel.set(e.getMessage() == null ? "Use valid data" : e.getMessage());
    }

  }

  public StringProperty getProductNameProperty()
  {
    return productName;
  }

  public LongProperty getProductNumberProperty()
  {
    return productNumber;
  }

  public StringProperty getPriceProperty()
  {
    return price;
  }

  public ObjectProperty<LocalDate> getExpirationDateProperty()
  {
    return expirationDate;
  }

  public IntegerProperty getQuantityProperty()
  {
    return quantity;
  }

  public StringProperty getErrorLabelProperty()
  {
    return errorLabel;
  }

  public ObservableList<String> getPickedCategoryProperty()
  {
    return pickedCategory;
  }
}

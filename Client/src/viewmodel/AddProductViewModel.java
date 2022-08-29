package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;
import model.Date;
import model.Model;
import model.ModelEmployee;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddProductViewModel
{
    private StringProperty productName;
    private IntegerProperty productNumber;
    private StringProperty price;
    private ObjectProperty<LocalDate> expirationDate;
    private IntegerProperty quantity;
    private StringProperty errorLabel;
    private ObservableList<String> pickedCategory;
    private ModelEmployee modelEmployee;
    private UserViewState userViewState;

    public AddProductViewModel(Model model, UserViewState userViewState)
    {
        this.modelEmployee = model;
        this.userViewState = userViewState;
        productName = new SimpleStringProperty();
        productNumber = new SimpleIntegerProperty();
        price = new SimpleStringProperty();
        expirationDate = new SimpleObjectProperty<>();
        quantity = new SimpleIntegerProperty();
        errorLabel = new SimpleStringProperty();
        pickedCategory = FXCollections.observableArrayList();
        clear();
    }

    public void clear()
    {
        productName.set("");
        productNumber.set(0);
        price.set("");
        quantity.set(0);
        errorLabel.set("");
        expirationDate.set(null);
    }

    public void addCategory(String s)
    {
        pickedCategory.add(s);
    }

    public void removeCategory(String s)
    {
        pickedCategory.remove(s);
    }

    public void addProduct()
    {


        ArrayList<Category> categories = new ArrayList<>();
        for(String s: pickedCategory)
        {
            categories.add(new Category(s));
        }


        try
        {
            LocalDate localdate = expirationDate.get();
            Date date = new Date(localdate.getDayOfMonth(), localdate.getMonthValue(),
                localdate.getYear());


            modelEmployee.addItem(userViewState.getShopAddress(), productName.get(),
                productNumber.get(), Double.parseDouble(price.get()), date, quantity.get(),
                categories);
            errorLabel.set("Item was successfully added");
        }
        catch (Exception e)
        {
            clear();
            errorLabel.set(e.getMessage() == null ? "Enter correct data" : e.getMessage());
        }
    }

    public StringProperty getProductNameProperty()
    {
        return productName;
    }

    public IntegerProperty getProductNumberProperty()
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

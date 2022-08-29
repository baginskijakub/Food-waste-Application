package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Order;

public class AddressViewModel
{
    private StringProperty address1;
    private StringProperty address2;
    private StringProperty city;
    private IntegerProperty postalCode;
    private StringProperty error;
    private Model model;

    public AddressViewModel(Model model)
    {
        this.model = model;
        address1 = new SimpleStringProperty();
        address2 = new SimpleStringProperty();
        city = new SimpleStringProperty();
        postalCode = new SimpleIntegerProperty();
        error = new SimpleStringProperty();
    }

    public void clear()
    {
        address1.set("");
        address2.set("");
        city.set("");
        postalCode.set(0);
        error.set("");

    }

    public boolean setDeliveryOptions()
    {

        try
        {
            model.setDelivery(address1.get(), address2.get(), city.get(), postalCode.get());
            return true;
        }
        catch (Exception e)
        {
            error.set(e.getMessage());
            return false;
        }

    }

    public StringProperty getAddress1()
    {
        return address1;
    }

    public StringProperty getAddress2()
    {
        return address2;
    }

    public StringProperty getCity()
    {
        return city;
    }

    public IntegerProperty getPostalCode()
    {
        return postalCode;
    }

    public StringProperty getError()
    {
        return error;
    }
}

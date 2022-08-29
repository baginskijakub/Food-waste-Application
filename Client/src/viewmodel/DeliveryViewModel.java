package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class DeliveryViewModel
{
    private StringProperty email;
    private Model model;
    private StringProperty error;

    public DeliveryViewModel(Model model)
    {
        this.email = new SimpleStringProperty();
        this.model = model;
        error = new SimpleStringProperty();
    }

    public StringProperty getEmail()
    {
        return email;
    }

    public boolean setEmail(String email)
    {
        try
        {
            model.setEmail(email);
            return true;
        }
        catch (Exception e)
        {
            error.set(e.getMessage());
            return false;
        }

    }

    public StringProperty getError()
    {
        return error;
    }
}

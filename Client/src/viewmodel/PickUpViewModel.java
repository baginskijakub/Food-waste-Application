package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class PickUpViewModel
{
    private Model model;
    private StringProperty timeSelection;

    public PickUpViewModel(Model model)
    {
        this.model = model;
        timeSelection = new SimpleStringProperty();
    }

    public void setDeliveryOptions()
    {
        model.setDelivery(timeSelection.get());
    }

    public StringProperty getTimeSelection()
    {
        return timeSelection;
    }
}

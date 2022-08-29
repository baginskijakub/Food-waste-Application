package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import model.Model;
import model.ModelEmployee;

public class EmployeeViewModel
{
    private StringProperty username;
    private StringProperty workplace;
    private ModelEmployee model;
    private UserViewState viewState;

    public EmployeeViewModel(ModelEmployee model, UserViewState userViewState)
    {
        this.model = model;
        viewState = userViewState;
        username = new SimpleStringProperty();
        workplace = new SimpleStringProperty();
    }

    public StringProperty getUsername()
    {
        return username;
    }

    public StringProperty getWorkplace()
    {
        return workplace;
    }

    public void clear()
    {
        username.set(viewState.getUsername());
        workplace.set(viewState.getShopAddress());
    }
}

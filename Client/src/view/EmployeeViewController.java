package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import viewmodel.EmployeeViewModel;

import java.io.IOException;

public class EmployeeViewController extends ViewController
{
    @FXML private Label usernameLabel;
    @FXML private Label workplaceLabel;

    private EmployeeViewModel employeeViewModel;

    protected void init()
    {
      employeeViewModel = getViewModelFactory().getEmployeeViewModel();
        usernameLabel.textProperty().bind(employeeViewModel.getUsername());
        workplaceLabel.textProperty().bind(employeeViewModel.getWorkplace());
    }

    public void reset()
    {
        employeeViewModel.clear();
    }

    @FXML private void addProductsButton() throws IOException
    {
        getViewHandler().openView("AddProduct");
    }

    @FXML private void backButton() throws IOException
    {
        getViewHandler().openView("Login");
    }

    @FXML private void manageOrdersButton()throws IOException
    {
        getViewHandler().openView("EmployeeViewOrderList");
    }

    @FXML private void manageProductsButton() throws IOException
    {
        getViewHandler().openView("EmployeeManageItems");
    }
}

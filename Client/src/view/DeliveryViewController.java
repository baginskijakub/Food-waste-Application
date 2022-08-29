package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import view.ViewController;
import viewmodel.DeliveryViewModel;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;

public class DeliveryViewController extends ViewController
{
    private DeliveryViewModel deliveryViewModel;
    @FXML private TextField emailField;
    @FXML private Label errorLabel;

    @Override
    protected void init()
    {
        deliveryViewModel = getViewModelFactory().getDeliveryViewModel();

        Bindings.bindBidirectional(emailField.textProperty(), deliveryViewModel.getEmail());
        errorLabel.textProperty().bind(deliveryViewModel.getError());
    }

    @FXML private void goBackButton() throws IOException
    {
        getViewHandler().openView("Bag");
    }

    @FXML private void deliveredButton() throws IOException
    {
        if (deliveryViewModel.setEmail(emailField.getText()))
        {
            getViewHandler().openView("Address");
        }


    }

    @FXML private void pickUpButton() throws IOException
    {
        if (deliveryViewModel.setEmail(emailField.getText()))
        {
            getViewHandler().openView("Pickup");
        }

    }





}

package view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import view.ViewController;
import viewmodel.PickUpViewModel;

import java.io.IOException;

public class PickUpViewController extends ViewController
{
  @FXML private ChoiceBox<String> timeSelection;

  private PickUpViewModel pickUpViewModel;

  @Override protected void init()
  {
    pickUpViewModel = getViewModelFactory().getPickUpViewModel();

    // not sure if the choice box binding is needed and if below syntax is correct
    timeSelection.getItems()
        .addAll("9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00",
            "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
            "16:00");

    timeSelection.valueProperty()
        .bindBidirectional(pickUpViewModel.getTimeSelection());
  }

  @FXML private void goBackButton() throws IOException
  {
    getViewHandler().openView("Delivery");
  }

  @FXML private void submitButton() throws IOException
  {
      try
      {
          pickUpViewModel.setDeliveryOptions();
          getViewHandler().openView("Payment");
      }
      catch (Exception e)
      {
          //
      }
  }

  public void reset()
  {
    timeSelection.valueProperty().set(null);
  }
}

package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.EmployeeManageItemsViewModel;
import viewmodel.ItemsTableVM;
import viewmodel.ProductsTableVM;

import java.io.IOException;

public class EmployeeManageItemsController extends ViewController
{

  @FXML private TableView<ItemsTableVM> table;
  @FXML private TableColumn<ItemsTableVM, String> nameOfProduct;
  @FXML private TableColumn<ItemsTableVM, Number> productNumber;
  @FXML private TableColumn<ItemsTableVM, String> expiration;
  @FXML private TableColumn<ItemsTableVM, Number> quantity;
  @FXML private TableColumn<ItemsTableVM, Number> price;

  private EmployeeManageItemsViewModel employeeManageItemsViewModel;

  public void goBackButton() throws IOException
  {
    getViewHandler().openView("Employee");
  }

  public void removeButton()
  {
    ItemsTableVM itemsTableVM = table.getSelectionModel().getSelectedItem();

    if (itemsTableVM != null)
    {
      employeeManageItemsViewModel.removeItem(itemsTableVM);
    }

  }

  @Override protected void init()
  {

    employeeManageItemsViewModel = getViewModelFactory().getEmployeeManageItemsViewModel();

    nameOfProduct.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    productNumber.setCellValueFactory(
        cellData -> cellData.getValue().getIdProperty());
    quantity.setCellValueFactory(
        cellData -> cellData.getValue().getQuantityProperty());
    expiration.setCellValueFactory(
        cellData -> cellData.getValue().getDateProperty());
    price.setCellValueFactory(
        cellData -> cellData.getValue().getPriceProperty());

    table.setItems(employeeManageItemsViewModel.getItems());

  }

  @FXML private void updateButton()
  {
    ItemsTableVM selectedItem = table.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      employeeManageItemsViewModel.chooseItem(selectedItem);
      try
      {
        getViewHandler().openView("EditProduct");
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void reset()
  {
    employeeManageItemsViewModel.clear();
  }

}

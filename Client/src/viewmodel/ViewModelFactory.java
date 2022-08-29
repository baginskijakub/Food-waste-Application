package viewmodel;

import model.Model;

public class ViewModelFactory
{

    private ProductsViewModel productsViewModel;
    private ItemsViewModel itemsViewModel;
    private BagViewModel bagViewModel;
    private ProductViewState productViewState;
    private ItemViewState itemViewState;
    private LoginViewModel loginViewModel;
    private AddProductViewModel addProductViewModel;
    private EmployeeViewModel employeeViewModel;
    private ShopViewState shopViewState;
    private ShopsViewModel shopsViewModel;
    private UserViewState userViewState;
    private AddressViewModel addressViewModel;
    private DeliveryViewModel deliveryViewModel;
    private EmployeeOrderView employeeOrderView;
    private OrderListViewModel orderListViewModel;
    private OrderViewModel orderViewModel;
    private PaymentViewModel paymentViewModel;
    private PickUpViewModel pickUpViewModel;
    private OrderViewState orderViewState;
    private EmployeeManageItemsViewModel employeeManageItemsViewModel;
    private EditProductViewModel editProductViewModel;

    public ViewModelFactory(Model model)
    {
        userViewState = new UserViewState();
        productViewState = new ProductViewState();
        itemViewState = new ItemViewState();
        shopViewState = new ShopViewState();
        orderViewState = new OrderViewState();
        shopsViewModel = new ShopsViewModel(model, shopViewState);
        productsViewModel = new ProductsViewModel(model, productViewState, shopViewState);
        itemsViewModel = new ItemsViewModel(model, productViewState, shopViewState);
        bagViewModel = new BagViewModel(model, shopViewState);
        loginViewModel = new LoginViewModel(model, userViewState);
        addProductViewModel = new AddProductViewModel(model,userViewState);
        employeeViewModel = new EmployeeViewModel(model, userViewState);
        addressViewModel = new AddressViewModel(model);
        deliveryViewModel = new DeliveryViewModel(model);
        employeeOrderView = new EmployeeOrderView(model, orderViewState);
        orderListViewModel = new OrderListViewModel(model, userViewState, orderViewState);
        orderViewModel = new OrderViewModel(model, orderViewState);
        paymentViewModel = new PaymentViewModel(model, shopViewState, orderViewState);
        pickUpViewModel = new PickUpViewModel(model);
        employeeManageItemsViewModel = new EmployeeManageItemsViewModel(model, userViewState, itemViewState);
        editProductViewModel = new EditProductViewModel(model,userViewState,itemViewState);
    }

    public AddressViewModel getAddressViewModel()
    {
        return addressViewModel;
    }

    public DeliveryViewModel getDeliveryViewModel()
    {
        return deliveryViewModel;
    }

    public OrderViewModel getOrderViewModel()
    {
        return orderViewModel;
    }

    public PaymentViewModel getPaymentViewModel()
    {
        return paymentViewModel;
    }

    public PickUpViewModel getPickUpViewModel()
    {
        return pickUpViewModel;
    }

    public EmployeeOrderView getEmployeeOrderView()
    {
        return employeeOrderView;
    }

    public OrderListViewModel getOrderListViewModel()
    {
        return orderListViewModel;
    }



    public ShopsViewModel getShopsViewModel()
    {
        return shopsViewModel;
    }

    public ProductsViewModel getProductsViewModel()
    {
        return productsViewModel;
    }

    public ItemsViewModel getItemsViewModel()
    {
        return itemsViewModel;
    }

    public BagViewModel getBagViewModel()
    {
        return bagViewModel;
    }

    public LoginViewModel getLoginViewModel()
    {
        return loginViewModel;
    }

    public EmployeeViewModel getEmployeeViewModel()
    {
        return employeeViewModel;
    }

    public AddProductViewModel getAddProductViewModel()
    {
        return addProductViewModel;
    }

    public EmployeeManageItemsViewModel getEmployeeManageItemsViewModel()
    {
        return employeeManageItemsViewModel;
    }

    public EditProductViewModel getEditProductViewModel()
    {
        return editProductViewModel;
    }
}

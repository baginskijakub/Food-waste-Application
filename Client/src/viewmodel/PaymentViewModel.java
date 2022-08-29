package viewmodel;

import javafx.beans.property.*;
import model.Model;

import javax.swing.*;

public class PaymentViewModel
{
    private StringProperty name;
    private LongProperty cardNo;
    private IntegerProperty securityCode;
    private StringProperty error;
    private Model model;
    private StringProperty paymentCount;
    private IntegerProperty monthField;
    private IntegerProperty yearField;
    private ShopViewState shopViewState;
    private OrderViewState orderViewState;

    public PaymentViewModel(Model model, ShopViewState shopViewState, OrderViewState orderViewState)
    {
        this.model  = model;
        this.shopViewState =shopViewState;
        this.orderViewState = orderViewState;
        name = new SimpleStringProperty();
        cardNo = new SimpleLongProperty();
        securityCode = new SimpleIntegerProperty();
        error = new SimpleStringProperty();
        monthField = new SimpleIntegerProperty();
        yearField = new SimpleIntegerProperty();
        paymentCount = new SimpleStringProperty("Pay: " + model.getOrder().getTotalPrice());
    }

    public void clear()
    {
        paymentCount.set("Pay: " + model.getOrder().getTotalPrice());
        name.set("");
        cardNo.set(0);
        securityCode.set(0);
        error.set("");
    }

    public boolean setPaymentOptions()
    {
        try
        {
            model.setPayment(name.get(),cardNo.get(),monthField.get(),yearField.get(),securityCode.get());
            orderViewState.setOrder(model.getOrder());
            model.completeOrder(shopViewState.getShopAddress(), model.getOrder());
            return true;
        }
        catch (Exception e)
        {
            error.set(e.getMessage());
            return false;
        }
    }

    public StringProperty getName()
    {
        return name;
    }
    public LongProperty getCardNo()
    {
        return cardNo;
    }

    public IntegerProperty getSecurityCode()
    {
        return securityCode;
    }

    public StringProperty getError()
    {
        return error;
    }

    public StringProperty getPaymentCount()
    {
        return paymentCount;
    }

    public IntegerProperty getYearField()
    {
        return yearField;
    }

    public IntegerProperty getMonthField()
    {
        return monthField;
    }
}

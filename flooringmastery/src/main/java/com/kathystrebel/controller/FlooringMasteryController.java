package com.kathystrebel.controller;

import java.sql.Date;
import java.time.LocalDate;

import com.kathystrebel.dao.FMException;
import com.kathystrebel.dto.Order;
import com.kathystrebel.service.FlooringMasteryService;
import com.kathystrebel.ui.FlooringMasteryView;

public class FlooringMasteryController
{
    private FlooringMasteryView view;
    private FlooringMasteryService service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryService service)
    {
        this.view = view;
        this.service = service;
    }

    public void run()
    {
        boolean keepRunning = true;
        try
        {
            while (keepRunning)
            {
                view.printMenu();
                int operation = view.getMenuSelection();

                switch (operation)
                {
                    case 1: // display orders
                        displayOrders();
                        break;
                    case 2: // add order
                        addOrder();
                        break;
                    case 3: // edit order
                        editOrder();
                        break;
                    case 4: // remove order
                        removeOrder();
                        break;
                    case 5: // export orders
                        view.displayNotImplemented();
                        break;
                    case 6: // quit
                        view.displayQuitMessage();
                        keepRunning = false;
                        break;
                    default:
                        view.displayUnknownCommand();
                }
            }
        }
        catch (FMException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public void displayOrders() throws FMException
    {
        view.printAllOrders(service.listAllOrders(view.getDateSelection()));
    }

    public void addOrder() throws FMException
    {
        service.addOrder(view.getDateSelection(), view.getCustomerName(), view.getState(), view.getProductType(),
                view.getArea());
    }

    public void editOrder() throws FMException
    {
        LocalDate date = view.getDateSelection();
        int orderNumber = view.getOrderSelection();

        Order origOrder = service.getOrder(date, orderNumber);

        service.editOrder(date, orderNumber,
                view.getCustomerName(origOrder.getCustomerName()),
                view.getState(origOrder.getState()),
                view.getProductType(origOrder.getProductType()),
                view.getArea(origOrder.getArea().toString()));

        view.pause("Order number " + origOrder.getOrderNumber() + " was modified successfully.");
    }

    public void removeOrder() throws FMException
    {
        try
        {
            view.pause("Order from customer "
                    + service.removeOrder(view.getDateSelection(), view.getOrderSelection()).getCustomerName()
                    + " removed successfully.");
        }
        catch (FMException e)
        {
            view.displayErrorMessage("The order could not be removed. Please check information entered and try again.");
        }
    }
}

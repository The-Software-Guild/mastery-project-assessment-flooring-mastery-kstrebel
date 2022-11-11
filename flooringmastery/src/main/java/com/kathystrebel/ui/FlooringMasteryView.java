package com.kathystrebel.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.kathystrebel.dto.Order;

public class FlooringMasteryView
{
    private UserIO io;

    public FlooringMasteryView(UserIO io)
    {
        this.io = io;
    }

    public void printMenu()
    {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                "* <<Flooring Program>>\n" +
                "* 1. Display Orders\n" +
                "* 2. Add an Order\n" +
                "* 3. Edit an Order\n" +
                "* 4. Remove an Order\n" +
                "* 5. Export All Data\n" +
                "* 6. Quit\n" +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

    public int getMenuSelection()
    {
        return io.readInt("Please select a menu option.", 1, 6);
    }

    public LocalDate getDateSelection()
    {
        return io.readDate("Please enter a date to access orders. (mm/dd/yyyy)");
    }

    public int getOrderSelection()
    {
        return io.readInt("Please enter an order number.");
    }

    public String getCustomerName()
    {
        return io.readString("Enter customer name: ");
    }

    public String getCustomerName(String currentName)
    {
        return io.readString("Enter customer name (" + currentName + "): ");
    }

    public String getState()
    {
        return io.readString("Enter state:");
    }

    public String getState(String currentState)
    {
        return io.readString("Enter state (" + currentState + "):");
    }

    public String getProductType()
    {
        return io.readString("Enter product type:");
    }

    public String getProductType(String currentProductType)
    {
        return io.readString("Enter product type (" + currentProductType + "):");
    }

    public String getArea()
    {
        return io.readString("Enter area:");
    }

    public String getArea(String currentArea)
    {
        return io.readString("Enter area (" + currentArea + "):");
    }

    public ArrayList<String> printAllOrders(List<Order> orderList)
    {
        ArrayList<String> list = new ArrayList<>();

        list.add(0, "null");

        for (Order o : orderList)
        {
            printOrder(o);
        }

        return list;
    }

    public void printOrder(Order order)
    {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                "* Order Number " + order.getOrderNumber() + ":\n" +
                "* " + order.getCustomerName() + "\n" +
                "*   " + order.getArea() + "sqft of " + order.getProductType() + "\n" +
                "*   Materials: " + order.getMaterialCost() + " (" + order.getSqftCost() + "/sqft)" + "\n" +
                "*       Labor: " + order.getLaborCost() + " (" + order.getSqftLaborCost() + "/sqft)" + "\n" +
                "*         Tax: " + order.getTax() + " (" + order.getState() + " @ " + order.getTaxRate() + "%)" + "\n"
                +
                "*       Total: " + order.getTotal() + "\n" +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

    public void pause(String message)
    {
        io.readString(message + "\nPress enter to continue.");
    }

    public void displayQuitMessage()
    {
        io.print("Program closing. Goodbye!");
    }

    public void displayErrorMessage(String message)
    {
        io.print(message + "\n");
        io.readString("Please press enter to continue.");
    }

    public void displayUnknownCommand()
    {
        io.print("Invalid input. Please try again.");
    }

    public void displayNotImplemented()
    {
        pause("This feature is not implemented.");
    }
}

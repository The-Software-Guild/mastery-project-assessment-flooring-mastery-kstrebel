package com.kathystrebel.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.kathystrebel.dto.Order;
import com.kathystrebel.dto.Product;
import com.kathystrebel.dto.Tax;

public class FileDaoImpl implements FileDao
{
    private static final String DELIMITER = ",";
    private static final String ORDER_PATH = "files/Orders/Orders_";
    private static final String TAX_PATH = "files/Data/Taxes.txt";
    private static final String PRODUCT_PATH = "files/Data/Products.txt";

    @Override
    public String marshallOrder(Order order)
    {
        return order.getOrderNumber() + DELIMITER +
                order.getCustomerName() + DELIMITER +
                order.getState() + DELIMITER +
                order.getTaxRate() + DELIMITER +
                order.getProductType() + DELIMITER +
                order.getArea() + DELIMITER +
                order.getSqftCost() + DELIMITER +
                order.getSqftLaborCost() + DELIMITER +
                order.getMaterialCost() + DELIMITER +
                order.getLaborCost() + DELIMITER +
                order.getTax() + DELIMITER +
                order.getTotal();
    }

    @Override
    public Order unmarshallOrder(String line)
    {
        String[] attributes = line.split(DELIMITER);

        Order order = new Order(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4],
                attributes[5], attributes[6], attributes[7], attributes[8], attributes[9], attributes[10],
                attributes[11]);

        return order;
    }

    @Override
    public void writeOrders(LocalDate date, List<Order> list) throws FMException
    {
        try
        {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMddyyy");
            String datestr = date.format(dateFormatter);

            File file = getFile(ORDER_PATH + datestr + ".txt");

            if (list.size() < 1) // if file would be empty, delete file
            {
                file.delete();
            }
            else
            {
                PrintWriter output = new PrintWriter(new FileWriter(file));

                for (Order order : list)
                {
                    output.println(marshallOrder(order));
                }

                output.flush();

                output.close();
            }
        }
        catch (IOException e)
        {
            throw new FMException("Could not save order data", e);
        }
    }

    @Override
    public Map<Integer, Order> readOrders(LocalDate date) throws FMException
    {
        try
        {
            String datestr = dateToString(date);

            Scanner input = new Scanner(new BufferedReader(new FileReader(getFile(ORDER_PATH + datestr + ".txt"))));
            Map<Integer, Order> ordersMap = new HashMap<>();

            while (input.hasNextLine())
            {
                String line = input.nextLine();

                if (Character.isDigit(line.charAt(0))) // if first line is a heading
                {
                    Order order = unmarshallOrder(line);

                    ordersMap.put(order.getOrderNumber(), order);
                }
            }

            input.close();

            return ordersMap;
        }
        catch (FileNotFoundException e)
        {
            throw new FMException("File not found", e);
        }
    }

    @Override
    public int getNextOrderNumber(LocalDate date)
    {
        try
        {
            String datestr = dateToString(date);
            int highestNumber = 0;

            Scanner input = new Scanner(new BufferedReader(new FileReader(getFile(ORDER_PATH + datestr + ".txt"))));

            while (input.hasNextLine())
            {
                String line = input.nextLine();

                if (Character.isDigit(line.charAt(0))) // if first line is a heading
                {
                    Order order = unmarshallOrder(line);

                    int orderNumber = order.getOrderNumber();

                    if (highestNumber < orderNumber)
                    {
                        highestNumber = orderNumber;
                    }
                }
            }

            input.close();

            return ++highestNumber;
        }
        catch (Exception e)
        {
            return 1; // if there are no orders on date, start at 1
        }
    }

    @Override
    public File getFile(String path) throws FMException
    {
        try
        {
            File file = new File(path);

            if (!file.exists())
            {
                file.createNewFile();
            }
            return file;
        }
        catch (SecurityException e)
        {
            throw new FMException("File at path " + path + " could not be accessed.");
        }
        catch (IOException e)
        {
            throw new FMException("File at path " + path + " could not be accessed.");
        }
    }

    public String dateToString(LocalDate date)
    {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMddyyy");
        return date.format(dateFormatter);
    }

    @Override
    public Tax unmarshallTax(String line)
    {
        String[] attributes = line.split(DELIMITER);

        Tax tax = new Tax(attributes[0], attributes[1], attributes[2]);

        return tax;
    }

    @Override
    public Map<String, Tax> readTaxes() throws FMException
    {
        try
        {
            Scanner input = new Scanner(new BufferedReader(new FileReader(getFile(TAX_PATH))));
            Map<String, Tax> taxesMap = new HashMap<>();

            input.nextLine(); // skip header line

            while (input.hasNextLine())
            {
                String line = input.nextLine();

                Tax tax = unmarshallTax(line);

                taxesMap.put(tax.getStateShort(), tax);
            }

            input.close();

            return taxesMap;
        }
        catch (FileNotFoundException e)
        {
            throw new FMException("File not found", e);
        }
    }

    @Override
    public Product unmarshallProduct(String line)
    {
        String[] attributes = line.split(DELIMITER);

        Product product = new Product(attributes[0], attributes[1], attributes[2]);

        return product;
    }

    @Override
    public Map<String, Product> readProducts() throws FMException
    {
        try
        {
            Scanner input = new Scanner(new BufferedReader(new FileReader(getFile(PRODUCT_PATH))));
            Map<String, Product> productsMap = new HashMap<>();

            input.nextLine(); // skip header line

            while (input.hasNextLine())
            {
                String line = input.nextLine();

                Product product = unmarshallProduct(line);

                productsMap.put(product.getProductType(), product);
            }

            input.close();

            return productsMap;
        }
        catch (FileNotFoundException e)
        {
            throw new FMException("File not found", e);
        }
    }
}
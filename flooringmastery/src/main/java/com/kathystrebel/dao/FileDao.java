package com.kathystrebel.dao;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.kathystrebel.dto.Order;
import com.kathystrebel.dto.Product;
import com.kathystrebel.dto.Tax;

public interface FileDao
{
    public String marshallOrder(Order order);

    public Order unmarshallOrder(String line);

    public void writeOrders(LocalDate date, List<Order> list) throws FMException;

    public Map<Integer, Order> readOrders(LocalDate date) throws FMException;

    public int getNextOrderNumber(LocalDate date);

    public File getFile(String path) throws FMException;

    public Tax unmarshallTax(String line);

    public Map<String, Tax> readTaxes() throws FMException;

    public Product unmarshallProduct(String line);

    public Map<String, Product> readProducts() throws FMException;
}

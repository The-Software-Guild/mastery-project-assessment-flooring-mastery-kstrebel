package com.kathystrebel.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.kathystrebel.dto.Order;

public interface FlooringMasteryDao
{
    public Order getOrder(LocalDate date, int orderNumber) throws FMException;

    public List<Order> listAllOrders(LocalDate date) throws FMException;

    public Order addOrder(LocalDate date, String customerName, String state, String productType, String area) throws FMException;

    public Order editOrder(LocalDate date, int orderNumber, String customerName, String state, String productType,
            String area) throws FMException;

    public Order removeOrder(LocalDate date, int orderNumber) throws FMException;
}

package com.kathystrebel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.kathystrebel.dao.FMException;
import com.kathystrebel.dao.FlooringMasteryDao;
import com.kathystrebel.dao.FlooringMasteryDaoImpl;
import com.kathystrebel.dto.Order;

public class FlooringMasteryServiceImpl implements FlooringMasteryService
{
    private FlooringMasteryDao dao;

    public FlooringMasteryServiceImpl() throws FMException
    {
        this.dao = new FlooringMasteryDaoImpl();
    }

    public FlooringMasteryServiceImpl(FlooringMasteryDao dao)
    {
        this.dao = dao;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws FMException
    {
        return dao.getOrder(date, orderNumber);
    }

    @Override
    public List<Order> listAllOrders(LocalDate date) throws FMException
    {
        return dao.listAllOrders(date)
                .stream()
                .filter(o -> o.getOrderNumber() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Order addOrder(LocalDate date, String customerName, String state, String productType, String area) throws FMException
    {
        return dao.addOrder(date, customerName, state, productType, area);
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, String customerName, String state, String productType,
            String area) throws FMException
    {
        return dao.editOrder(date, orderNumber, customerName, state, productType, area);
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws FMException
    {
        return dao.removeOrder(date, orderNumber);
    }

    // private Order findOrder(LocalDate date, int orderNumber) throws FMException
    // {
    //     try
    //     {
    //         List<Order> list = dao.listAllOrders(date).stream().filter(o -> o.getOrderNumber() == orderNumber)
    //                 .collect(Collectors.toList());

    //         return list.get(0);
    //     }
    //     catch (IndexOutOfBoundsException e)
    //     {
    //         throw new FMException("Count not find order number " + orderNumber + " on date " + date.toString());
    //     }
    // }
}

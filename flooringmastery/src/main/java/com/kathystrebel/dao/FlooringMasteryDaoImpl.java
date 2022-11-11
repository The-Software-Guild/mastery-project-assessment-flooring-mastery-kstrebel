package com.kathystrebel.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Bidi;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kathystrebel.dto.Order;
import com.kathystrebel.dto.Product;
import com.kathystrebel.dto.Tax;

public class FlooringMasteryDaoImpl implements FlooringMasteryDao
{
    Map<Integer, Order> orderMap;
    Map<String, Tax> taxMap;
    Map<String, Product> productMap;
    FileDao fio;

    public FlooringMasteryDaoImpl() throws FMException
    {
        fio = new FileDaoImpl();
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws FMException
    {
        try
        {
            orderMap = fio.readOrders(date);
            Order order = orderMap.get(orderNumber);
            order.getArea(); // test that it's valid

            return order;
        }
        catch (NullPointerException e)
        {
            throw new FMException("Could not find order number " + orderNumber + " on " + date.toString());
        }
    }

    @Override
    public List<Order> listAllOrders(LocalDate date) throws FMException
    {
        orderMap = fio.readOrders(date);

        return new ArrayList<>(orderMap.values());
    }

    public Order buildOrder(LocalDate date, String customerName, Tax tax, Product product, BigDecimal area)
    {
        BigDecimal sqftCost, sqftLaborCost, materialCost, laborCost, totalPreTax, totalTax;

        sqftCost = product.getSqftCost();
        sqftLaborCost = product.getSqftLaborCost();
        materialCost = sqftCost.multiply(area).setScale(2, RoundingMode.HALF_UP);
        laborCost = sqftLaborCost.multiply(area).setScale(2, RoundingMode.HALF_UP);
        totalPreTax = materialCost.add(laborCost).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
        totalTax = totalPreTax.multiply(tax.getRate()).setScale(2, RoundingMode.HALF_UP);

        return new Order(fio.getNextOrderNumber(date),
                customerName,
                tax.getStateShort(),
                tax.getRate(),
                product.getProductType(),
                area,
                sqftCost,
                sqftLaborCost,
                materialCost,
                laborCost,
                totalTax,
                totalPreTax.add(totalTax).setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public Order addOrder(LocalDate date, String customerName, String state, String productType, String area)
            throws FMException
    {
        orderMap = fio.readOrders(date);
        Order order = buildOrder(date, customerName, getTax(state), getProduct(productType),
                new BigDecimal(area).setScale(2, RoundingMode.HALF_UP));

        Order addedOrder = orderMap.put(order.getOrderNumber(), order);
        fio.writeOrders(date, new ArrayList<Order>(orderMap.values()));

        return addedOrder;
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, String customerName, String state, String productType,
            String area) throws FMException
    {
        Order order = getOrder(date, orderNumber);

        Order newOrder = buildOrder(date,
                customerName.isBlank() ? order.getCustomerName() : customerName,
                state.isBlank() ? getTax(order.getState()) : getTax(state.toUpperCase()),
                productType.isBlank() ? getProduct(order.getProductType()) : getProduct(productType),
                area.isBlank() ? order.getArea() : new BigDecimal(area).setScale(2, RoundingMode.HALF_UP));

        newOrder.setOrderNumber(orderNumber);

        newOrder = orderMap.put(newOrder.getOrderNumber(), newOrder);
        fio.writeOrders(date, new ArrayList<Order>(orderMap.values()));

        return newOrder;
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws FMException
    {
        Order order = getOrder(date, orderNumber);

        orderMap = fio.readOrders(date);
        Order newOrderMap = orderMap.remove(order.getOrderNumber());
        fio.writeOrders(date, new ArrayList<Order>(orderMap.values()));

        return newOrderMap;
    }

    public Tax getTax(String stateShort) throws FMException
    {
        try
        {
            taxMap = fio.readTaxes();
            Tax tax = taxMap.get(stateShort);
            tax.getRate(); // test that it's valid

            return tax;
        }
        catch (NullPointerException e)
        {
            throw new FMException("Could not find a state with the abbreviation " + stateShort + " on file.");
        }
    }

    public List<Tax> listAllTaxes() throws FMException
    {
        taxMap = fio.readTaxes();

        return new ArrayList<>(taxMap.values());
    }

    public Product getProduct(String productType) throws FMException
    {
        try
        {
            productMap = fio.readProducts();
            Product product = productMap.get(productType);
            product.getSqftCost(); // test that it's valid

            return product;
        }
        catch (NullPointerException e)
        {
            throw new FMException("Could not find the product " + productType + " on file.");
        }
    }

    public List<Product> listAllProducts() throws FMException
    {
        productMap = fio.readProducts();

        return new ArrayList<>(productMap.values());
    }
}
package com.kathystrebel.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Order
{
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal sqftCost;
    private BigDecimal sqftLaborCost;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;

    public Order()
    {
    }

    public Order(int orderNumber, String customerName, String state, BigDecimal taxRate, String productType,
            BigDecimal area, BigDecimal sqftCost, BigDecimal sqftLaborCost, BigDecimal materialCost,
            BigDecimal laborCost, BigDecimal tax, BigDecimal total)
    {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.sqftCost = sqftCost;
        this.sqftLaborCost = sqftLaborCost;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.total = total;
    }

    public Order(String orderNumberString, String customerName, String state, String taxRateString, String productType,
            String areaString, String sqftCostString, String sqftLaborCostString, String materialCostString,
            String laborCostString, String taxString, String totalString)
    {
        this.orderNumber = Integer.parseInt(orderNumberString);
        this.customerName = customerName;
        this.state = state;
        this.taxRate = new BigDecimal(taxRateString).setScale(2, RoundingMode.HALF_UP);
        this.productType = productType;
        this.area = new BigDecimal(areaString).setScale(2, RoundingMode.HALF_UP);
        this.sqftCost = new BigDecimal(sqftCostString).setScale(2, RoundingMode.HALF_UP);
        this.sqftLaborCost = new BigDecimal(sqftLaborCostString).setScale(2, RoundingMode.HALF_UP);
        this.materialCost = new BigDecimal(materialCostString).setScale(2, RoundingMode.HALF_UP);
        this.laborCost = new BigDecimal(laborCostString).setScale(2, RoundingMode.HALF_UP);
        this.tax = new BigDecimal(taxString).setScale(2, RoundingMode.HALF_UP);
        this.total = new BigDecimal(totalString).setScale(2, RoundingMode.HALF_UP);
    }

    public int getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public BigDecimal getTaxRate()
    {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    public BigDecimal getArea()
    {
        return area;
    }

    public void setArea(BigDecimal area)
    {
        this.area = area;
    }

    public void setArea(String area)
    {
        this.area = new BigDecimal(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSqftCost()
    {
        return sqftCost;
    }

    public void setSqftCost(BigDecimal sqftCost)
    {
        this.sqftCost = sqftCost;
    }

    public BigDecimal getSqftLaborCost()
    {
        return sqftLaborCost;
    }

    public void setSqftLaborCost(BigDecimal sqftLaborCost)
    {
        this.sqftLaborCost = sqftLaborCost;
    }

    public BigDecimal getMaterialCost()
    {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost)
    {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost()
    {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost)
    {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax()
    {
        return tax;
    }

    public void setTax(BigDecimal tax)
    {
        this.tax = tax;
    }

    public BigDecimal getTotal()
    {
        return total;
    }

    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + orderNumber;
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((taxRate == null) ? 0 : taxRate.hashCode());
        result = prime * result + ((productType == null) ? 0 : productType.hashCode());
        result = prime * result + ((area == null) ? 0 : area.hashCode());
        result = prime * result + ((sqftCost == null) ? 0 : sqftCost.hashCode());
        result = prime * result + ((sqftLaborCost == null) ? 0 : sqftLaborCost.hashCode());
        result = prime * result + ((materialCost == null) ? 0 : materialCost.hashCode());
        result = prime * result + ((laborCost == null) ? 0 : laborCost.hashCode());
        result = prime * result + ((tax == null) ? 0 : tax.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (orderNumber != other.orderNumber)
            return false;
        if (customerName == null)
        {
            if (other.customerName != null)
                return false;
        }
        else if (!customerName.equals(other.customerName))
            return false;
        if (state == null)
        {
            if (other.state != null)
                return false;
        }
        else if (!state.equals(other.state))
            return false;
        if (taxRate == null)
        {
            if (other.taxRate != null)
                return false;
        }
        else if (!taxRate.equals(other.taxRate))
            return false;
        if (productType == null)
        {
            if (other.productType != null)
                return false;
        }
        else if (!productType.equals(other.productType))
            return false;
        if (area == null)
        {
            if (other.area != null)
                return false;
        }
        else if (!area.equals(other.area))
            return false;
        if (sqftCost == null)
        {
            if (other.sqftCost != null)
                return false;
        }
        else if (!sqftCost.equals(other.sqftCost))
            return false;
        if (sqftLaborCost == null)
        {
            if (other.sqftLaborCost != null)
                return false;
        }
        else if (!sqftLaborCost.equals(other.sqftLaborCost))
            return false;
        if (materialCost == null)
        {
            if (other.materialCost != null)
                return false;
        }
        else if (!materialCost.equals(other.materialCost))
            return false;
        if (laborCost == null)
        {
            if (other.laborCost != null)
                return false;
        }
        else if (!laborCost.equals(other.laborCost))
            return false;
        if (tax == null)
        {
            if (other.tax != null)
                return false;
        }
        else if (!tax.equals(other.tax))
            return false;
        if (total == null)
        {
            if (other.total != null)
                return false;
        }
        else if (!total.equals(other.total))
            return false;
        return true;
    }
}
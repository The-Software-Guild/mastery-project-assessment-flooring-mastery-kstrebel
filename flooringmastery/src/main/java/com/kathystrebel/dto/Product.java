package com.kathystrebel.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product
{
    private String productType;
    private BigDecimal sqftCost;
    private BigDecimal sqftLaborCost;

    public Product()
    {
    }

    public Product(String productType, BigDecimal sqftCost, BigDecimal sqftLaborCost)
    {
        this.productType = productType;
        this.sqftCost = sqftCost;
        this.sqftLaborCost = sqftLaborCost;
    }

    public Product(String productType, String sqftCost, String sqftLaborCost)
    {
        this.productType = productType;
        this.sqftCost = new BigDecimal(sqftCost).setScale(2, RoundingMode.HALF_UP);
        this.sqftLaborCost = new BigDecimal(sqftLaborCost).setScale(2, RoundingMode.HALF_UP);
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    public BigDecimal getSqftCost()
    {
        return sqftCost;
    }

    public void setSqftCost(BigDecimal sqftCost)
    {
        this.sqftCost = sqftCost;
    }

    public void setSqftCost(String sqftCost)
    {
        this.sqftCost = new BigDecimal(sqftCost).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSqftLaborCost()
    {
        return sqftLaborCost;
    }

    public void setSqftLaborCost(BigDecimal sqftLaborCost)
    {
        this.sqftLaborCost = sqftLaborCost;
    }

    public void setSqftLaborCost(String sqftLaborCost)
    {
        this.sqftLaborCost = new BigDecimal(sqftLaborCost).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productType == null) ? 0 : productType.hashCode());
        result = prime * result + ((sqftCost == null) ? 0 : sqftCost.hashCode());
        result = prime * result + ((sqftLaborCost == null) ? 0 : sqftLaborCost.hashCode());
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
        Product other = (Product) obj;
        if (productType == null)
        {
            if (other.productType != null)
                return false;
        }
        else if (!productType.equals(other.productType))
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
        return true;
    }
}

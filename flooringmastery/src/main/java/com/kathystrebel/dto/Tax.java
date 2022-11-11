package com.kathystrebel.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tax
{
    private String stateShort;
    private String stateLong;
    private BigDecimal rate;

    public Tax()
    {
    }

    public Tax(String stateShort, String stateLong, String rate)
    {
        this.stateShort = stateShort;
        this.stateLong = stateLong;
        this.rate = new BigDecimal(rate).setScale(2, RoundingMode.HALF_UP);
    }

    public String getStateShort()
    {
        return stateShort;
    }

    public void setStateShort(String stateShort)
    {
        this.stateShort = stateShort;
    }

    public String getStateLong()
    {
        return stateLong;
    }

    public void setStateLong(String stateLong)
    {
        this.stateLong = stateLong;
    }

    public BigDecimal getRate()
    {
        return rate;
    }

    public void setRate(BigDecimal rate)
    {
        this.rate = rate;
    }

    public void setRate(String rate)
    {
        this.rate = new BigDecimal(rate).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stateShort == null) ? 0 : stateShort.hashCode());
        result = prime * result + ((stateLong == null) ? 0 : stateLong.hashCode());
        result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
        Tax other = (Tax) obj;
        if (stateShort == null)
        {
            if (other.stateShort != null)
                return false;
        }
        else if (!stateShort.equals(other.stateShort))
            return false;
        if (stateLong == null)
        {
            if (other.stateLong != null)
                return false;
        }
        else if (!stateLong.equals(other.stateLong))
            return false;
        if (rate == null)
        {
            if (other.rate != null)
                return false;
        }
        else if (!rate.equals(other.rate))
            return false;
        return true;
    }
}

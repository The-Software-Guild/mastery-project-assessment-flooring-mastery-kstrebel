package com.kathystrebel.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface UserIO
{
    public void print(String message);

    public String readString(String prompt);

    public int readInt(String prompt);

    public int readInt(String prompt, int min, int max);

    public float readFloat(String prompt);

    public float readFloat(String prompt, float min, float max);

    public LocalDate readDate(String prompt);

    public BigDecimal readBigDecimal(String prompt);

    public BigDecimal readBigDecimal(String prompt, BigDecimal min);
}

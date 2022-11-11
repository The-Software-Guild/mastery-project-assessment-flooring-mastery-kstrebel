package com.kathystrebel.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class UserIOImpl implements UserIO
{
    Scanner sc;

    public UserIOImpl()
    {
        sc = new Scanner(System.in);
    }

    @Override
    public void print(String message)
    {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt)
    {
        System.out.println(prompt);
        return sc.nextLine();
    }

    @Override
    public int readInt(String prompt)
    {
        System.out.println(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max)
    {
        int num;

        do
        {
            System.out.println(prompt);
            num = Integer.parseInt(sc.nextLine());
        }
        while (num < min || num > max);

        return num;
    }

    @Override
    public float readFloat(String prompt)
    {
        System.out.println(prompt);
        return sc.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max)
    {
        float num;

        do
        {
            System.out.println(prompt);
            num = sc.nextFloat();
        }
        while (num < min || num > max);

        return num;
    }

    @Override
    public LocalDate readDate(String prompt)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        formatter = formatter.withLocale(Locale.US);
        LocalDate date = null;

        System.out.println(prompt);

        while (date == null)
        {
            try
            {
                String input = sc.nextLine();

                date = LocalDate.parse(input, formatter);

            }
            catch (DateTimeParseException e)
            {
                System.out.println("Date entered was not in the proper format (mm/dd/yyyy). Please try again.");

                date = null;
            }
        }

        return date;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt)
    {
        System.out.println(prompt);
        return new BigDecimal(sc.nextLine());
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min)
    {
        BigDecimal num;

        do
        {
            System.out.println(prompt);
            num = new BigDecimal(sc.nextLine());
        }
        while (num.doubleValue() < min.doubleValue() || num == null);

        return num;
    }
}

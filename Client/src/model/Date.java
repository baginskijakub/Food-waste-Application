package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Date implements Serializable
{
  private int day;
  private int month;
  private int year;

  public Date(int day, int month, int year)
  {
    set(day, month, year);
  }
  public Date()
  {
    LocalDate localDate = LocalDate.now();
    day = localDate.getDayOfMonth();
    month = localDate.getMonthValue();
    year = localDate.getYear();
  }


  public Date(String dateString)
  {
    String[] arr = dateString.split("-");
    int year = Integer.parseInt(arr[0]);
    int month = Integer.parseInt(arr[1]);
    int day = Integer.parseInt(arr[2]);

    set(day,month,year);

  }


  public void set(int day, int month, int year)
  {
    if(year < 0)
    {
      year = -year;
    }
    if(month > 12)
    {
      month = 12;
    }
    if(day < 0)
    {
      day = 1;
    }
    switch (month)
    {
      case 1: case 3: case 5: case 7: case 8: case 10: case 12:
      if(day > 31)
      {
        throw new IllegalArgumentException("This month has only 31 days!");
      }
      break;
      case 4: case 6: case 9: case 11:
      if(day > 30)
      {
        throw new IllegalArgumentException("This month has only 30 days!");
      }
      break;
      case 2:
        if(year % 4 == 0 || year % 400 == 0)
        {
          if(day > 29)
          {
            throw new IllegalArgumentException("February has only 29 days this year!");
          }
        }
        else if(day > 28)
        {
          throw new IllegalArgumentException("February has only 28 days this year!");
        }
        break;
    }
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public String toString()
  {
    return day + "-" + month + "-" + year;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Date))
    {
      return false;
    }
    Date other = (Date) obj;
    return this.day == other.day && this.month == other.month && this.year == other.year;
  }

  public boolean isBefore(Date other)
  {
    return (this.year < other.year || (this.year == other.year && (
        (this.month < other.month
            || (this.month == other.month && this.day < other.day)))));
  }

  public int daysBetween(Date obj)
  {
    boolean condition = true;
    Date date1;
    Date date2;
    int result = 0;
    if(this.isBefore(obj))
    {
      date1 = new Date(this.day, this.month, this.year);
      date2 = obj;
    }
    else
    {
      date1 = obj;
      date2 = new Date(this.day, this.month, this.year);
    }
    while(date1.isBefore(date2))
    {
      result++;
      date1.stepForwardOneDay();
    }
    return result;
  }

  public void stepForwardOneDay()
  {
    day++;
    if (day > numberOfDaysInMonth())
    {
      day = 1;
      month++;
      if (month > 12)
      {
        month = 1;
        year++;
      }
    }
  }

  public int numberOfDaysInMonth()
  {
    switch (month)
    {
      case 2:
        if (isLeapYear())
        {
          return 29;
        }
        return 28;
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      default:
        return 31;
    }
  }

  public boolean isLeapYear()
  {
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
  }

  public String getDatabaseFormat()
  {
    String s = year + "-";
    if (month < 10)
    {
      s += "0";
    }
    s += month + "-";
    if (day < 10)
    {
      s += "0";
    }
    s+=day;

    return s;
  }
}

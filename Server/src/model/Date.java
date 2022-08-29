package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class that represents date with instance variables day, month and year of type int.
 */
public class Date implements Serializable
{
  private int day;
  private int month;
  private int year;

  /**
   * A constructor taking 3 arguments
   * @param day
   * @param month
   * @param year
   */
  public Date(int day, int month, int year)
  {
    set(day, month, year);
  }

  /**
   * A constructor taking no arguments. Sets date object to current date.
   */
  public Date()
  {
    LocalDate localDate = LocalDate.now();
    day = localDate.getDayOfMonth();
    month = localDate.getMonthValue();
    year = localDate.getYear();
  }

  /**
   * Sets string in format YYYY-MM-DD to this Date object.
   * @param dateString format YYYY-MM-DD
   */
  public Date(String dateString)
  {
    String[] arr = dateString.split("-");
    int year = Integer.parseInt(arr[0]);
    int month = Integer.parseInt(arr[1]);
    int day = Integer.parseInt(arr[2]);

    set(day,month,year);

  }

  /**
   * Setter
   * @param day
   * @param month
   * @param year
   */
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

  /**
   * Converts date object to String in DD-MM-YYYY format.
   * @return  String in DD-MM-YYYY format.
   */
  public String toString()
  {
    return day + "-" + month + "-" + year;
  }

  /**
   * Compares passed variable with this object.
   *
   * @param obj
   * @return true if passes object is the same as this object
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Date))
    {
      return false;
    }
    Date other = (Date) obj;
    return this.day == other.day && this.month == other.month && this.year == other.year;
  }

  /**
   * Calculates if this object is before passed object
   * @param other
   * @return true if passed object is before this object
   */
  public boolean isBefore(Date other)
  {
    return (this.year < other.year || (this.year == other.year && (
        (this.month < other.month
            || (this.month == other.month && this.day < other.day)))));
  }

  /**
   * Calculates how many days are there between this Date object and passed object.
   * @param obj Date object
   * @return  days between two dates
   */
  public int daysBetween(Date obj)
  {
    boolean condition = true;
    Date date1 = new Date(1,1,1);
    Date date2 = new Date(1,1,1);
    int result = 0;
    if(this.isBefore(obj))
    {
      date1 = this;
      date2 = obj;
    }
    else
    {
      date1 = obj;
      date2 = this;
    }
    while(date1.isBefore(date2))
    {
      result++;
      date1.stepForwardOneDay();
    }
    return result;
  }

  /**
   * Increments date by one day
   */
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

  /**
   * Calculates how many days are in this object's month
   * @return number of days in a given month
   */
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

  /**
   * Calculates if year in this object is a leap year
   * @return true if year is leap
   */
  public boolean isLeapYear()
  {
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
  }

  /**
   * Converts this object to String in format YYYY-MM-DD
   * @return String in format YYYY-MM-DD
   */
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

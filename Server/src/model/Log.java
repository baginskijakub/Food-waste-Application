package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for logging actions on server side into console and the text file
 */
public class Log
{

  private String filename;
  private static Map<String, Log> map = new HashMap<>();

  /**
   * Private constructor as the class is a Multiton, taking 1 argument
   *
   * @param filename name of the file where the logs will be stored
   */
  private Log(String filename)
  {
    this.filename = filename + ".txt";
  }

  /**
   * Method printing the log into the console and saving it into the text file
   *
   * @param text String containing message of the log
   */

  public void addLog(String text)
  {
    addToFile(text);
    System.out.println(text);
  }

  /**
   * Method returning instance of this class based on provided key
   *
   * @param key String containing date identifying instance of the class
   * @return instance of Log class corresponding to passed key
   */

  public static Log getInstance(String key)
  {
    Log instance = map.get(key);
    if (instance == null)
    {
      synchronized (map)
      {
        instance = map.get(key);
        if (instance == null)
        {
          instance = new Log(key);
          map.put(key, instance);
        }
      }
    }
    return instance;
  }

  /**
   * Method saving the log into the text file for later access
   *
   * @param log String containing message of the log
   */
  private void addToFile(String log)
  {
    if (log == null)
    {
      return;
    }
    BufferedWriter out = null;
    try
    {

      out = new BufferedWriter(new FileWriter(filename, true));
      out.write(log + "\n");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}

package ru.itpark.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kamila Iskhakova
 *         Created on 16.11.2016
 */
public class RequestUtil {

  public static Long getId(String id) {
    if (id == null) {
      return null;
    }
    Pattern p = Pattern.compile("^\\d+$");
    Matcher m = p.matcher(id);
    return m.matches() ? Long.parseLong(id) : null;
  }
}

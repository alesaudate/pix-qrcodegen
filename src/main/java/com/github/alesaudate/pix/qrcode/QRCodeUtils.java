package com.github.alesaudate.pix.qrcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.regex.Pattern.compile;

public class QRCodeUtils {

  private QRCodeUtils() {}

  private static final Pattern ALPHANUMERIC_PATTERN = compile("^[a-zA-Z\\d]+$");

  private static final Pattern ALPHANUMERIC_PATTERN_WITH_SPACES = compile("^[a-zA-Z\\d\\s]+$");
  private static final Pattern NUMERIC_PATTERN = compile("^\\d+$");
  private static final Pattern KEY_PATTERN = compile("^[a-zA-Z\\d\\-*]+$");
  private static final Pattern URL_PATTERN = compile("^[a-zA-Z\\d\\-./]+$");

  public static String getLengthWithTwoDigits(String string) {
    if (string.length() > 99) {
      throw new IllegalArgumentException(
          format("The provided string is greater than 99 characters: %s", string));
    }
    return leftPad(String.valueOf(string.length()), 2);
  }

  public static String crc16(String inputString) {
    final int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)

    int result = 0xFFFF; // initial value

    final byte[] bytes = inputString.getBytes();

    for (final byte b : bytes) {
      for (int i = 0; i < 8; i++) {
        final boolean bit = (b >> 7 - i & 1) == 1;
        final boolean c15 = (result >> 15 & 1) == 1;
        result <<= 1;
        if (c15 ^ bit) {
          result ^= polynomial;
        }
      }
    }

    result &= 0xffff;

    return leftPad(Integer.toHexString(result).toUpperCase(), 4);
  }

  public static boolean validateStringAlphanumeric(String string) {
    return validateStringUsingPattern(string, ALPHANUMERIC_PATTERN);
  }

  public static boolean validateStringAlphanumericIncludingSpaces(String string) {
    return validateStringUsingPattern(string, ALPHANUMERIC_PATTERN_WITH_SPACES);
  }

  public static boolean validateKey(String key) {
    return validateStringUsingPattern(key, KEY_PATTERN);
  }

  public static boolean validateURL(String url) {
    return validateStringUsingPattern(url, URL_PATTERN);
  }

  public static boolean validateNumeric(String string) {
    return validateStringUsingPattern(string, NUMERIC_PATTERN);
  }

  private static boolean validateStringUsingPattern(String string, Pattern pattern) {
    return ofNullable(string).map(pattern::matcher).map(Matcher::matches).orElse(false);
  }

  private static String leftPad(String string, int size) {
    int missingSize = size - string.length();
    StringBuilder padding = new StringBuilder();
    while (padding.length() < missingSize) {
      padding.append("0");
    }
    return padding.append(string).toString();
  }
}

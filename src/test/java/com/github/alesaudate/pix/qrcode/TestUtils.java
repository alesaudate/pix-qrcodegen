package com.github.alesaudate.pix.qrcode;

public class TestUtils {

  public static String generateLongString(int length) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int charCode = (i % 26) + 'a';
      builder.append((char) charCode);
    }
    return builder.toString();
  }
}

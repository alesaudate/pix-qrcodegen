package com.github.alesaudate.pix.qrcode;

public enum Country {
  BRAZIL("BR"),
  ;

  private final String value;

  Country(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}

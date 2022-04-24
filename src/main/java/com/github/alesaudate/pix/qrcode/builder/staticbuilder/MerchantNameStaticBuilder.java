package com.github.alesaudate.pix.qrcode.builder.staticbuilder;

import com.github.alesaudate.pix.qrcode.StaticQRCode;
import com.github.alesaudate.pix.qrcode.builder.MerchantCityBuilder;

public class MerchantNameStaticBuilder {
  private final StaticQRCode staticQRCode;

  public MerchantNameStaticBuilder(StaticQRCode staticQRCode) {
    this.staticQRCode = staticQRCode;
  }

  public MerchantCityBuilder merchantName(String merchantName) {
    staticQRCode.setMerchantName(merchantName);
    return new MerchantCityBuilder(this.staticQRCode);
  }
}

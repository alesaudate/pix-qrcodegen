package com.github.alesaudate.pix.qrcode.builder.staticbuilder;

import com.github.alesaudate.pix.qrcode.StaticQRCode;

public class MerchantCategoryCodeBuilder {
  private final StaticQRCode staticQRCode;

  public MerchantCategoryCodeBuilder(StaticQRCode staticQRCode) {

    this.staticQRCode = staticQRCode;
  }

  public MerchantNameStaticBuilder merchantCategoryCode(String merchantCategoryCode) {
    this.staticQRCode.setMerchantCategoryCode(merchantCategoryCode);
    return new MerchantNameStaticBuilder(this.staticQRCode);
  }

  public MerchantNameStaticBuilder unknownMerchantCategoryCode() {
    return merchantCategoryCode("0000");
  }
}

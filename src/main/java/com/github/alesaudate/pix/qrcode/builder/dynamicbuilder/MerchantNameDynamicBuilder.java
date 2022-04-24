package com.github.alesaudate.pix.qrcode.builder.dynamicbuilder;

import com.github.alesaudate.pix.qrcode.DynamicQRCode;

public class MerchantNameDynamicBuilder {

  private final DynamicQRCode dynamicQRCode;

  public MerchantNameDynamicBuilder(DynamicQRCode dynamicQRCode) {
    this.dynamicQRCode = dynamicQRCode;
  }

  public AdditionalDataFieldBuilder merchantName(String merchantName) {
    this.dynamicQRCode.setMerchantName(merchantName);
    return new AdditionalDataFieldBuilder(dynamicQRCode);
  }
}

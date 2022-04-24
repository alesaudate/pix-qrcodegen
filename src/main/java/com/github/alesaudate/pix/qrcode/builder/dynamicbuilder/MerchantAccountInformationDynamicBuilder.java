package com.github.alesaudate.pix.qrcode.builder.dynamicbuilder;

import com.github.alesaudate.pix.qrcode.DynamicQRCode;

public class MerchantAccountInformationDynamicBuilder {

  private final DynamicQRCode dynamicQRCode;

  public MerchantAccountInformationDynamicBuilder(DynamicQRCode dynamicQRCode) {
    this.dynamicQRCode = dynamicQRCode;
  }

  public MerchantCategoryCodeDynamicBuilder url(String url) {
    dynamicQRCode.setMerchantAccountInformationURL(url);
    return new MerchantCategoryCodeDynamicBuilder(dynamicQRCode);
  }
}

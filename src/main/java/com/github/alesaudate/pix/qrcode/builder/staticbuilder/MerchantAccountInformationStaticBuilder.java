package com.github.alesaudate.pix.qrcode.builder.staticbuilder;

import com.github.alesaudate.pix.qrcode.StaticQRCode;

public class MerchantAccountInformationStaticBuilder {

  private final StaticQRCode staticQRCode;

  MerchantAccountInformationStaticBuilder(StaticQRCode staticQRCode) {
    this.staticQRCode = staticQRCode;
  }

  public MerchantCategoryCodeBuilder merchantKey(String merchantKey) {
    staticQRCode.setMerchantAccountInformationKey(merchantKey);
    return new MerchantCategoryCodeBuilder(this.staticQRCode);
  }
}

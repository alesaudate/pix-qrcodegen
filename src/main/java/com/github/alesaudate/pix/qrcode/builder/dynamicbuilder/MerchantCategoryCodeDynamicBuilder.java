package com.github.alesaudate.pix.qrcode.builder.dynamicbuilder;

import static com.github.alesaudate.pix.qrcode.QRCode.MERCHANT_CATEGORY_CODE_UNKNOWN;

import com.github.alesaudate.pix.qrcode.DynamicQRCode;

public class MerchantCategoryCodeDynamicBuilder {

  private final DynamicQRCode dynamicQRCode;

  public MerchantCategoryCodeDynamicBuilder(DynamicQRCode dynamicQRCode) {
    this.dynamicQRCode = dynamicQRCode;
  }

  public TransactionAmountBuilder merchantCategoryCode(String merchantCategoryCode) {
    dynamicQRCode.setMerchantCategoryCode(merchantCategoryCode);
    return new TransactionAmountBuilder(dynamicQRCode);
  }

  public TransactionAmountBuilder unknownMerchantCategoryCode() {
    return merchantCategoryCode(MERCHANT_CATEGORY_CODE_UNKNOWN);
  }
}

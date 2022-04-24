package com.github.alesaudate.pix.qrcode.builder.dynamicbuilder;

import com.github.alesaudate.pix.qrcode.DynamicQRCode;
import java.math.BigDecimal;

public class TransactionAmountBuilder {

  private final DynamicQRCode dynamicQRCode;

  public TransactionAmountBuilder(DynamicQRCode dynamicQRCode) {
    this.dynamicQRCode = dynamicQRCode;
  }

  public MerchantNameDynamicBuilder transactionAmount(String transactionAmount) {
    return transactionAmount(new BigDecimal(transactionAmount));
  }

  public MerchantNameDynamicBuilder transactionAmount(BigDecimal transactionAmount) {
    dynamicQRCode.setTransactionAmount(transactionAmount);
    return new MerchantNameDynamicBuilder(dynamicQRCode);
  }

  public MerchantNameDynamicBuilder transactionAmount(Double transactionAmount) {
    return transactionAmount(new BigDecimal(transactionAmount));
  }
}

package com.github.alesaudate.pix.qrcode.builder.staticbuilder;

import com.github.alesaudate.pix.qrcode.StaticQRCode;

import java.math.BigDecimal;

public class StaticQRCodeBuilder {

  private final StaticQRCode staticQRCode;

  public StaticQRCodeBuilder(StaticQRCode staticQRCode) {
    this.staticQRCode = staticQRCode;
  }

  public StaticQRCodeBuilder transactionAmount(String transactionAmount) {
    return transactionAmount(new BigDecimal(transactionAmount));
  }

  public StaticQRCodeBuilder transactionAmount(BigDecimal transactionAmount) {
    staticQRCode.setTransactionAmount(transactionAmount);
    return this;
  }

  public MerchantAccountInformationStaticBuilder merchantAccountInformation() {
    return new MerchantAccountInformationStaticBuilder(this.staticQRCode);
  }
}

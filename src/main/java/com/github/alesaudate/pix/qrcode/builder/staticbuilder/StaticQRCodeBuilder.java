package com.github.alesaudate.pix.qrcode.builder.staticbuilder;

import com.github.alesaudate.pix.qrcode.StaticQRCode;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class StaticQRCodeBuilder {

  private final StaticQRCode staticQRCode;

  public StaticQRCodeBuilder(StaticQRCode staticQRCode) {
    this.staticQRCode = staticQRCode;
  }

  public StaticQRCodeBuilder transactionAmount(String transactionAmount) {
    return transactionAmount(
        new BigDecimal(transactionAmount).setScale(2, RoundingMode.UNNECESSARY));
  }

  public StaticQRCodeBuilder transactionAmount(BigDecimal transactionAmount) {
    staticQRCode.setTransactionAmount(transactionAmount.setScale(2, RoundingMode.UNNECESSARY));
    return this;
  }

  public MerchantAccountInformationStaticBuilder merchantAccountInformation() {
    return new MerchantAccountInformationStaticBuilder(this.staticQRCode);
  }
}

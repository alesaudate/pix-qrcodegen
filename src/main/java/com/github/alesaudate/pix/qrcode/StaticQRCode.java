package com.github.alesaudate.pix.qrcode;

import static com.github.alesaudate.pix.qrcode.QRCodeUtils.validateKey;
import static com.github.alesaudate.pix.qrcode.QRCodeUtils.validateStringAlphanumericIncludingSpaces;

import java.util.Set;

public class StaticQRCode extends QRCode {

  private static final String UNDEFINED_TX_ID = "***";

  private static final String MERCHANT_ACCOUNT_INFORMATION_KEY_CODE = "01";

  private static final String MERCHANT_ACCOUNT_INFORMATION_ADDITIONAL_INFO = "02";

  @Override
  public void setDefaultValues() {
    super.setDefaultValues();
    setTransactionCode(UNDEFINED_TX_ID);
  }

  public void setMerchantAccountInformationKey(String key) {
    if (!validateKey(key)) {
      throw new InvalidDataException(MESSAGE_PROVIDED_VALUE_IS_NOT_VALID + key);
    }
    SimpleBlock keyBlock = new SimpleBlock(MERCHANT_ACCOUNT_INFORMATION_KEY_CODE, key);
    addBlockWithinMerchantAccountInformation(keyBlock);
  }

  public void setMerchantAccountInformationAdditionalInfo(String additionalInfo) {
    if (!validateStringAlphanumericIncludingSpaces(additionalInfo)) {
      throw new InvalidDataException(MESSAGE_PROVIDED_VALUE_IS_NOT_VALID + additionalInfo);
    }
    addBlockWithinMerchantAccountInformation(
        new SimpleBlock(MERCHANT_ACCOUNT_INFORMATION_ADDITIONAL_INFO, additionalInfo));
  }

  @Override
  protected Set<String> getMandatoryBlockCodes() {
    Set<String> mandatoryBlockCodes = super.getMandatoryBlockCodes();
    mandatoryBlockCodes.add(
        MERCHANT_ACCOUNT_INFORMATION_BLOCK_CODE + MERCHANT_ACCOUNT_INFORMATION_KEY_CODE);
    return mandatoryBlockCodes;
  }
}

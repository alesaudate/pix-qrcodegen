package com.github.alesaudate.pix.qrcode;

import static com.github.alesaudate.pix.qrcode.QRCodeUtils.validateURL;

import java.util.Set;

public class DynamicQRCode extends QRCode {

  private static final String POINT_OF_INITIATION_METHOD_CODE = "01";
  private static final String POINT_OF_INITIATION_DEFAULT = "12";
  private static final String MERCHANT_ACCOUNT_INFORMATION_URL_CODE = "25";

  @Override
  protected void setDefaultValues() {
    super.setDefaultValues();
    addBlock(new SimpleBlock(POINT_OF_INITIATION_METHOD_CODE, POINT_OF_INITIATION_DEFAULT));
  }

  public void setMerchantAccountInformationURL(String url) {
    if (!validateURL(url)) {
      throw new InvalidDataException(MESSAGE_PROVIDED_VALUE_IS_NOT_VALID + url);
    }
    SimpleBlock urlBlock = new SimpleBlock(MERCHANT_ACCOUNT_INFORMATION_URL_CODE, url);
    addBlockWithinMerchantAccountInformation(urlBlock);
  }

  public void setReferenceLabel(String referenceLabel) {
    setTransactionCode(referenceLabel);
  }

  @Override
  protected Set<String> getMandatoryBlockCodes() {
    Set<String> mandatoryBlockCodes = super.getMandatoryBlockCodes();
    mandatoryBlockCodes.add(POINT_OF_INITIATION_METHOD_CODE);
    mandatoryBlockCodes.add(
        MERCHANT_ACCOUNT_INFORMATION_BLOCK_CODE + MERCHANT_ACCOUNT_INFORMATION_URL_CODE);
    mandatoryBlockCodes.add(TRANSACTION_AMOUNT_CODE);
    return mandatoryBlockCodes;
  }
}

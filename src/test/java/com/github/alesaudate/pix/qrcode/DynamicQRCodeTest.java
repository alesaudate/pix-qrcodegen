package com.github.alesaudate.pix.qrcode;

import static com.github.alesaudate.pix.qrcode.QRCode.MERCHANT_CATEGORY_CODE_UNKNOWN;
import static com.github.alesaudate.pix.qrcode.QRCodeBuilder.dynamicQRCode;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class DynamicQRCodeTest {

  @Test
  void dynamicQRCode_basicQRCodeUsingDSL() {

    String dynamicQRCode =
        dynamicQRCode()
            .merchantAccountInformation()
            .url("bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441")
            .merchantCategoryCode("0000")
            .transactionAmount(new BigDecimal("123.45"))
            .merchantName("Fulano de Tal")
            .referenceLabel("RP12345678-2019")
            .merchantCity("BRASILIA")
            .build();

    assertEquals(
        "000201"
            + "010212"
            + "2672"
            + "0014br.gov.bcb.pix"
            + "2550bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441"
            + "52040000"
            + "5303986"
            + "5406123.45"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6219"
            + "0515RP12345678-2019"
            + "630445C8",
        dynamicQRCode);
  }

  @Test
  void dynamicQRCode_customizingMerchantAccountInformationGUI() {

    String dynamicQRCode =
        dynamicQRCode()
            .merchantAccountInformation()
            .gui("test.pix.com.br")
            .url("bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441")
            .merchantCategoryCode("0000")
            .transactionAmount(new BigDecimal("123.45"))
            .merchantName("Fulano de Tal")
            .referenceLabel("RP12345678-2019")
            .merchantCity("BRASILIA")
            .build();

    assertEquals(
        "000201"
            + "010212"
            + "2673"
            + "0015test.pix.com.br"
            + "2550bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441"
            + "52040000"
            + "5303986"
            + "5406123.45"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6219"
            + "0515RP12345678-2019"
            + "6304C270",
        dynamicQRCode);
  }

  @Test
  void dynamicQRCode_basicQRCodeUsingDSLAndAmountAsString() {

    String dynamicQRCode =
        dynamicQRCode()
            .merchantAccountInformation()
            .url("bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441")
            .merchantCategoryCode("0000")
            .transactionAmount("123.45")
            .merchantName("Fulano de Tal")
            .referenceLabel("RP12345678-2019")
            .merchantCity("BRASILIA")
            .build();

    assertEquals(
        "000201"
            + "010212"
            + "2672"
            + "0014br.gov.bcb.pix"
            + "2550bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441"
            + "52040000"
            + "5303986"
            + "5406123.45"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6219"
            + "0515RP12345678-2019"
            + "630445C8",
        dynamicQRCode);
  }

  @Test
  void dynamicQRCode_basicQRCodeUsingDSLAndUnknownMerchantCode() {

    String dynamicQRCode =
        dynamicQRCode()
            .merchantAccountInformation()
            .url("bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441")
            .unknownMerchantCategoryCode()
            .transactionAmount("123.45")
            .merchantName("Fulano de Tal")
            .referenceLabel("RP12345678-2019")
            .merchantCity("BRASILIA")
            .build();

    assertEquals(
        "000201"
            + "010212"
            + "2672"
            + "0014br.gov.bcb.pix"
            + "2550bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441"
            + "52040000"
            + "5303986"
            + "5406123.45"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6219"
            + "0515RP12345678-2019"
            + "630445C8",
        dynamicQRCode);
  }

  @Test
  void dynamicQRCode_basicQRCodeUsingPOJO() {

    DynamicQRCode dynamicQRCode = givenABasicDynamicQRCode();

    assertEquals(
        "000201"
            + "010212"
            + "2672"
            + "0014br.gov.bcb.pix"
            + "2550bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441"
            + "52040000"
            + "5303986"
            + "5406123.45"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6219"
            + "0515RP12345678-2019"
            + "630445C8",
        dynamicQRCode.asString());
  }

  @Test
  void setMerchantAccountInformationURL_notAValidURL_validationFails() {
    DynamicQRCode dynamicQRCode = givenABasicDynamicQRCode();

    assertThrows(
        InvalidDataException.class,
        () -> dynamicQRCode.setMerchantAccountInformationURL("http://pix.com.br/test"));
  }

  @Test
  void asString_setDefaultValuesNotCalled_validationFails() {
    DynamicQRCode dynamicQRCode = new DynamicQRCode();

    assertThrows(InvalidDataException.class, dynamicQRCode::asString);
  }

  @Test
  void setTransactionCode_codeIsNotValid_validationFails() {
    DynamicQRCode dynamicQRCode = givenABasicDynamicQRCode();

    assertThrows(InvalidDataException.class, () -> dynamicQRCode.setTransactionCode("..."));
  }

  @Test
  void setMerchantAccountInformationGuiCode_guiIsNotValid_validationFails() {
    DynamicQRCode dynamicQRCode = givenABasicDynamicQRCode();

    assertThrows(
        InvalidDataException.class,
        () -> dynamicQRCode.setMerchantAccountInformationGuiCode("***"));
  }

  @Test
  void setMerchantAccountInformationGuiCode_guiIsReplaced_existingURLIsNotAffected() {
    DynamicQRCode dynamicQRCode = givenABasicDynamicQRCode();

    dynamicQRCode.setMerchantAccountInformationGuiCode("test.pix.com.br");

    assertTrue(
        dynamicQRCode.asString().contains("bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441"));
  }

  private DynamicQRCode givenABasicDynamicQRCode() {
    DynamicQRCode dynamicQRCode = new DynamicQRCode();
    dynamicQRCode.setDefaultValues();
    dynamicQRCode.setMerchantAccountInformationURL(
        "bx.com.br/pix/8b3da2f3-9a41-40d1-a91a-bd93113bd441");
    dynamicQRCode.setMerchantCategoryCode(MERCHANT_CATEGORY_CODE_UNKNOWN);
    dynamicQRCode.setReferenceLabel("RP12345678-2019");
    dynamicQRCode.setMerchantCity("BRASILIA");
    dynamicQRCode.setTransactionAmount(new BigDecimal("123.45"));
    dynamicQRCode.setMerchantName("Fulano de Tal");
    return dynamicQRCode;
  }
}

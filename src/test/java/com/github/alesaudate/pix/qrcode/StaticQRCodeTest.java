package com.github.alesaudate.pix.qrcode;

import static com.github.alesaudate.pix.qrcode.QRCodeBuilder.staticQRCode;
import static com.github.alesaudate.pix.qrcode.TestUtils.generateLongString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class StaticQRCodeTest {

  @Test
  void staticQRCode_basicStaticQRCodeUsingDSL() {

    String staticQRCode =
        staticQRCode()
            .merchantAccountInformation()
            .merchantKey("123e4567-e12b-12d1-a456-426655440000")
            .unknownMerchantCategoryCode()
            .merchantName("Fulano de Tal")
            .merchantCity("BRASILIA")
            .build();

    assertEquals(
        "000201"
            + "2658"
            + "0014br.gov.bcb.pix"
            + "0136123e4567-e12b-12d1-a456-426655440000"
            + "52040000"
            + "5303986"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6207"
            + "0503***"
            + "63041D3D",
        staticQRCode);
  }

  @Test
  void staticQRCode_customizingMerchantAccountInformationGUI() {

    String staticQRCode =
        staticQRCode()
            .merchantAccountInformation()
            .gui("test.pix.com.br")
            .merchantKey("123e4567-e12b-12d1-a456-426655440000")
            .unknownMerchantCategoryCode()
            .merchantName("Fulano de Tal")
            .merchantCity("BRASILIA")
            .build();

    assertEquals(
        "000201"
            + "2659"
            + "0015test.pix.com.br"
            + "0136123e4567-e12b-12d1-a456-426655440000"
            + "52040000"
            + "5303986"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6207"
            + "0503***"
            + "63042060",
        staticQRCode);
  }

    @Test
    void staticQRCode_setMerchantAccountInformationAdditionalInfo() {

        String staticQRCode =
                staticQRCode()
                        .merchantAccountInformation()
                        .gui("test.pix.com.br")
                        .additionalInfo("Test merchant additional info")
                        .merchantKey("123e4567-e12b-12d1-a456-426655440000")
                        .unknownMerchantCategoryCode()
                        .merchantName("Fulano de Tal")
                        .merchantCity("BRASILIA")
                        .build();

        assertEquals(
                "000201"
                        + "2692"
                        + "0015test.pix.com.br"
                        + "0229Test merchant additional info"
                        + "0136123e4567-e12b-12d1-a456-426655440000"
                        + "52040000"
                        + "5303986"
                        + "5802BR"
                        + "5913Fulano de Tal"
                        + "6008BRASILIA"
                        + "6207"
                        + "0503***"
                        + "63043203",
                staticQRCode);
    }

  @Test
  void staticQRCode_basicStaticQRCodeUsingPOJO() {

    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertEquals(
        "000201"
            + "2658"
            + "0014br.gov.bcb.pix"
            + "0136123e4567-e12b-12d1-a456-426655440000"
            + "52040000"
            + "5303986"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6207"
            + "0503***"
            + "63041D3D",
        staticQRCode.asString());
  }

  @Test
  void staticQRCode_usingMerchantAccountInformationAdditionalInfo() {
    StaticQRCode staticQRCode = givenAStaticQRCode();
    staticQRCode.setMerchantAccountInformationAdditionalInfo("Test data generation");

    assertEquals(
        "000201"
            + "2682"
            + "0014br.gov.bcb.pix"
            + "0136123e4567-e12b-12d1-a456-426655440000"
            + "0220Test data generation"
            + "52040000"
            + "5303986"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6207"
            + "0503***"
            + "6304B113",
        staticQRCode.asString());
  }

  @Test
  void staticQRCode_usingDSLSetTransactionAmount() {

    String staticQRCode =
        staticQRCode()
            .transactionAmount("123")
            .merchantAccountInformation()
            .merchantKey("123e4567-e12b-12d1-a456-426655440000")
            .unknownMerchantCategoryCode()
            .merchantName("Fulano de Tal")
            .merchantCity("BRASILIA")
            .build();

    assertEquals(
        "000201"
            + "2658"
            + "0014br.gov.bcb.pix"
            + "0136123e4567-e12b-12d1-a456-426655440000"
            + "52040000"
            + "5303986"
            + "5406123.00"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6207"
            + "0503***"
            + "63041D1D",
        staticQRCode);
  }

  @Test
  void staticQRCode_usingDSLSetTransactionAmountAsBigDecimal() {

    String staticQRCode =
        staticQRCode()
            .transactionAmount(new BigDecimal("123"))
            .merchantAccountInformation()
            .merchantKey("123e4567-e12b-12d1-a456-426655440000")
            .unknownMerchantCategoryCode()
            .merchantName("Fulano de Tal")
            .merchantCity("BRASILIA")
            .build();

    assertEquals(
        "000201"
            + "2658"
            + "0014br.gov.bcb.pix"
            + "0136123e4567-e12b-12d1-a456-426655440000"
            + "52040000"
            + "5303986"
            + "5406123.00"
            + "5802BR"
            + "5913Fulano de Tal"
            + "6008BRASILIA"
            + "6207"
            + "0503***"
            + "63041D1D",
        staticQRCode);
  }

  @Test
  void setMerchantName_nullMerchantName_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantName(null));
  }

  @Test
  void setMerchantName_blankMerchantName_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantName("    "));
  }

  @Test
  void setMerchantName_merchantNameTooLong_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(
        InvalidDataException.class, () -> staticQRCode.setMerchantName(generateLongString(100)));
  }

  @Test
  void setMerchantAccountInformationKey_nullMerchantAccountInformationKey_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(
        InvalidDataException.class, () -> staticQRCode.setMerchantAccountInformationKey(null));
  }

  @Test
  void setMerchantAccountInformationKey_blankMerchantAccountInformationKey_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(
        InvalidDataException.class, () -> staticQRCode.setMerchantAccountInformationKey("   "));
  }

  @Test
  void setMerchantAccountInformationKey_merchantAccountInformationKeyTooLong_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(
        InvalidDataException.class,
        () -> staticQRCode.setMerchantAccountInformationKey(generateLongString(78)));
  }

  @Test
  void setMerchantCity_nullMerchantCity_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantCity(null));
  }

  @Test
  void setMerchantCity_emptyMerchantCity_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantCity("   "));
  }

  @Test
  void setMerchantCity_merchantCityTooLong_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(
        InvalidDataException.class, () -> staticQRCode.setMerchantCity(generateLongString(100)));
  }

  @Test
  void setMerchantCategoryCode_nullMerchantCategoryCode_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantCategoryCode(null));
  }

  @Test
  void setMerchantCategoryCode_emptyMerchantCategoryCode_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantCategoryCode("   "));
  }

  @Test
  void setMerchantCategoryCode_smallerThanExpected_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantCategoryCode("000"));
  }

  @Test
  void setMerchantCategoryCode_longerThanExpected_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantCategoryCode("00000"));
  }

  @Test
  void setMerchantCategoryCode_hasNonDigits_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantCategoryCode("a123"));
  }

  @Test
  void setMerchantAccountInformationAdditionalInfo_longerThanExpected_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(
        InvalidDataException.class,
        () -> staticQRCode.setMerchantAccountInformationAdditionalInfo("Test data generation with a little bit "));
  }

  @Test
  void setMerchantAccountInformationAdditionalInfo_containingSpecialCharacters_validationFails() {
    StaticQRCode staticQRCode = givenAStaticQRCode();

    assertThrows(InvalidDataException.class, () -> staticQRCode.setMerchantAccountInformationAdditionalInfo("***"));
  }

  @Test
  void asString_missingMerchantAccountInformationKey_validationFails() {
    StaticQRCode staticQRCode = new StaticQRCode();
    staticQRCode.setDefaultValues();
    staticQRCode.setMerchantName("Fulano de Tal");
    staticQRCode.setMerchantCity("BRASILIA");

    assertThrows(InvalidDataException.class, staticQRCode::asString);
  }

  @Test
  void asString_missingMerchantName_validationFails() {
    StaticQRCode staticQRCode = new StaticQRCode();
    staticQRCode.setDefaultValues();
    staticQRCode.setMerchantAccountInformationKey("123e4567-e12b-12d1-a456-426655440000");
    staticQRCode.setMerchantCity("BRASILIA");

    assertThrows(InvalidDataException.class, staticQRCode::asString);
  }

  @Test
  void asString_missingMerchantCity_validationFails() {
    StaticQRCode staticQRCode = new StaticQRCode();
    staticQRCode.setDefaultValues();
    staticQRCode.setMerchantAccountInformationKey("123e4567-e12b-12d1-a456-426655440000");
    staticQRCode.setMerchantName("Fulano de Tal");

    assertThrows(InvalidDataException.class, staticQRCode::asString);
  }

  @Test
  void asString_missingDefaultValues_validationFails() {
    StaticQRCode staticQRCode = new StaticQRCode();
    staticQRCode.setMerchantAccountInformationKey("123e4567-e12b-12d1-a456-426655440000");
    staticQRCode.setMerchantName("Fulano de Tal");
    staticQRCode.setMerchantCity("BRASILIA");

    assertThrows(InvalidDataException.class, staticQRCode::asString);
  }

  private StaticQRCode givenAStaticQRCode() {
    StaticQRCode staticQRCode = new StaticQRCode();
    staticQRCode.setDefaultValues();
    staticQRCode.setMerchantAccountInformationKey("123e4567-e12b-12d1-a456-426655440000");
    staticQRCode.setMerchantName("Fulano de Tal");
    staticQRCode.setMerchantCity("BRASILIA");
    return staticQRCode;
  }
}

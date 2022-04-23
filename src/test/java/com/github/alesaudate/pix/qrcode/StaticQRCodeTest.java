package com.github.alesaudate.pix.qrcode;

import org.junit.jupiter.api.Test;

import static com.github.alesaudate.pix.qrcode.QRCodeBuilder.staticQRCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StaticQRCodeTest {


    @Test
    void staticQRCode_basicStaticQRCodeUsingDSL() {

        String staticQRCode = staticQRCode()
                .merchantAccountInformation()
                .merchantKey("123e4567-e12b-12d1-a456-426655440000")
                .unknownMerchantCategoryCode()
                .merchantName("Fulano de Tal")
                .merchantCity("BRASILIA")
                .build();

        assertEquals("000201" +
                "2658" +
                    "0014br.gov.bcb.pix" +
                    "0136123e4567-e12b-12d1-a456-426655440000" +
                "52040000" +
                "5303986" +
                "5802BR" +
                "5913Fulano de Tal" +
                "6008BRASILIA" +
                "6207" +
                    "0503***" +
                "63041D3D", staticQRCode);

    }


    @Test
    void staticQRCode_basicStaticQRCodeUsingPOJO() {

        StaticQRCode staticQRCode = givenAStaticQRCode();

        assertEquals("000201" +
                "2658" +
                "0014br.gov.bcb.pix" +
                "0136123e4567-e12b-12d1-a456-426655440000" +
                "52040000" +
                "5303986" +
                "5802BR" +
                "5913Fulano de Tal" +
                "6008BRASILIA" +
                "6207" +
                "0503***" +
                "63041D3D", staticQRCode.asString());

    }


    @Test
    void asString_nullMerchantName_validationFails() {
        StaticQRCode staticQRCode = givenAStaticQRCode();
        staticQRCode.setMerchantName(null);

        assertThrows(InvalidDataException.class, staticQRCode::asString);
    }


    @Test
    void asString_blankMerchantName_validationFails() {
        StaticQRCode staticQRCode = givenAStaticQRCode();
        staticQRCode.setMerchantName("    ");

        assertThrows(InvalidDataException.class, staticQRCode::asString);
    }


    @Test
    void asString_merchantNameTooLong_validationFails() {
        StaticQRCode staticQRCode = givenAStaticQRCode();
        staticQRCode.setMerchantName(generateLongString(100));

        assertThrows(InvalidDataException.class, staticQRCode::asString);
    }


    @Test
    void asString_nullMerchantAccountInformationKey_validationFails() {
        StaticQRCode staticQRCode = givenAStaticQRCode();
        staticQRCode.setMerchantAccountInformationKey(null);

        assertThrows(InvalidDataException.class, staticQRCode::asString);
    }


    @Test
    void asString_blankMerchantAccountInformationKey_validationFails() {
        StaticQRCode staticQRCode = givenAStaticQRCode();
        staticQRCode.setMerchantAccountInformationKey("   ");

        assertThrows(InvalidDataException.class, staticQRCode::asString);
    }


    @Test
    void asString_merchantAccountInformationKeyTooLong_validationFails() {
        StaticQRCode staticQRCode = givenAStaticQRCode();
        staticQRCode.setMerchantAccountInformationKey(generateLongString(78));

        assertThrows(InvalidDataException.class, staticQRCode::asString);
    }

    @Test
    void asString_nullMerchantCity_validationFails() {
        StaticQRCode staticQRCode = givenAStaticQRCode();
        staticQRCode.setMerchantCity(null);

        assertThrows(InvalidDataException.class, staticQRCode::asString);
    }


    @Test
    void asString_emptyMerchantCity_validationFails() {
        StaticQRCode staticQRCode = givenAStaticQRCode();
        staticQRCode.setMerchantCity("   ");

        assertThrows(InvalidDataException.class, staticQRCode::asString);
    }

    @Test
    void asString_merchantCityTooLong_validationFails() {
        StaticQRCode staticQRCode = givenAStaticQRCode();
        staticQRCode.setMerchantCity(generateLongString(100));

        assertThrows(InvalidDataException.class, staticQRCode::asString);
    }



    private String generateLongString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int charCode = (i % 26) + 'a';
            builder.append((char)charCode);
        }
        return builder.toString();
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

package com.github.alesaudate.pix.qrcode;

import org.junit.jupiter.api.Test;

import static com.github.alesaudate.pix.qrcode.QRCodeBuilder.staticQRCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticQRCodeTest {


    @Test
    void staticQRCode_basicStaticQRCodeUsingDSL() {

        String staticQRCode = staticQRCode()
                .merchantAccountInformation()
                .merchantKey("123e4567-e12b-12d1-a456-426655440000")
                .merchantCategoryCode("0000")
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

        StaticQRCode staticQRCode = new StaticQRCode();
        staticQRCode.setDefaultValues();
        staticQRCode.setMerchantAccountInformationKey("123e4567-e12b-12d1-a456-426655440000");
        staticQRCode.setMerchantCategoryCode("0000");
        staticQRCode.setMerchantName("Fulano de Tal");
        staticQRCode.setMerchantCity("BRASILIA");

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
                "63041D3D", staticQRCode.toString());

    }


}

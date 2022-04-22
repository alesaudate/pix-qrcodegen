package com.github.alesaudate.pix.qrcode;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class StaticQRCode extends QRCode {


    private static final Block PAYLOAD_FORMAT_INDICATOR_STATIC_QR_CODE = new SimpleBlock("00", "01");
    private static final Block UNDEFINED_TX_ID_BLOCK = new CompositeBlock("62", singletonList(new SimpleBlock("05", "***")));
    private static final String MERCHANT_ACCOUNT_INFORMATION_BLOCK_CODE = "26";
    private static final String MERCHANT_CATEGORY_CODE_BLOCK_CODE = "52";
    private static final String MERCHANT_ACCOUNT_INFORMATION_GUI_CODE = "00";
    private static final String MERCHANT_ACCOUNT_INFORMATION_GUI_DEFAULT = "br.gov.bcb.pix";
    private static final String MERCHANT_ACCOUNT_INFORMATION_KEY_CODE = "01";
    private static final String MERCHANT_NAME_BLOCK_CODE = "59";
    private static final SimpleBlock MERCHANT_ACCOUNT_INFORMATION_GUI_BLOCK = new SimpleBlock(MERCHANT_ACCOUNT_INFORMATION_GUI_CODE, MERCHANT_ACCOUNT_INFORMATION_GUI_DEFAULT);
    private static final String MERCHANT_CITY_BLOCK_CODE = "60";


    public void setDefaultValues() {
        addBlock(PAYLOAD_FORMAT_INDICATOR_STATIC_QR_CODE);
        addBlock(TRANSACTION_CURRENCY_BRL_BLOCK);
        addBlock(COUNTRY_CODE_BR_BLOCK);
        addBlock(UNDEFINED_TX_ID_BLOCK);
        setMerchantCategoryCode("0000");
    }

    public void setMerchantAccountInformationKey(String key) {
        SimpleBlock keyBlock = new SimpleBlock(MERCHANT_ACCOUNT_INFORMATION_KEY_CODE, key);
        addBlock(new CompositeBlock(MERCHANT_ACCOUNT_INFORMATION_BLOCK_CODE, asList(MERCHANT_ACCOUNT_INFORMATION_GUI_BLOCK, keyBlock)));
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        addBlock(new SimpleBlock(MERCHANT_CATEGORY_CODE_BLOCK_CODE, merchantCategoryCode));
    }

    public void setMerchantName(String merchantName) {
        addBlock(new SimpleBlock(MERCHANT_NAME_BLOCK_CODE, merchantName));
    }

    public void setMerchantCity(String merchantCity) {
        addBlock(new SimpleBlock(MERCHANT_CITY_BLOCK_CODE, merchantCity));
    }

}

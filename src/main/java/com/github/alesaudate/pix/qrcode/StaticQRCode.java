package com.github.alesaudate.pix.qrcode;

import java.util.Set;

public class StaticQRCode extends QRCode {

    private static final String UNDEFINED_TX_ID = "***";

    private static final String MERCHANT_ACCOUNT_INFORMATION_KEY_CODE = "01";

    @Override
    public void setDefaultValues() {
        super.setDefaultValues();
        setTransactionCode(UNDEFINED_TX_ID);
    }

    public void setMerchantAccountInformationKey(String key) {
        SimpleBlock keyBlock = new SimpleBlock(MERCHANT_ACCOUNT_INFORMATION_KEY_CODE, key);
        addBlockWithinMerchantAccountInformation(keyBlock);
    }

    @Override
    protected Set<String> getMandatoryBlockCodes() {
        Set<String> mandatoryBlockCodes = super.getMandatoryBlockCodes();
        mandatoryBlockCodes.add(MERCHANT_ACCOUNT_INFORMATION_BLOCK_CODE + MERCHANT_ACCOUNT_INFORMATION_KEY_CODE);
        return mandatoryBlockCodes;
    }
}

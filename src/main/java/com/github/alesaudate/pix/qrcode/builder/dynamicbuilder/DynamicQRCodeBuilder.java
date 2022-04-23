package com.github.alesaudate.pix.qrcode.builder.dynamicbuilder;

import com.github.alesaudate.pix.qrcode.DynamicQRCode;

public class DynamicQRCodeBuilder {

    private final DynamicQRCode dynamicQRCode;

    public DynamicQRCodeBuilder(DynamicQRCode dynamicQRCode) {
        this.dynamicQRCode = dynamicQRCode;
    }


    public  MerchantAccountInformationDynamicBuilder merchantAccountInformation() {
        return new MerchantAccountInformationDynamicBuilder(this.dynamicQRCode);
    }
}

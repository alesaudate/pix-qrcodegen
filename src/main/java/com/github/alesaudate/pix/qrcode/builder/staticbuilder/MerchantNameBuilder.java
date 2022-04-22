package com.github.alesaudate.pix.qrcode.builder.staticbuilder;

import com.github.alesaudate.pix.qrcode.StaticQRCode;

public class MerchantNameBuilder {
    private final StaticQRCode staticQRCode;

    public MerchantNameBuilder(StaticQRCode staticQRCode) {
        this.staticQRCode = staticQRCode;
    }

    public MerchantCityBuilder merchantName(String merchantName) {
        staticQRCode.setMerchantName(merchantName);
        return new MerchantCityBuilder(this.staticQRCode);
    }
}

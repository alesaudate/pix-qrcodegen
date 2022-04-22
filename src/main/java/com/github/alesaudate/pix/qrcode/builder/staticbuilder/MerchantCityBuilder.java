package com.github.alesaudate.pix.qrcode.builder.staticbuilder;

import com.github.alesaudate.pix.qrcode.StaticQRCode;

public class MerchantCityBuilder {
    private final StaticQRCode staticQRCode;

    public MerchantCityBuilder(StaticQRCode staticQRCode) {
        this.staticQRCode = staticQRCode;
    }


    public OutputDataToStringBuilder merchantCity(String merchantCity) {
        staticQRCode.setMerchantCity(merchantCity);
        return new OutputDataToStringBuilder(this.staticQRCode);
    }
}

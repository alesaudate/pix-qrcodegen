package com.github.alesaudate.pix.qrcode.builder;

import com.github.alesaudate.pix.qrcode.QRCode;

public class MerchantCityBuilder {
    private final QRCode qrCode;

    public MerchantCityBuilder(QRCode qrCode) {
        this.qrCode = qrCode;
    }


    public OutputDataToStringBuilder merchantCity(String merchantCity) {
        qrCode.setMerchantCity(merchantCity);
        return new OutputDataToStringBuilder(this.qrCode);
    }
}

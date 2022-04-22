package com.github.alesaudate.pix.qrcode.builder.staticbuilder;

import com.github.alesaudate.pix.qrcode.StaticQRCode;

public class StaticQRCodeBuilder {

    private final StaticQRCode staticQRCode;

    public StaticQRCodeBuilder(StaticQRCode staticQRCode) {
        this.staticQRCode = staticQRCode;
    }


    public MerchantAccountInformationStaticBuilder merchantAccountInformation() {
        return new MerchantAccountInformationStaticBuilder(this.staticQRCode);
    }
}



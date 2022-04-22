package com.github.alesaudate.pix.qrcode;

import com.github.alesaudate.pix.qrcode.builder.staticbuilder.StaticQRCodeBuilder;

public class QRCodeBuilder {


    public static StaticQRCodeBuilder staticQRCode() {
        StaticQRCode staticQRCode = new StaticQRCode();
        staticQRCode.setDefaultValues();
        return new StaticQRCodeBuilder(staticQRCode);
    }


}




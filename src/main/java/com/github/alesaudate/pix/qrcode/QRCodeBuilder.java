package com.github.alesaudate.pix.qrcode;

import com.github.alesaudate.pix.qrcode.builder.dynamicbuilder.DynamicQRCodeBuilder;
import com.github.alesaudate.pix.qrcode.builder.staticbuilder.StaticQRCodeBuilder;

public class QRCodeBuilder {


    public static StaticQRCodeBuilder staticQRCode() {
        StaticQRCode staticQRCode = new StaticQRCode();
        staticQRCode.setDefaultValues();
        return new StaticQRCodeBuilder(staticQRCode);
    }

    public static DynamicQRCodeBuilder dynamicQRCode(){
        DynamicQRCode dynamicQRCode = new DynamicQRCode();
        dynamicQRCode.setDefaultValues();
        return new DynamicQRCodeBuilder(dynamicQRCode);
    }

}




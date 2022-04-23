package com.github.alesaudate.pix.qrcode.builder;

import com.github.alesaudate.pix.qrcode.QRCode;

public class OutputDataToStringBuilder {

    private final QRCode qrCode;

    public OutputDataToStringBuilder(QRCode qrCode) {
        this.qrCode = qrCode;
    }

    public String build() {
        return qrCode.asString();
    }
}

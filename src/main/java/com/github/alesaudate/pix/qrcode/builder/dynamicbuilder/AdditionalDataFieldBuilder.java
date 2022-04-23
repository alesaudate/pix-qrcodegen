package com.github.alesaudate.pix.qrcode.builder.dynamicbuilder;

import com.github.alesaudate.pix.qrcode.DynamicQRCode;
import com.github.alesaudate.pix.qrcode.builder.MerchantCityBuilder;

public class AdditionalDataFieldBuilder {

    private final DynamicQRCode dynamicQRCode;

    public AdditionalDataFieldBuilder(DynamicQRCode dynamicQRCode) {
        this.dynamicQRCode = dynamicQRCode;
    }

    public MerchantCityBuilder referenceLabel(String referenceLabel) {
        this.dynamicQRCode.setReferenceLabel(referenceLabel);
        return new MerchantCityBuilder(dynamicQRCode);
    }
}

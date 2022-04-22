package com.github.alesaudate.pix.qrcode.builder.staticbuilder;

import com.github.alesaudate.pix.qrcode.StaticQRCode;

public class MerchantCategoryCodeBuilder {
    private final StaticQRCode staticQRCode;

    public MerchantCategoryCodeBuilder(StaticQRCode staticQRCode) {

        this.staticQRCode = staticQRCode;
    }


    public MerchantNameBuilder merchantCategoryCode(String merchantCategoryCode) {
        this.staticQRCode.setMerchantCategoryCode(merchantCategoryCode);
        return new MerchantNameBuilder(this.staticQRCode);
    }

    public MerchantNameBuilder unknownMerchantCategoryCode() {
        return merchantCategoryCode("0000");
    }
}

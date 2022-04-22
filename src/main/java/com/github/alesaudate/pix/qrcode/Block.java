package com.github.alesaudate.pix.qrcode;

import static com.github.alesaudate.pix.qrcode.QRCodeUtils.getLengthWithTwoDigits;

public abstract class Block {


    private final String blockCode;


    protected Block(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public String assembleBlock() {
        String blockContent = getBlockContent();
        String blockLength = getLengthWithTwoDigits(blockContent);
        return blockCode + blockLength + blockContent;
    }

    protected abstract String getBlockContent() ;
}

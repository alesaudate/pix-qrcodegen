package com.github.alesaudate.pix.qrcode;

public class SimpleBlock extends Block {

    private final String value;

    public SimpleBlock(String blockCode, String value) {
        super(blockCode);
        this.value = value;
    }

    @Override
    protected String getBlockContent() {
        return value;
    }
}

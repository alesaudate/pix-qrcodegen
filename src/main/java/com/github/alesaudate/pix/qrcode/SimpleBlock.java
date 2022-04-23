package com.github.alesaudate.pix.qrcode;

import static java.lang.String.format;

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

    @Override
    protected void validateBlockContent() {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidDataException(format("The block %s has empty content", getBlockCode()));
        }
        else if (value.length() > 99) {
            throw new InvalidDataException(format("The block %s has too long content, with %d characters", getBlockCode(), value.length()));
        }
    }
}

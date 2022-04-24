package com.github.alesaudate.pix.qrcode;

import static com.github.alesaudate.pix.qrcode.QRCodeUtils.getLengthWithTwoDigits;

import java.util.Collections;
import java.util.Set;

public abstract class Block {

  private final String blockCode;

  protected Block(String blockCode) {
    this.blockCode = blockCode;
  }

  public String getBlockCode() {
    return blockCode;
  }

  public Set<String> getBlockCodesForValidation() {
    return Collections.singleton(getBlockCode());
  }

  public String assembleBlock() {
    String blockContent = getBlockContent();
    String blockLength = getLengthWithTwoDigits(blockContent);
    return blockCode + blockLength + blockContent;
  }

  protected abstract String getBlockContent();

  protected abstract void validateBlockContent();
}

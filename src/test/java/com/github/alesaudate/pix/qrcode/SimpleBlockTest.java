package com.github.alesaudate.pix.qrcode;

import static com.github.alesaudate.pix.qrcode.TestUtils.generateLongString;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimpleBlockTest {

  @Test
  void validateBlockContent_contentIsNull_validationFails() {
    SimpleBlock block = new SimpleBlock("00", null);

    assertThrows(InvalidDataException.class, block::validateBlockContent);
  }

  @Test
  void validateBlockContent_contentIsBlank_validationFails() {
    SimpleBlock block = new SimpleBlock("00", "   ");

    assertThrows(InvalidDataException.class, block::validateBlockContent);
  }

  @Test
  void validateBlockContent_contentIsTooLong_validationFails() {
    SimpleBlock block = new SimpleBlock("00", generateLongString(100));

    assertThrows(InvalidDataException.class, block::validateBlockContent);
  }

  @Test
  void validateBlockContent_contentIsRegular_validationSucceeds() {
    SimpleBlock block = new SimpleBlock("00", generateLongString(99));

    block.validateBlockContent();
  }
}

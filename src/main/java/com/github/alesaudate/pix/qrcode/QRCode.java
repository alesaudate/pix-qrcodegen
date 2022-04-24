package com.github.alesaudate.pix.qrcode;

import static com.github.alesaudate.pix.qrcode.Country.BRAZIL;
import static com.github.alesaudate.pix.qrcode.QRCodeUtils.*;
import static com.github.alesaudate.pix.qrcode.TransactionCurrency.BRL;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

import java.util.*;

public abstract class QRCode {

  // Codes for blocks
  private static final String MERCHANT_ACCOUNT_INFORMATION_GUI_CODE = "00";
  protected static final String MERCHANT_ACCOUNT_INFORMATION_BLOCK_CODE = "26";
  private static final String MERCHANT_CATEGORY_CODE_BLOCK_CODE = "52";
  private static final String MERCHANT_NAME_BLOCK_CODE = "59";
  private static final String MERCHANT_CITY_BLOCK_CODE = "60";
  protected static final String ADDITIONAL_DATA_FIELD_CODE = "62";
  private static final String ADDITIONAL_DATA_FIELD_REFERENCE_LABEL_CODE = "05";

  // Default values for some blocks
  private static final String MERCHANT_ACCOUNT_INFORMATION_GUI_DEFAULT = "br.gov.bcb.pix";
  public static final String MERCHANT_CATEGORY_CODE_UNKNOWN = "0000";

  //Default definitions of blocks
  private static final Block PAYLOAD_FORMAT_INDICATOR_BLOCK = new SimpleBlock("00", "01");
  private static final Block TRANSACTION_CURRENCY_BRL_BLOCK = new SimpleBlock("53", BRL.getValue());
  private static final Block COUNTRY_CODE_BR_BLOCK = new SimpleBlock("58", BRAZIL.getValue());
  private static final SimpleBlock MERCHANT_ACCOUNT_INFORMATION_GUI_BLOCK =
      new SimpleBlock(
          MERCHANT_ACCOUNT_INFORMATION_GUI_CODE, MERCHANT_ACCOUNT_INFORMATION_GUI_DEFAULT);

  //Exception message
  protected static final String MESSAGE_PROVIDED_VALUE_IS_NOT_VALID =
      "Provided value is not compliant with accepted values: ";

  private final List<Block> blocks;

  protected QRCode() {
    blocks = new ArrayList<>();
    addBlock(PAYLOAD_FORMAT_INDICATOR_BLOCK);
  }

  protected void addBlock(Block block) {
    removeBlockWithCode(block.getBlockCode());
    this.blocks.add(block);
  }

  protected void removeBlockWithCode(String code) {
    Optional<Block> optionalBlock =
        this.blocks.stream().filter(block -> block.getBlockCode().equals(code)).findAny();
    optionalBlock.ifPresent(this.blocks::remove);
  }

  protected void setDefaultValues() {
    addBlock(TRANSACTION_CURRENCY_BRL_BLOCK);
    addBlock(COUNTRY_CODE_BR_BLOCK);
    setMerchantCategoryCode(MERCHANT_CATEGORY_CODE_UNKNOWN);
  }

  protected void addBlockWithinMerchantAccountInformation(SimpleBlock simpleBlock) {
    addBlock(
        new CompositeBlock(
            MERCHANT_ACCOUNT_INFORMATION_BLOCK_CODE,
            asList(MERCHANT_ACCOUNT_INFORMATION_GUI_BLOCK, simpleBlock)));
  }

  public void setMerchantCategoryCode(String merchantCategoryCode) {
    if (!validateNumeric(merchantCategoryCode)) {
      throw new InvalidDataException(MESSAGE_PROVIDED_VALUE_IS_NOT_VALID + merchantCategoryCode);
    }
    addBlock(new SimpleBlock(MERCHANT_CATEGORY_CODE_BLOCK_CODE, merchantCategoryCode));
  }

  public void setMerchantName(String merchantName) {
    if (!validateStringAlphanumericIncludingSpaces(merchantName)) {
      throw new InvalidDataException(MESSAGE_PROVIDED_VALUE_IS_NOT_VALID + merchantName);
    }
    addBlock(new SimpleBlock(MERCHANT_NAME_BLOCK_CODE, merchantName));
  }

  public void setMerchantCity(String merchantCity) {
    if (!validateStringAlphanumeric(merchantCity)) {
      throw new InvalidDataException(MESSAGE_PROVIDED_VALUE_IS_NOT_VALID + merchantCity);
    }
    addBlock(new SimpleBlock(MERCHANT_CITY_BLOCK_CODE, merchantCity));
  }

  public void setTransactionCode(String transactionCode) {
    if (!validateKey(transactionCode)) {
      throw new InvalidDataException(MESSAGE_PROVIDED_VALUE_IS_NOT_VALID + transactionCode);
    }
    addBlock(
        new CompositeBlock(
            ADDITIONAL_DATA_FIELD_CODE,
            singletonList(
                new SimpleBlock(ADDITIONAL_DATA_FIELD_REFERENCE_LABEL_CODE, transactionCode))));
  }

  protected Set<String> getMandatoryBlockCodes() {
    return new HashSet<>(
        asList(
            PAYLOAD_FORMAT_INDICATOR_BLOCK.getBlockCode(),
            MERCHANT_ACCOUNT_INFORMATION_BLOCK_CODE + MERCHANT_ACCOUNT_INFORMATION_GUI_CODE,
            MERCHANT_CATEGORY_CODE_BLOCK_CODE,
            TRANSACTION_CURRENCY_BRL_BLOCK.getBlockCode(),
            COUNTRY_CODE_BR_BLOCK.getBlockCode(),
            MERCHANT_NAME_BLOCK_CODE,
            MERCHANT_CITY_BLOCK_CODE,
            ADDITIONAL_DATA_FIELD_CODE + ADDITIONAL_DATA_FIELD_REFERENCE_LABEL_CODE));
  }

  protected void validate() {
    Set<String> mandatoryBlockCodes = new HashSet<>(getMandatoryBlockCodes());
    this.blocks
        .stream()
        .map(Block::getBlockCodesForValidation)
        .flatMap(Collection::stream)
        .forEach(mandatoryBlockCodes::remove);
    if (!mandatoryBlockCodes.isEmpty()) {
      throw new InvalidDataException(
          format(
              "The following mandatory blocks are missing: [%s]", join(",", mandatoryBlockCodes)));
    }
    this.blocks.forEach(Block::validateBlockContent);
  }

  public String asString() {
    validate();
    String blocksAssembled =
        this.blocks
            .stream()
            .sorted(comparing(Block::getBlockCode))
            .map(Block::assembleBlock)
            .collect(joining());
    StringBuilder builder = new StringBuilder(blocksAssembled);
    builder.append("6304");
    builder.append(crc16(builder.toString()));
    return builder.toString();
  }
}

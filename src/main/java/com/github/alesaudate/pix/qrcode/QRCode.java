package com.github.alesaudate.pix.qrcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.alesaudate.pix.qrcode.Country.BRAZIL;
import static com.github.alesaudate.pix.qrcode.QRCodeUtils.crc16;
import static com.github.alesaudate.pix.qrcode.TransactionCurrency.BRL;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

public abstract class QRCode {


    public static final Block TRANSACTION_CURRENCY_BRL_BLOCK = new SimpleBlock("53", BRL.getValue());
    public static final Block COUNTRY_CODE_BR_BLOCK = new SimpleBlock("58", BRAZIL.getValue());


    private final List<Block> blocks;


    protected QRCode() {
        blocks = new ArrayList<>();
    }

    protected void addBlock(Block block) {
        removeBlockWithCode(block.getBlockCode());
        this.blocks.add(block);
    }

    protected void removeBlockWithCode(String code) {
        Optional<Block> optionalBlock = this.blocks.stream().filter(block -> block.getBlockCode().equals(code)).findAny();
        optionalBlock.ifPresent(this.blocks::remove);
    }

    public String toString() {

        String blocksAssembled = this.blocks.stream()
                .sorted(comparing(Block::getBlockCode))
                .map(Block::assembleBlock)
                .collect(joining());
        StringBuilder builder = new StringBuilder(blocksAssembled);
        builder.append("6304");
        builder.append(crc16(builder.toString()));
        return builder.toString();
    }
}

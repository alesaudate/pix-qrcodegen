package com.github.alesaudate.pix.qrcode;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class CompositeBlock extends Block{

    private final List<SimpleBlock> blocks;


    CompositeBlock(String blockCode, List<SimpleBlock> blocks) {
        super(blockCode);
        this.blocks = blocks;
    }

    @Override
    public Set<String> getBlockCodesForValidation() {
        String blockCode = getBlockCode();
        return blocks.stream().map(Block::getBlockCode).map(blockCode::concat).collect(Collectors.toSet());
    }

    @Override
    protected String getBlockContent() {
        return blocks.stream()
                .map(Block::assembleBlock)
                .collect(joining());
    }
}

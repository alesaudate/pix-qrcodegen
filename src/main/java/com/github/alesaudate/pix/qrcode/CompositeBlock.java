package com.github.alesaudate.pix.qrcode;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompositeBlock extends Block {

  private final List<SimpleBlock> blocks;

  CompositeBlock(String blockCode, List<SimpleBlock> blocks) {
    super(blockCode);
    this.blocks = new ArrayList<>(blocks);
  }

  void addOrReplaceBlock(SimpleBlock simpleBlock) {
    blocks
        .stream()
        .filter(block -> block.getBlockCode().equals(simpleBlock.getBlockCode()))
        .findAny()
        .ifPresent(blocks::remove);
    blocks.add(simpleBlock);
  }

  @Override
  public Set<String> getBlockCodesForValidation() {
    String blockCode = getBlockCode();
    return blocks
        .stream()
        .map(Block::getBlockCode)
        .map(blockCode::concat)
        .collect(Collectors.toSet());
  }

  @Override
  protected String getBlockContent() {
    return blocks.stream().map(Block::assembleBlock).collect(joining());
  }

  @Override
  protected void validateBlockContent() {
    blocks.forEach(Block::validateBlockContent);
    String blockContent = getBlockContent();
    if (blockContent.length() > 99) {
      throw new InvalidDataException(
          format(
              "The block %s has too long content, with %d characters",
              getBlockCode(),
              blockContent.length()));
    }
  }
}

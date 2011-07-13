package com.bitcoinvisualizer;

import java.util.List;

public interface BlockInterface
{
	void addBlock(BlockType block);
	void removeBlock(BlockType block);
	void updateBlock(BlockType block);
	List<BlockType> getBlocks();	
}

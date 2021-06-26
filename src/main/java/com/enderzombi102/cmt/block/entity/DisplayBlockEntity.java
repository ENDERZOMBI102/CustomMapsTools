package com.enderzombi102.cmt.block.entity;

import com.enderzombi102.cmt.CMTContent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class DisplayBlockEntity extends BlockEntity {

	private static final ItemStack DEFAULT_STACK = new ItemStack(Items.JUKEBOX, 1);
	public ItemStack stack = DEFAULT_STACK;

	public DisplayBlockEntity(BlockPos pos, BlockState state) {
		super(CMTContent.displayBlockEntityType, pos, state);
	}

	public ItemStack getItem() {
		return this.stack;
	}

	@Override
	public NbtCompound writeNbt(NbtCompound tag) {
		super.writeNbt(tag);
		tag.putString("item", Registry.ITEM.getId( this.stack.getItem() ).toString() );
		return tag;
	}

	@Override
	public void readNbt(NbtCompound tag) {
		super.readNbt(tag);
		final Optional<Item> item = Registry.ITEM.getOrEmpty(
				new Identifier( tag.getString("item") )
		);
		// set item
		this.stack = item.isEmpty() ? DEFAULT_STACK : new ItemStack( item.get() );
	}
}

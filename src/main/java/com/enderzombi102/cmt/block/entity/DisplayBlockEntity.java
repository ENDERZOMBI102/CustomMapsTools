package com.enderzombi102.cmt.block.entity;

import com.enderzombi102.cmt.CMTContent;
import com.enderzombi102.cmt.block.DisplayBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DisplayBlockEntity extends BlockEntity {

	private static final ItemStack DEFAULT_STACK = new ItemStack(Items.JUKEBOX, 1);
	public ItemStack stack = DEFAULT_STACK;

	public DisplayBlockEntity() {
		super(CMTContent.displayBlockEntityType);
	}

	public ItemStack getItem() {
		return this.stack;
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag.putString("item", Registry.ITEM.getId( this.stack.getItem() ).toString() );
		return super.toTag(tag);
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		this.stack = new ItemStack( Registry.ITEM.get( new Identifier( tag.getString("item") ) ) );
		if ( this.stack.getItem() == null ) {
			this.stack = DEFAULT_STACK;
		}
	}
}

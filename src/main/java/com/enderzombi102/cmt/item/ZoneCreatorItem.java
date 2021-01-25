package com.enderzombi102.cmt.item;

import com.enderzombi102.cmt.CMTContent;
import com.enderzombi102.cmt.CustomMapsTools;
import com.enderzombi102.cmt.Utils;
import com.enderzombi102.cmt.zone.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ZoneCreatorItem extends Item {

	private BlockPos pos0;
	private BlockPos pos1;
	private int type = 0;
	private final ZoneActionData data = new ZoneActionData();

	public ZoneCreatorItem() {
		super( new FabricItemSettings().group(CustomMapsTools.CMT_GROUP) );
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext ctx) {
		tooltip.add( new LiteralText("Zone type: " + ZoneTypes.getType(this.type).toString() ) );
		tooltip.add( new LiteralText("Position 0: " + this.pos0.toShortString() ) );
		tooltip.add( new LiteralText("Position 1: " + this.pos1.toShortString() ) );
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext ctx) {
		if ( ctx.getPlayer() == null || ctx.getWorld().isClient() ) return ActionResult.PASS;
		if ( ctx.getPlayer().isSneaking() ) {
			if ( type == ZoneTypes.lenght - 1) type = 0;
			else type++;
			ctx.getPlayer().sendMessage( new LiteralText("Zone type set to {}.".replace("{}", ZoneTypes.getType(this.type).toString() ) ), true);
		} else {
			if ( pos0 == null ) {
				this.pos0 = ctx.getBlockPos();
				ctx.getPlayer().sendMessage( new LiteralText("Point 0 set to {}.".replace("{}", this.pos0.toShortString() ) ), true);
			} else {
				this.pos1 = ctx.getBlockPos();
				ctx.getPlayer().sendMessage( new LiteralText("Point 1 set to {}.".replace("{}", this.pos1.toShortString() ) ), true);
			}
		}
		return ActionResult.SUCCESS;
	}

	private void createZone(ItemUsageContext ctx) {
		AbstractZone<? extends Entity> zone = null;
		ZoneComponent mgr = CMTContent.ZONE_COMP_KEY.get( ctx.getWorld() );
		if ( this.type == 0 ) {
			zone = new CommandZone(
					(ServerWorld) ctx.getWorld(),
					Utils.vecFrom(this.pos0),
					Utils.vecFrom(this.pos1),
					mgr.getRandomId(),
					this.data
			);
		} else if ( this.type == 1 ) {
			zone = new FunctionZone(
					(ServerWorld) ctx.getWorld(),
					Utils.vecFrom(this.pos0),
					Utils.vecFrom(this.pos1),
					mgr.getRandomId(),
					this.data
			);
		}
		mgr.addZone( zone );
	}

}

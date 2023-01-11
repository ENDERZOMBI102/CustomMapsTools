package com.enderzombi102.cmt.mixins;

import com.enderzombi102.cmt.registry.ComponentRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin( MinecraftServer.class )
public abstract class MinecraftServerMixin {

	@Shadow
	public abstract Iterable< ServerWorld > getWorlds();

	@Inject( method = "stop", at = @At( "TAIL" ) )
	public void onStop( boolean bl, CallbackInfo info ) {
		for ( var world : this.getWorlds() )
			ComponentRegistry.ZONE_COMPONENT_KEY.get( world );
	}

}

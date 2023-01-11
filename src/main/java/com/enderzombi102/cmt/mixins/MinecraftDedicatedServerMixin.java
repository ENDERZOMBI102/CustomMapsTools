package com.enderzombi102.cmt.mixins;

import com.enderzombi102.cmt.registry.ComponentRegistry;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.datafixers.DataFixer;
import net.minecraft.resource.pack.ResourcePackManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.Services;
import net.minecraft.server.WorldGenerationProgressListenerFactory;
import net.minecraft.server.WorldStem;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.util.UserCache;
import net.minecraft.world.SaveProperties;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSaveStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.Proxy;

@Mixin(MinecraftDedicatedServer.class)
public abstract class MinecraftDedicatedServerMixin extends MinecraftServer {
	public MinecraftDedicatedServerMixin( Thread serverThread, WorldSaveStorage.Session session, ResourcePackManager dataPackManager, WorldStem worldStem, Proxy proxy, DataFixer dataFixer, Services services, WorldGenerationProgressListenerFactory worldGenerationProgressListenerFactory ) {
		super( serverThread, session, dataPackManager, worldStem, proxy, dataFixer, services, worldGenerationProgressListenerFactory );
	}

	@Inject( method = "setupServer", at = @At("TAIL") )
	public void onSetup( CallbackInfoReturnable<Boolean> info ) {
		for ( var world : this.getWorlds() )
			ComponentRegistry.ZONE_COMPONENT_KEY.get( world );
	}

}

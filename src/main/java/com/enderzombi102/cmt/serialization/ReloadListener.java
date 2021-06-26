package com.enderzombi102.cmt.serialization;


import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class ReloadListener implements SimpleSynchronousResourceReloadListener {

	private static final Identifier IDENTIFIER = new Identifier("cmt", "data_reload_listener");

	@Override
	public Identifier getFabricId() {
		return IDENTIFIER;
	}

	@Override
	public void reload(ResourceManager manager) {
		manager.findResources("cmt", path -> path.endsWith(".") );
	}
}

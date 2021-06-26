package com.enderzombi102.cmt.client;

import com.enderzombi102.cmt.CMTContent;
import com.enderzombi102.cmt.serialization.ReloadListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class CustomMapsToolsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		CMTContent.registerClientThings();
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener( new ReloadListener() );
	}

}

package com.enderzombi102.cmt.client;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.SyntaxError;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;

import static com.enderzombi102.cmt.CustomMapsTools.LOGGER;

public class ReloadListener implements SimpleSynchronousResourceReloadListener {

	@Override
	public Identifier getFabricId() {
		return null;
	}

	@Override
	public void apply(ResourceManager manager) {
		for( Identifier id : manager.findResources("keybinds", path -> path.endsWith(".json5"))) {
			try( InputStream stream = manager.getResource(id).getInputStream() ) {
				final JsonObject keybindObject = Jankson.builder().build().load( stream );


			} catch (SyntaxError e) {
				// user syntax error
				LOGGER.error( e.getMessage() );
				LOGGER.error( e.getLineMessage() );
			} catch(Exception e) {
				// general error
				LOGGER.error("Error occurred while loading resource json5: " + id.toString(), e);
			}

		}
	}
}

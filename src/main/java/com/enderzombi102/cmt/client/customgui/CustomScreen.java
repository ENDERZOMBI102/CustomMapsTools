package com.enderzombi102.cmt.client.customgui;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonArray;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.SyntaxError;
import com.enderzombi102.cmt.Utils;
import com.enderzombi102.cmt.exception.MissingKeyException;
import me.lambdaurora.spruceui.screen.SpruceScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import static com.enderzombi102.cmt.CustomMapsTools.LOGGER;

public class CustomScreen extends SpruceScreen {

	private boolean isInventory = false;


	private CustomScreen(JsonObject object, String name) throws SyntaxError {
		super( new LiteralText( name ) );
		// get screen content
		final JsonArray content = Utils.checkMissingKey( (JsonArray) object.get("content"), "content");
		// cycle and create widgets
		for ( JsonElement rawObj : content ) {
			// obj check
			if (! ( rawObj instanceof JsonObject ) ) throw new SyntaxError("Expected json object, found something else!");
			// variables
			final JsonObject obj = (JsonObject) rawObj;
			final String type = Utils.checkMissingKey( obj.get(String.class, "type"), "type" );
			// widget!
			switch ( type ) {
				case "button":
					this.addChild( WidgetBuilders.ButtonBuilder.create( obj ) );
					break;
				case "text":
					this.addChild( WidgetBuilders.TextBuilder.create( obj ) );
					break;
				default:
					LOGGER.warn("Unknown widget type \"" + type + "\", ignoring");
			}
		}
	}


	public static @Nullable CustomScreen create(Identifier id) {
		final Resource resource;
		try {
			// get the resource associated with this id
			resource = MinecraftClient.getInstance().getResourceManager().getResource(id);
			// read the json object from the inputstream
			final JsonObject object = Jankson.builder().build().load( resource.getInputStream() );
			// get the screen name
			final String name = object.get( String.class, "name" );
			// name is a required key, if its null, it wasn't found
			if ( name == null ) throw new MissingKeyException("name");
			// name found, create screen
			return new CustomScreen( object, name );
		} catch (IOException e) {
			// no resource with that name
			LOGGER.error("Couldn't read the screen file for \"" + id + "\"", e);
		} catch ( MissingKeyException e ) {
			// grave user error
			LOGGER.error("Couldn't find required \"" + e.getKey() + "\" key in root object for screen \" " + id + "\", aborting", e);
		} catch (SyntaxError e) {
			// user syntax error
			LOGGER.error( e.getMessage() );
			LOGGER.error( e.getLineMessage() );
		}
		// caught an exception so return null
		return  null;
	}




}

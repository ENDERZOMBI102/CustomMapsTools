package com.enderzombi102.cmt.client.window;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.util.Window;
import net.minecraft.util.ActionResult;

public interface WindowMoveCallback {

	Event<WindowMoveCallback> EVENT = EventFactory.createArrayBacked( WindowMoveCallback.class,
			(listeners) -> (window, oldX, oldY) -> {
				for (WindowMoveCallback listener : listeners) {
					ActionResult result = listener.process( window, oldX, oldY );

					if(result != ActionResult.PASS) {
						return result;
					}
				}

				return ActionResult.PASS;
			});

	ActionResult process(Window window, int oldX, int oldY);
}
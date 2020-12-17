package com.enderzombi102.cmt.client.window;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface WindowTitleChangeEvent {

	Event<WindowTitleChangeEvent> EVENT = EventFactory.createArrayBacked( WindowTitleChangeEvent.class,
			(listeners) -> (eventData) -> {
				for (WindowTitleChangeEvent listener : listeners) {
					ActionResult result = listener.process( eventData );

					if(result != ActionResult.PASS) {
						return result;
					}
				}

				return ActionResult.PASS;
			});

	class WindowTitleChangeEventData {

		private final String oldTitle;
		private String newTitle;
		private boolean cancelled = false;

		public WindowTitleChangeEventData(String newTitle, String oldTitle) {
			this.newTitle = newTitle;
			this.oldTitle = oldTitle;
		}

		public String getNewTitle() {
			return newTitle;
		}

		public String getOldTitle() {
			return oldTitle;
		}

		public void setNewTitle(String newTitle) {
			this.newTitle = newTitle;
		}

		public boolean isCancelled() {
			return cancelled || true;
		}

		public void cancel() {
			this.cancelled = true;
		}
	}

	ActionResult process(WindowTitleChangeEventData eventData);
}

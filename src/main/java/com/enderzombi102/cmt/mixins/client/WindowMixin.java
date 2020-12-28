package com.enderzombi102.cmt.mixins.client;

import com.enderzombi102.cmt.client.WindowHandler;
import com.enderzombi102.cmt.client.window.WindowMoveCallback;
import com.enderzombi102.cmt.client.window.WindowTitleChangeEvent;
import net.minecraft.client.util.Window;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Window.class)
public abstract class WindowMixin {

	@Shadow private int y;

	@Shadow private int x;

	@Shadow @Final private long handle;

	@Shadow public abstract int getX();

	@Shadow public abstract int getY();

	@Inject(at = @At("HEAD"), method = "onWindowPosChanged", cancellable = true)
	private void onWindowPosChanged(long window, int x, int y, CallbackInfo info) {

		ActionResult result = WindowMoveCallback.EVENT.invoker().process( (Window) (Object) this, this.getX(), this.getY() );

		if (result == ActionResult.FAIL) {
			org.lwjgl.glfw.GLFW.glfwSetWindowPos(this.handle, this.getX(), this.getY() );
			info.cancel();
		}
	}

	@Inject(at=@At("HEAD"), method = "setTitle", cancellable = true )
	public void onSetTitle(String title, CallbackInfo info) {

		WindowTitleChangeEvent.WindowTitleChangeEventData data = new WindowTitleChangeEvent.WindowTitleChangeEventData(
				title, WindowHandler.getTitle()
		);
		WindowTitleChangeEvent.EVENT.invoker().process( data );

		if ( data.isCancelled() ) {
			title = data.getOldTitle();
		} else {
			title = data.getNewTitle();
		}

		WindowHandler.windowTitle = title;

	}
}
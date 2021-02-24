package com.enderzombi102.cmt.keybind;

import com.enderzombi102.cmt.keybind.client.Key;
import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.client.MinecraftClient;

import java.util.function.Consumer;


public interface KeybindComponent extends Component {

	void createBind(Key key, Consumer<MinecraftClient> callback, String category, String name);


}

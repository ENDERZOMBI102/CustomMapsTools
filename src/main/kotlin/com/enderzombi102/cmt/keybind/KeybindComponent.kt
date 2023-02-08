package com.enderzombi102.cmt.keybind;

import dev.onyxstudios.cca.api.v3.component.Component;


public interface KeybindComponent extends Component {

	void createBind(String key, String category, String name, String executes);

}

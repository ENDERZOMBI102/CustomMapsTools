package com.enderzombi102.cmt.keybind

import dev.onyxstudios.cca.api.v3.component.Component

interface KeybindComponent : Component {
	fun createBind(key: String?, category: String?, name: String?, executes: String?)
}

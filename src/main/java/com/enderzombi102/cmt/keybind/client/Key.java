package com.enderzombi102.cmt.keybind.client;

import java.util.Objects;

public class Key {

	private final int GLFW_KEY_CODE;

	public Key(int key) {
		this.GLFW_KEY_CODE = key;
	}

	public int getCode() {
		return this.GLFW_KEY_CODE;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if ( other == null || getClass() != other.getClass() ) return false;
		return GLFW_KEY_CODE == ( (Key) other ).GLFW_KEY_CODE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(GLFW_KEY_CODE);
	}

	/** keyboard keys */
	public static Key GLFW_KEY_SPACE         = new Key(32);
	public static Key GLFW_KEY_APOSTROPHE    = new Key(39);
	public static Key GLFW_KEY_COMMA         = new Key(44);
	public static Key GLFW_KEY_MINUS         = new Key(45);
	public static Key GLFW_KEY_PERIOD        = new Key(46);
	public static Key GLFW_KEY_SLASH         = new Key(47);
	public static Key GLFW_KEY_0             = new Key(48);
	public static Key GLFW_KEY_1             = new Key(49);
	public static Key GLFW_KEY_2             = new Key(50);
	public static Key GLFW_KEY_3             = new Key(51);
	public static Key GLFW_KEY_4             = new Key(52);
	public static Key GLFW_KEY_5             = new Key(53);
	public static Key GLFW_KEY_6             = new Key(54);
	public static Key GLFW_KEY_7             = new Key(55);
	public static Key GLFW_KEY_8             = new Key(56);
	public static Key GLFW_KEY_9             = new Key(57);
	public static Key GLFW_KEY_SEMICOLON     = new Key(59);
	public static Key GLFW_KEY_EQUAL         = new Key(61);
	public static Key GLFW_KEY_A             = new Key(65);
	public static Key GLFW_KEY_B             = new Key(66);
	public static Key GLFW_KEY_C             = new Key(67);
	public static Key GLFW_KEY_D             = new Key(68);
	public static Key GLFW_KEY_E             = new Key(69);
	public static Key GLFW_KEY_F             = new Key(70);
	public static Key GLFW_KEY_G             = new Key(71);
	public static Key GLFW_KEY_H             = new Key(72);
	public static Key GLFW_KEY_I             = new Key(73);
	public static Key GLFW_KEY_J             = new Key(74);
	public static Key GLFW_KEY_K             = new Key(75);
	public static Key GLFW_KEY_L             = new Key(76);
	public static Key GLFW_KEY_M             = new Key(77);
	public static Key GLFW_KEY_N             = new Key(78);
	public static Key GLFW_KEY_O             = new Key(79);
	public static Key GLFW_KEY_P             = new Key(80);
	public static Key GLFW_KEY_Q             = new Key(81);
	public static Key GLFW_KEY_R             = new Key(82);
	public static Key GLFW_KEY_S             = new Key(83);
	public static Key GLFW_KEY_T             = new Key(84);
	public static Key GLFW_KEY_U             = new Key(85);
	public static Key GLFW_KEY_V             = new Key(86);
	public static Key GLFW_KEY_W             = new Key(87);
	public static Key GLFW_KEY_X             = new Key(88);
	public static Key GLFW_KEY_Y             = new Key(89);
	public static Key GLFW_KEY_Z             = new Key(90);
	public static Key GLFW_KEY_LEFT_BRACKET  = new Key(91);
	public static Key GLFW_KEY_BACKSLASH     = new Key(92);
	public static Key GLFW_KEY_RIGHT_BRACKET = new Key(93);
	public static Key GLFW_KEY_GRAVE_ACCENT  = new Key(96);
	public static Key GLFW_KEY_WORLD_1       = new Key(161);
	public static Key GLFW_KEY_WORLD_2       = new Key(162);

	/** Function keys. */
	public static Key GLFW_KEY_ESCAPE        = new Key(256);
	public static Key GLFW_KEY_ENTER         = new Key(257);
	public static Key GLFW_KEY_TAB           = new Key(258);
	public static Key GLFW_KEY_BACKSPACE     = new Key(259);
	public static Key GLFW_KEY_INSERT        = new Key(260);
	public static Key GLFW_KEY_DELETE        = new Key(261);
	public static Key GLFW_KEY_RIGHT         = new Key(262);
	public static Key GLFW_KEY_LEFT          = new Key(263);
	public static Key GLFW_KEY_DOWN          = new Key(264);
	public static Key GLFW_KEY_UP            = new Key(265);
	public static Key GLFW_KEY_PAGE_UP       = new Key(266);
	public static Key GLFW_KEY_PAGE_DOWN     = new Key(267);
	public static Key GLFW_KEY_HOME          = new Key(268);
	public static Key GLFW_KEY_END           = new Key(269);
	public static Key GLFW_KEY_CAPS_LOCK     = new Key(280);
	public static Key GLFW_KEY_SCROLL_LOCK   = new Key(281);
	public static Key GLFW_KEY_NUM_LOCK      = new Key(282);
	public static Key GLFW_KEY_PRINT_SCREEN  = new Key(283);
	public static Key GLFW_KEY_PAUSE         = new Key(284);
	public static Key GLFW_KEY_F1            = new Key(290);
	public static Key GLFW_KEY_F2            = new Key(291);
	public static Key GLFW_KEY_F3            = new Key(292);
	public static Key GLFW_KEY_F4            = new Key(293);
	public static Key GLFW_KEY_F5            = new Key(294);
	public static Key GLFW_KEY_F6            = new Key(295);
	public static Key GLFW_KEY_F7            = new Key(296);
	public static Key GLFW_KEY_F8            = new Key(297);
	public static Key GLFW_KEY_F9            = new Key(298);
	public static Key GLFW_KEY_F10           = new Key(299);
	public static Key GLFW_KEY_F11           = new Key(300);
	public static Key GLFW_KEY_F12           = new Key(301);
	public static Key GLFW_KEY_F13           = new Key(302);
	public static Key GLFW_KEY_F14           = new Key(303);
	public static Key GLFW_KEY_F15           = new Key(304);
	public static Key GLFW_KEY_F16           = new Key(305);
	public static Key GLFW_KEY_F17           = new Key(306);
	public static Key GLFW_KEY_F18           = new Key(307);
	public static Key GLFW_KEY_F19           = new Key(308);
	public static Key GLFW_KEY_F20           = new Key(309);
	public static Key GLFW_KEY_F21           = new Key(310);
	public static Key GLFW_KEY_F22           = new Key(311);
	public static Key GLFW_KEY_F23           = new Key(312);
	public static Key GLFW_KEY_F24           = new Key(313);
	public static Key GLFW_KEY_F25           = new Key(314);
	public static Key GLFW_KEY_KP_0          = new Key(320);
	public static Key GLFW_KEY_KP_1          = new Key(321);
	public static Key GLFW_KEY_KP_2          = new Key(322);
	public static Key GLFW_KEY_KP_3          = new Key(323);
	public static Key GLFW_KEY_KP_4          = new Key(324);
	public static Key GLFW_KEY_KP_5          = new Key(325);
	public static Key GLFW_KEY_KP_6          = new Key(326);
	public static Key GLFW_KEY_KP_7          = new Key(327);
	public static Key GLFW_KEY_KP_8          = new Key(328);
	public static Key GLFW_KEY_KP_9          = new Key(329);
	public static Key GLFW_KEY_KP_DECIMAL    = new Key(330);
	public static Key GLFW_KEY_KP_DIVIDE     = new Key(331);
	public static Key GLFW_KEY_KP_MULTIPLY   = new Key(332);
	public static Key GLFW_KEY_KP_SUBTRACT   = new Key(333);
	public static Key GLFW_KEY_KP_ADD        = new Key(334);
	public static Key GLFW_KEY_KP_ENTER      = new Key(335);
	public static Key GLFW_KEY_KP_EQUAL      = new Key(336);
	public static Key GLFW_KEY_LEFT_SHIFT    = new Key(340);
	public static Key GLFW_KEY_LEFT_CONTROL  = new Key(341);
	public static Key GLFW_KEY_LEFT_ALT      = new Key(342);
	public static Key GLFW_KEY_LEFT_SUPER    = new Key(343);
	public static Key GLFW_KEY_RIGHT_SHIFT   = new Key(344);
	public static Key GLFW_KEY_RIGHT_CONTROL = new Key(345);
	public static Key GLFW_KEY_RIGHT_ALT     = new Key(346);
	public static Key GLFW_KEY_RIGHT_SUPER   = new Key(347);
	public static Key GLFW_KEY_MENU          = new Key(348);
	public static Key GLFW_KEY_LAST          = GLFW_KEY_MENU;
}

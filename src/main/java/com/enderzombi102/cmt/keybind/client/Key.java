package com.enderzombi102.cmt.keybind.client;

import org.jetbrains.annotations.Nullable;
import java.util.Arrays;
import java.util.Optional;

public enum Key {

	/** keyboard keys */
	GLFW_KEY_SPACE(32, "space"),
	GLFW_KEY_APOSTROPHE(39, "apostrophe"),
	GLFW_KEY_COMMA(44, "comma"),
	GLFW_KEY_MINUS(45, "minus"),
	GLFW_KEY_PERIOD(46, "period"),
	GLFW_KEY_SLASH(47, "slash"),
	GLFW_KEY_0(48, "0"),
	GLFW_KEY_1(49, "1"),
	GLFW_KEY_2(50, "2"),
	GLFW_KEY_3(51, "3"),
	GLFW_KEY_4(52, "4"),
	GLFW_KEY_5(53, "5"),
	GLFW_KEY_6(54, "6"),
	GLFW_KEY_7(55, "7"),
	GLFW_KEY_8(56, "8"),
	GLFW_KEY_9(57, "9"),
	GLFW_KEY_SEMICOLON(59, "semicolon"),
	GLFW_KEY_EQUAL(61, "equal"),
	GLFW_KEY_A(65, "a"),
	GLFW_KEY_B(66, "b"),
	GLFW_KEY_C(67, "c"),
	GLFW_KEY_D(68, "d"),
	GLFW_KEY_E(69, "e"),
	GLFW_KEY_F(70, "f"),
	GLFW_KEY_G(71, "g"),
	GLFW_KEY_H(72, "h"),
	GLFW_KEY_I(73, "i"),
	GLFW_KEY_J(74, "j"),
	GLFW_KEY_K(75, "k"),
	GLFW_KEY_L(76, "l"),
	GLFW_KEY_M(77, "m"),
	GLFW_KEY_N(78, "n"),
	GLFW_KEY_O(79, "o"),
	GLFW_KEY_P(80, "p"),
	GLFW_KEY_Q(81, "q"),
	GLFW_KEY_R(82, "r"),
	GLFW_KEY_S(83, "s"),
	GLFW_KEY_T(84, "t"),
	GLFW_KEY_U(85, "u"),
	GLFW_KEY_V(86, "v"),
	GLFW_KEY_W(87, "w"),
	GLFW_KEY_X(88, "x"),
	GLFW_KEY_Y(89, "y"),
	GLFW_KEY_Z(90, "z"),
	GLFW_KEY_LEFT_BRACKET(91, "left_bracket"),
	GLFW_KEY_BACKSLASH(92, "backslash"),
	GLFW_KEY_RIGHT_BRACKET(93, "right_bracket"),
	GLFW_KEY_GRAVE_ACCENT(96, "grave_accent"),
	GLFW_KEY_WORLD_1(161, "world_1"),
	GLFW_KEY_WORLD_2(162, "world_2"),

	/** Function keys. */
	GLFW_KEY_ESCAPE(256, "escape"),
	GLFW_KEY_ENTER(257, "enter"),
	GLFW_KEY_TAB(258, "tab"),
	GLFW_KEY_BACKSPACE(259, "backspace"),
	GLFW_KEY_INSERT(260, "insert"),
	GLFW_KEY_DELETE(261, "delete"),
	GLFW_KEY_RIGHT(262, "right"),
	GLFW_KEY_LEFT(263, "left"),
	GLFW_KEY_DOWN(264, "down"),
	GLFW_KEY_UP(265, "up"),
	GLFW_KEY_PAGE_UP(266, "page_up"),
	GLFW_KEY_PAGE_DOWN(267, "page_down"),
	GLFW_KEY_HOME(268, "home"),
	GLFW_KEY_END(269, "end"),
	GLFW_KEY_CAPS_LOCK(280, "caps_lock"),
	GLFW_KEY_SCROLL_LOCK(281, "scroll_lock"),
	GLFW_KEY_NUM_LOCK(282, "num_lock"),
	GLFW_KEY_PRINT_SCREEN(283, "print_screen"),
	GLFW_KEY_PAUSE(284, "pause"),
	GLFW_KEY_F1(290, "f1"),
	GLFW_KEY_F2(291, "f2"),
	GLFW_KEY_F3(292, "f3"),
	GLFW_KEY_F4(293, "f4"),
	GLFW_KEY_F5(294, "f5"),
	GLFW_KEY_F6(295, "f6"),
	GLFW_KEY_F7(296, "f7"),
	GLFW_KEY_F8(297, "f8"),
	GLFW_KEY_F9(298, "f9"),
	GLFW_KEY_F10(299, "f10"),
	GLFW_KEY_F11(300, "f11"),
	GLFW_KEY_F12(301, "f12"),
	GLFW_KEY_F13(302, "f13"),
	GLFW_KEY_F14(303, "f14"),
	GLFW_KEY_F15(304, "f15"),
	GLFW_KEY_F16(305, "f16"),
	GLFW_KEY_F17(306, "f17"),
	GLFW_KEY_F18(307, "f18"),
	GLFW_KEY_F19(308, "f19"),
	GLFW_KEY_F20(309, "f20"),
	GLFW_KEY_F21(310, "f21"),
	GLFW_KEY_F22(311, "f22"),
	GLFW_KEY_F23(312, "f23"),
	GLFW_KEY_F24(313, "f25"),
	GLFW_KEY_F25(314, "f25"),
	GLFW_KEY_KP_0(320, "kp_0"),
	GLFW_KEY_KP_1(321, "kp_1"),
	GLFW_KEY_KP_2(322, "kp_2"),
	GLFW_KEY_KP_3(323, "kp_3"),
	GLFW_KEY_KP_4(324, "kp_4"),
	GLFW_KEY_KP_5(325, "kp_5"),
	GLFW_KEY_KP_6(326, "kp_6"),
	GLFW_KEY_KP_7(327, "kp_7"),
	GLFW_KEY_KP_8(328, "kp_8"),
	GLFW_KEY_KP_9(329, "kp_9"),
	GLFW_KEY_KP_DECIMAL(330, "kp_decimal"),
	GLFW_KEY_KP_DIVIDE(331, "kp_divide"),
	GLFW_KEY_KP_MULTIPLY(332, "kp_multiply"),
	GLFW_KEY_KP_SUBTRACT(333, "kp_substract"),
	GLFW_KEY_KP_ADD(334, "kp_add"),
	GLFW_KEY_KP_ENTER(335, "kp_enter"),
	GLFW_KEY_KP_EQUAL(336, "kp_equal"),
	GLFW_KEY_LEFT_SHIFT(340, "left_shift"),
	GLFW_KEY_LEFT_CONTROL(341, "left_control"),
	GLFW_KEY_LEFT_ALT(342, "left_alt"),
	GLFW_KEY_LEFT_SUPER(343, "left_super"),
	GLFW_KEY_RIGHT_SHIFT(344, "right_shift"),
	GLFW_KEY_RIGHT_CONTROL(345, "right_control"),
	GLFW_KEY_RIGHT_ALT(346, "right_alt"),
	GLFW_KEY_RIGHT_SUPER(347, "right_super"),
	GLFW_KEY_MENU(348, "menu"),
	GLFW_KEY_LAST(348, "last");


	private final int GLFW_KEY_CODE;
	private final String name;

	Key(int key, String name) {
		this.GLFW_KEY_CODE = key;
		this.name = name;
	}

	public int getCode() {
		return this.GLFW_KEY_CODE;
	}

	public String getName() {
		return this.name;
	}

	public static @Nullable Key findKey(String key) {
		final Optional<Key> foundKey = Arrays.stream( values() ).filter(key1 -> key1.getName().equals(key) ).findFirst();
		return foundKey.orElse(null);
	}


}

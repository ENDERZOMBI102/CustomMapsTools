package com.enderzombi102.cmt.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu( modId = "cmt" )
@Config( name = "my-config", wrapperName = "CmtConfig" )
public class CmtConfigModel {
	boolean toggleA = true;
	boolean toggleB = false;
}

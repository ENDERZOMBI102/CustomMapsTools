package com.enderzombi102.cmt.exception

import blue.endless.jankson.api.SyntaxError

class MissingKeyException( val key: String ) : SyntaxError( "" )

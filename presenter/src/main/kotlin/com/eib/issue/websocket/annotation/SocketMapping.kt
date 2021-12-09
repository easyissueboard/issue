package com.eib.issue.websocket.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SocketMapping(
    val endpoint: String,
    val requestDto: KClass<*>
)

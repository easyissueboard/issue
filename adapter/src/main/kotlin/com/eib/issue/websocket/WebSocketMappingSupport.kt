package com.eib.issue.websocket

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import com.eib.issue.websocket.annotation.SocketHandler
import com.eib.issue.websocket.annotation.SocketMapping
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.getBeansWithAnnotation
import org.springframework.stereotype.Component
import java.lang.reflect.Method
import kotlin.math.log
import kotlin.reflect.full.primaryConstructor

@Component
class WebSocketMappingSupport (
    private val beanFactory: ConfigurableListableBeanFactory
) {

    private val logger = LoggerFactory.getLogger(WebSocketMappingSupport::class.java)

    private lateinit var initialize: SocketIOServer

    private val socketIOServer: SocketIOServer by lazy { initialize }

    fun addListeners(socketIOServer: SocketIOServer) {
        this.initialize = socketIOServer

        val classes = beanFactory.getBeansWithAnnotation<SocketHandler>()
            .values.map { it::class.java }
            .toList()

        for (cls in classes) {
            val methods = findAnnotatedMethods(cls)
            addEventListener(cls, methods)
        }
    }

    private fun findAnnotatedMethods(cls: Class<*>) = cls.methods
        .filter { it.getAnnotation(SocketMapping::class.java) != null }
        .toList()

    private fun addEventListener(handler: Class<*>, methods: List<Method>) {
        for (method in methods) {
            val socketMapping: SocketMapping = method.getAnnotation(SocketMapping::class.java)
            val endpoint = socketMapping.endpoint

            val dtoClass = socketMapping.requestDto.java

            socketIOServer.addEventListener(endpoint, dtoClass) { client, data, _ ->
                val args = mutableListOf<Any>()

                for (params in method.parameterTypes) {
                    when (params) {
                        SocketIOServer::class.java -> args += socketIOServer
                        SocketIOClient::class.java -> args += client
                        dtoClass -> args += data
                    }
                }

                method.invoke(beanFactory.getBean(handler), *args.toTypedArray())
            }
        }
    }
}
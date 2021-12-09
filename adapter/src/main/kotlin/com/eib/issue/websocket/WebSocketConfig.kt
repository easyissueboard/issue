package com.eib.issue.websocket

import com.corundumstudio.socketio.Configuration
import com.corundumstudio.socketio.SocketConfig
import com.corundumstudio.socketio.SocketIOServer
import com.corundumstudio.socketio.protocol.JacksonJsonSupport
import com.eib.issue.websocket.exception.SocketExceptionListener
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class WebSocketConfig (
    private val mappingSupport: WebSocketMappingSupport,
    private val exceptionListener: SocketExceptionListener,

    @Value("\${socket.port}")
    private val port: Int
) {
    @Bean
    fun socketIOServer(): SocketIOServer {
        val config = Configuration()
        config.port = port
        config.origin = "*"
        config.exceptionListener = exceptionListener
        config.jsonSupport = JacksonJsonSupport(kotlinModule())

        val socketConfig = SocketConfig()
        socketConfig.isReuseAddress = true
        config.socketConfig = socketConfig

        val server = SocketIOServer(config)
        mappingSupport.addListeners(server)

        return server
    }
}
package com.eib.issue.websocket.exception

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.listener.ExceptionListener
import io.netty.channel.ChannelHandlerContext
import org.springframework.stereotype.Component
import kotlin.Exception

@Component
class SocketExceptionListener : ExceptionListener {

    override fun onConnectException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun onDisconnectException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun onEventException(e: Exception, args: MutableList<*>, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun onPingException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    private fun runExceptionHandling(e: Exception, client: SocketIOClient) {

    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, e: Throwable) = false
}
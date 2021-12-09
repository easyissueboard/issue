package com.eib.issue.board

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import com.eib.issue.board.dto.BoardDto
import com.eib.issue.websocket.annotation.SocketHandler
import com.eib.issue.websocket.annotation.SocketMapping

@SocketHandler
class BoardHandler {

    @SocketMapping("test", BoardDto.Request::class)
    fun test(client: SocketIOClient, server: SocketIOServer, request: BoardDto.Request) {
        client.joinRoom("test")
        server.getRoomOperations("test").sendEvent("test", BoardDto.Response(request.message))
    }
}
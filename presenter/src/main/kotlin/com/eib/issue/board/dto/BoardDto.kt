package com.eib.issue.board.dto

open class BoardDto {
    open class Request (
        val message: String
    )

    open class Response (
        val message: String
    )
}


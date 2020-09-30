package com.groundzero.camw.messaging.data

import java.util.*

data class NotificationMessage(
        val id: String = UUID.randomUUID().toString(),
        val email: String,
        val author: String,
        val topic: String,
        val image: String = "",
        val messageType: Int,
        val title: String,
        val text: String
)
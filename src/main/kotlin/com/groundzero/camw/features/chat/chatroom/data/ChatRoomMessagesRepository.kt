package com.groundzero.camw.features.chat.chatroom.data

import com.groundzero.camw.features.chat.chatroom.network.ChatRoomMessageResponse
import com.groundzero.camw.features.chat.chatroom.network.ChatRoomMessageRequest

interface ChatRoomMessagesRepository {
    fun insertMessage(roomId: String, request: ChatRoomMessageRequest)
    fun updateMessage(roomId: String, request: ChatRoomMessageRequest)
    fun deleteMessage(roomId: String, messageId: String)
    fun getMessagesPerRoomIdFromMemory(roomId: String): List<ChatRoomMessageResponse>
}
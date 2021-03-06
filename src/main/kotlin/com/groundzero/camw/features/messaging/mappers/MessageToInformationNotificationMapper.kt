package com.groundzero.camw.features.messaging.mappers

import com.groundzero.camw.core.data.Mapper
import com.groundzero.camw.features.messaging.data.InformationNotificationResponse
import com.groundzero.camw.features.messaging.data.NotificationRequest
import org.springframework.stereotype.Component

@Component
class MessageToInformationNotificationMapper : Mapper<NotificationRequest, InformationNotificationResponse> {

    override fun map(data: NotificationRequest): InformationNotificationResponse = InformationNotificationResponse(
            notificationType = 1,
            title = data.title,
            text = data.text,
            url = data.url
    )
}
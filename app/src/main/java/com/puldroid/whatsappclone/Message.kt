package com.puldroid.whatsappclone

import java.util.*

interface ChatEvent {
    val sentAt: Date
}

data class Message(
    val msg: String,
    val senderId: String,
    var msgId: String = "",
    val type: String = "TEXT",
    val status: Int = 1,
    val highFive: Boolean = false,
    override val sentAt: Date = Date(),
    val time: Long = System.currentTimeMillis()
) : ChatEvent {

    /** Empty [Constructor] for Firebase */
    constructor() : this("", "", "", "", 1, false, Date(0L))
}

data class DateHeader(val date: String)
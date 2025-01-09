package com.sport.workoutapp.data.model

import android.icu.util.Calendar
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class Training(var title: String = "") {
    var date: Calendar = Calendar.getInstance()
    var id: UUID = UUID.randomUUID()

    constructor(json: JSONObject) : this() {
        title = json.getString("TITLE")
        id = UUID.fromString(json.getString("ID"))
        date = Calendar.getInstance().apply {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            time = dateFormat.parse(json.getString("DATE")) ?: Date()
        }
    }

    fun toJSON(): JSONObject {
        val json = JSONObject()
        json.put("TITLE", title)
        json.put("ID", id)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        json.put("DATE", dateFormat.format(date.time))

        return json
    }
}
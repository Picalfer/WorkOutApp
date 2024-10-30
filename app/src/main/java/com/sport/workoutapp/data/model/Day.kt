package com.sport.workoutapp.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.BsonObjectId.Companion.invoke
import org.mongodb.kbson.ObjectId

class Day : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var title: String = "New day"
    var color: Int = Color.Green.toArgb()
    var exercises: RealmList<Exercise> = realmListOf()

    fun getColor(): Color {
        return Color(color)
    }
}
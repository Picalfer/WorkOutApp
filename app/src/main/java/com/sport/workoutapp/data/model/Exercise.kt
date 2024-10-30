package com.sport.workoutapp.data.model

import androidx.annotation.RawRes
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.BsonObjectId.Companion.invoke
import org.mongodb.kbson.ObjectId

class Exercise : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var title: String = ""
    var isDone: Boolean = false
    var type: String = ExerciseType.Standard.name
    var sets: String = ""
    var reps: String = ""

    @RawRes
    var gifResId: Int? = null

    fun copy(
        _id: ObjectId = this._id,
        title: String = this.title,
        isDone: Boolean = this.isDone,
        type: String = this.type,
        sets: String = this.sets,
        reps: String = this.reps,
        gifResId: Int? = this.gifResId
    ): Exercise {
        val newExercise = Exercise()
        newExercise._id = _id
        newExercise.title = title
        newExercise.isDone = isDone
        newExercise.type = type
        newExercise.sets = sets
        newExercise.reps = reps
        newExercise.gifResId = gifResId
        return newExercise
    }
}

enum class ExerciseType {
    WarmUp,
    Standard,
    WarmDown
}
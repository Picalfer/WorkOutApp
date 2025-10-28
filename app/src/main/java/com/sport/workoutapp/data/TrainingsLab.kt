package com.sport.workoutapp.data

import android.content.Context
import android.util.Log
import com.sport.workoutapp.data.model.Training
import java.util.UUID

class TrainingsLab(context: Context) {
    private val trainings = arrayListOf<Training>()
    private val serializer = WorkOutAppJSONSerializer(context, FILENAME)

    init {
        try {
            trainings.addAll(serializer.loadTrainings())
        } catch (e: Exception) {
            Log.e(TAG, "Error loading trainings: ", e)
        }
    }

    fun getTrainings(): ArrayList<Training> {
        return trainings
    }

    fun addTraining(training: Training) {
        trainings.add(training)
        saveTrainings()
    }

    fun deleteTraining(training: Training) {
        trainings.remove(training)
        saveTrainings()
    }

    fun getTraining(id: UUID): Training? {
        for (training in trainings) {
            if (training.id == id) {
                return training
            }
        }
        return null
    }

    fun saveTrainings(): Boolean {
        try {
            serializer.saveTrainings(trainings)
            Log.d(TAG, "Trainings saved to file")
            return true
        } catch (e: Exception) {
            Log.d(TAG, "Error saving trainings: ", e)
            return false
        }
    }

    companion object {
        const val FILENAME = "trainings.json"
        const val TAG = "TrainingLab"

        private var trainingLab: TrainingsLab? = null

        @JvmStatic
        fun getInstance(context: Context): TrainingsLab {
            if (trainingLab == null) {
                trainingLab = TrainingsLab(context)
                return trainingLab as TrainingsLab
            }
            return trainingLab!!
        }
    }
}
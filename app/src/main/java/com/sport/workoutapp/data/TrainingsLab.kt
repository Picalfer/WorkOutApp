package com.sport.workoutapp.data

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.sport.workoutapp.data.model.Training
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
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

    suspend fun importTrainingsFromUri(uri: Uri, context: Context): Int {
        return withContext(Dispatchers.IO) {
            try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    val jsonString = inputStream.bufferedReader().use { it.readText() }
                    val jsonArray = JSONArray(jsonString)
                    val importedTrainings = ArrayList<Training>()

                    for (i in 0 until jsonArray.length()) {
                        importedTrainings.add(Training(jsonArray.getJSONObject(i)))
                    }

                    // Объединяем с существующими данными
                    val existingTrainings = getTrainings()
                    val mergedTrainings = mergeTrainings(existingTrainings, importedTrainings)

                    // Очищаем и сохраняем объединенные данные
                    getTrainings().clear()
                    getTrainings().addAll(mergedTrainings)
                    saveTrainings()

                    importedTrainings.size
                } ?: 0
            } catch (e: Exception) {
                Log.e(TAG, "Import error", e)
                0
            }
        }
    }

    suspend fun exportTrainings(context: Context): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val jsonArray = JSONArray()
                for (training in trainings) {
                    jsonArray.put(training.toJSON())
                }

                // Используем MediaStore для Android 10+
                val contentValues = ContentValues().apply {
                    put(
                        MediaStore.MediaColumns.DISPLAY_NAME,
                        "workout_statistics_${System.currentTimeMillis()}.json"
                    )
                    put(MediaStore.MediaColumns.MIME_TYPE, "application/json")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }

                val resolver = context.contentResolver
                val uri = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)

                uri?.let {
                    resolver.openOutputStream(it)?.use { outputStream ->
                        outputStream.write(jsonArray.toString().toByteArray())
                    }
                }

                uri != null
            } catch (e: Exception) {
                Log.e(TAG, "Export error", e)
                false
            }
        }
    }

    private fun mergeTrainings(existing: List<Training>, imported: List<Training>): List<Training> {
        val merged = existing.toMutableList()
        val existingIds = existing.map { it.id }.toSet()

        for (training in imported) {
            if (!existingIds.contains(training.id)) {
                merged.add(training)
            }
        }

        return merged.sortedBy { it.date.timeInMillis }
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
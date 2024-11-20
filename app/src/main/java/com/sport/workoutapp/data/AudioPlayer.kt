package com.sport.workoutapp.data

import android.content.Context
import android.media.MediaPlayer
import com.sport.workoutapp.R

class AudioPlayer(private val context: Context) {
    private var player: MediaPlayer? = null

    fun playGong() {
        stopGong()
        player = MediaPlayer.create(context, R.raw.gong_sound)

        player?.setOnCompletionListener {
            stopGong()
        }

        player?.let {
            player!!.start()
        }
    }

    fun stopGong() {
        player?.release()
        player = null
    }
}
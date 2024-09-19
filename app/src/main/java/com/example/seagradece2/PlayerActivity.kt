package com.example.seagradece2

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.util.Log

class PlayerActivity : Activity() {
    private lateinit var audioNameText: TextView
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        audioNameText = findViewById(R.id.audioNameText)
        playButton = findViewById(R.id.playButton)
        pauseButton = findViewById(R.id.pauseButton)
        stopButton = findViewById(R.id.stopButton)

        val selectedAudio = intent.getStringExtra("selectedAudio")
        audioNameText.text = selectedAudio

        val audioResource = when(selectedAudio) {
            "Reggaeton" -> R.raw.reague_song
            "Balada" -> R.raw.ballad
            "Rock Clásico" -> R.raw.classic_rock
            "Cumbia" -> R.raw.cumbia_classic
            "Electrónica" -> R.raw.electronic
            else -> R.raw.default_song
        }

        try {
            mediaPlayer = MediaPlayer.create(this, audioResource)
            mediaPlayer.setOnPreparedListener {
                Log.d("PlayerActivity", "MediaPlayer prepared successfully")
            }
            mediaPlayer.setOnErrorListener { mp, what, extra ->
                Log.e("PlayerActivity", "Error in MediaPlayer: what=$what, extra=$extra")
                true
            }
        } catch (e: Exception) {
            Log.e("PlayerActivity", "Error creating MediaPlayer", e)
        }

        playButton.setOnClickListener {
            try {
                mediaPlayer.start()
            } catch (e: Exception) {
                Log.e("PlayerActivity", "Error starting MediaPlayer", e)
            }
        }
        pauseButton.setOnClickListener { mediaPlayer.pause() }
        stopButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
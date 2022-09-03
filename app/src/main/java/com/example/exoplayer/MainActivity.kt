package com.example.exoplayer

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var sampleUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"

    var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaItem = MediaItem.fromUri(Uri.parse(sampleUrl))
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoplayer ->
                binding.exoPlayer.player = exoplayer
            }

        player!!.setMediaItem(mediaItem)

        player!!.prepare()
        player!!.play()
    }

    override fun onPause() {
        super.onPause()
        player!!.pause()
        player!!.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player!!.stop()
        player!!.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player!!.release()
    }
}
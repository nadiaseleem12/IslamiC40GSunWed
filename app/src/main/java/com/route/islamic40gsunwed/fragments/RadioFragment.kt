package com.route.islamic40gsunwed.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.islamic40gsunwed.OnPlayClick
import com.route.islamic40gsunwed.R
import com.route.islamic40gsunwed.RadioService
import com.route.islamic40gsunwed.databinding.FragmentRadioBinding
import com.route.islamic40gsunwed.model.Radio

class RadioFragment : Fragment() {

    lateinit var binding: FragmentRadioBinding
    var radioService: RadioService? = null
    lateinit var radiosList: List<Radio>
    var currentRadioIndex= 0
    var bound = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRadiosList()

        initViews()
        onPlayClick()
        onPreviousClick()
        onNextClick()
    }

    private fun onNextClick() {
        binding.nextIv.setOnClickListener {
            pauseMediaPlayer()
            binding.playIv.setImageResource(R.drawable.ic_play)

            currentRadioIndex = if (currentRadioIndex
                == radiosList.size-1) 0 else ++currentRadioIndex
            radioService?.initMediaPlayer(radiosList[currentRadioIndex].url,radiosList[currentRadioIndex].name)
            binding.tvRadio.text = radiosList[currentRadioIndex].name
        }
    }

    private fun onPreviousClick() {
        binding.previousIv.setOnClickListener {
            pauseMediaPlayer()
            binding.playIv.setImageResource(R.drawable.ic_play)

            currentRadioIndex = if (currentRadioIndex==0) radiosList.size - 1 else --currentRadioIndex
            radioService?.initMediaPlayer(radiosList[currentRadioIndex].url,radiosList[currentRadioIndex].name)
            binding.tvRadio.text = radiosList[currentRadioIndex].name
        }
    }

    private fun onPlayClick() {
        binding.playIv.setOnClickListener {
            radioService?.let { radioService->
                if(!radioService.getIsPlaying()){
                    radioService.startMediaPlayer()
                    binding.playIv.setImageResource(R.drawable.ic_pause)
                }else{
                    pauseMediaPlayer()
                    binding.playIv.setImageResource(R.drawable.ic_play)
                }

            }
        }
    }

    private fun pauseMediaPlayer() {
        if (bound){
            radioService?.playPauseMediaPlayer()
        }
    }

    private fun initViews() {
        binding.tvRadio.text = radiosList[currentRadioIndex].name
        radioService?.initMediaPlayer(radiosList[currentRadioIndex].url,radiosList[currentRadioIndex].name)
    }

    private fun getRadiosList() {
        radiosList = listOf(
            Radio(
                "إذاعة إبراهيم الأخضر",
                "https://backup.qurango.net/radio/ibrahim_alakdar"
            ),
            Radio("إذاعة القارئ ياسين", "https://backup.qurango.net/radio/alqaria_yassen"),
            Radio("إذاعة أحمد الطرابلسي", "https://backup.qurango.net/radio/ahmed_altrabulsi")
        )
    }

    override fun onStart() {
        super.onStart()
        bindService()
        startService()
    }

    private fun startService() {
        val intent = Intent(requireActivity(), RadioService::class.java)
        requireActivity().startService(intent)
    }

    private fun bindService() {
        val intent = Intent(requireContext(), RadioService::class.java)
        requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as RadioService.MyBinder
            radioService = binder.getService()
            bound = true
            radioService?.onPlayClick = OnPlayClick { isPlayed ->
                if(isPlayed){
                    binding.playIv.setImageResource(R.drawable.ic_pause)
                }else{
                    binding.playIv.setImageResource(R.drawable.ic_play)
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            radioService = null
            bound = false
        }

    }

    override fun onStop() {
        super.onStop()
        requireActivity().unbindService(connection)
    }

}

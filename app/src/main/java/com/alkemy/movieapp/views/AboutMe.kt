package com.alkemy.movieapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import com.alkemy.movieapp.databinding.ActivityAboutMeBinding
import kotlinx.android.synthetic.main.activity_about_me.*

class AboutMe : AppCompatActivity() {
    private lateinit var binding: ActivityAboutMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutMeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView3.text = "Thanks for user my MovieApp! \n\n I'm a rookie programmer trying to increase my knowledge \n\n You can see my others proyects in: https://github.com/pedraNicolas/"
        Linkify.addLinks(textView3,Linkify.WEB_URLS)

    }
}
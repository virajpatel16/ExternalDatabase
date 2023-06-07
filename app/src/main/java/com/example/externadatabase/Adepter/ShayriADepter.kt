package com.example.externadatabase.Adepter

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.externadatabase.Shayrimodel
import com.example.externadatabase.databinding.ShayriItemBinding
import java.util.Locale


class ShayriADepter(list: ArrayList<Shayrimodel>) : Adapter<ShayriADepter.Shayriholder>() {

    var list = list
    lateinit var context: Context
    lateinit var tts: TextToSpeech


    class Shayriholder(itemView: ShayriItemBinding) : ViewHolder(itemView.root) {
        var binding = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Shayriholder {
        var binding = ShayriItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Shayriholder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Shayriholder, position: Int) {

        fun showMessage(msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        holder.binding.apply {
            list.get(position).apply {
                txtshayri.text = Shayri
            }




            audio.setOnClickListener {
                fun onInit(status: Int) {
                    if (status == TextToSpeech.SUCCESS) {
                        val result = tts!!.setLanguage(Locale.US)

                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            showMessage("This Language is not supported")
                        } else {

                        }
                    } else {

                    }
                }

            }
        }


    }
}





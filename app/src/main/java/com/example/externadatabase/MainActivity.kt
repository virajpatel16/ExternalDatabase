package com.example.externadatabase

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.externadatabase.Adepter.ShayriADepter
import com.example.externadatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var data=ExternalDB(this)

   var list=data.retrivedata()

        binding.rcvShayrilist.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.rcvShayrilist.adapter=ShayriADepter(list)



        for (l in list){
            Log.e(TAG,"success fully add "+l.Shayri)

        }

    }
}
package com.dicoding.kotlinacademy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.list)
        initData()

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = RecylerViewAdapter(this, items)
    }

    fun initData(){
        val name = resources.getStringArray(R.array.android_name)
        val image = resources.obtainTypedArray(R.array.android_image)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i],
                    image.getResourceId(i, 0)))
        }

        //Recycle the typed array
        image.recycle()
    }
}

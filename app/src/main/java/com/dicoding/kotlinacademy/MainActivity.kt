package com.dicoding.kotlinacademy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.dicoding.kotlinacademy.R.array.club_image
import com.dicoding.kotlinacademy.R.array.club_name
import com.dicoding.kotlinacademy.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        initData()

        club_list.layoutManager = LinearLayoutManager(this)
        club_list.adapter = RecyclerViewAdapter(this, items){
            val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun initData(){
        val name = resources.getStringArray(club_name)
        val image = resources.obtainTypedArray(club_image)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i],
                    image.getResourceId(i, 0)))
        }

        //Recycle the typed array
        image.recycle()
    }
}

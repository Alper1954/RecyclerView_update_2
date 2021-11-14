package com.example.recyclerview_update_2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var numItem:Int = 0

    private val _items: MutableLiveData<MutableList<String>> = MutableLiveData()
    val items: LiveData<MutableList<String>>
        get() = _items

    init{
        _items.value = MutableList(10) { "item$it" }
    }

    fun remove(pos:Int){
        val newList = mutableListOf<String>()
        _items.value?.let { newList.addAll(it) }
        newList.removeAt(pos)
        _items.value = newList
    }

    fun addItem() {
        val newList = mutableListOf<String>()
        _items.value?.let { newList.addAll(it) }
        newList.add("newItem$numItem")
        _items.value = newList
        numItem++
    }
}

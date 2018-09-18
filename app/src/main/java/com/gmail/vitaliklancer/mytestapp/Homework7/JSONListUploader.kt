package com.gmail.vitaliklancer.mytestapp.Homework7

import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class JSONListUploader(private val urlAdress: String) {

    @Throws(IOException::class)
    fun execute(): StudentGroup {
        val url = URL(urlAdress)
        val urlCon = url.openConnection()
        val inputStream = urlCon.getInputStream()
        val gson = GsonBuilder().create()
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        return gson.fromJson(bufferedReader, StudentGroup::class.java)
    }
}
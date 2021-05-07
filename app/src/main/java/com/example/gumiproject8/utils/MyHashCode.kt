package com.example.gumiproject8.utils

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest
import java.security.Timestamp

object MyHashCode {
    fun generate(timestamp: Long, privateApiKey: String, publicApiKey: String): String =
        md5("$timestamp$privateApiKey$publicApiKey")

    private fun md5(str: String): String {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(str.toByteArray())
        val messageDigest = digest.digest()
        val bigInt = BigInteger(1, messageDigest)
        var hashText = bigInt.toString(16)
        while (hashText.length < 32) {
            hashText = "0$hashText"
        }
        Log.d("HashCode", hashText)
        return hashText
    }


}
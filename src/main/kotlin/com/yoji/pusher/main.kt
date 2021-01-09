package com.yoji.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main(){
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(dbUrl)
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
          "postAuthor": "Netology",
          "postAuthorId": 1,
          "postId": 2,
          "postContent": "Поговорим о важнейшей детали экшена Майкла Бэя, которую никто не видит. Буквально. Ведь это пауза в монтаже звука. А тесная работа с саунддизайнерами навсегда изменила подход Бэя к режиссуре и финальной сборке его фильмов. Что стало для режиссёра как благословением, так и проклятием."
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}
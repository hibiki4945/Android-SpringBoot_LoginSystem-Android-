package com.example.demo_06.network.res

import android.provider.ContactsContract.CommonDataKinds.Nickname

//data class UserLoginRes (
//    val user: Int,
//    val account: String,
//    val password: String,
//    val nickname: String,
//)

data class UserLoginRes (
    val account: String,
    val password: String,
    val name: String,
)
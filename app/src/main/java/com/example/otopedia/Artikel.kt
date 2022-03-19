package com.example.otopedia

data class Artikel(var id: String, var judul : String, var detail: String) {

    constructor() : this("", "", "") {

    }
}
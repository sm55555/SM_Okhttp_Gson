package com.example.osangmin.sm_okhttp

data class PapagoDTO(var message : Message? = null){
    data class Message(var result : Result? = null){
        data class Result(var translatedText : String? = null)
    }

}

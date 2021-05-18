package com.example.samplemoviesproject.model

data class BaseResponseModel<T>(
        var page: Int? = 0,
        var results: T? = null,
)
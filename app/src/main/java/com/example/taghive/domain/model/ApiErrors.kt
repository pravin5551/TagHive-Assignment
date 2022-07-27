package com.example.taghive.domain.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Errors(
        @JsonProperty("other")
        val other: List<String>? = arrayListOf(),
        @JsonProperty("mobile")
        val mobile: List<String>? = arrayListOf(),
        @JsonProperty("email")
        val email: List<String>? = arrayListOf()
)

data class ApiErrors(
        @JsonProperty("error")
        val error: String?,
        @JsonProperty("errors")
        var errors: Errors? = Errors(),
        @JsonProperty("success")
        var success: Boolean? = false
)

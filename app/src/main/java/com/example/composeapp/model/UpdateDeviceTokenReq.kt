package com.example.composeapp.model


data class UpdateDeviceTokenReq(
    var device_token: String,
    var device_type: String,
    var time_offset: String,
    var device_make_model: String,
    var device_os_version: String
)

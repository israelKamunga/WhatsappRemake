package com.isy_code.whatsappremake.Utils

sealed class Screen(
    public var screen: String
){
    object Home:Screen(screen = "Home")
    object ChatScreen:Screen(screen = "Chat")
}

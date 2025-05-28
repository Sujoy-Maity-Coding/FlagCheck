package com.sujoy.flagcheck.Navigation

import kotlinx.serialization.Serializable

sealed class Routes{
    @Serializable
    object Home : Routes()

    @Serializable
    object Quiz : Routes()

    @Serializable
    object Result : Routes()
}
package com.delacrixmorgan.kingscup.nav

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Start : Routes()

    @Serializable
    data object Support : Routes()

    @Serializable
    data object AppInfo : Routes()

    @Serializable
    data object StyleGuide : Routes()

    @Serializable
    data object Setup : Routes()

    @Serializable
    data object Rules : Routes()

    @Serializable
    data object Loading : Routes()

    @Serializable
    data object Board : Routes()

    @Serializable
    data object Card : Routes()
}
package com.delacrixmorgan.kingscup.data.model

enum class ThemePreference {
    System,
    Light,
    Dark;

    companion object {
        val Default = System
    }

    val label: String
        get() = when (this) {
            System -> "System"
            Light -> "Light"
            Dark -> "Dark"
        }
}
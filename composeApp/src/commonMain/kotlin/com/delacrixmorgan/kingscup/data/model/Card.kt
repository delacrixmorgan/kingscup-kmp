package com.delacrixmorgan.kingscup.data.model

import org.jetbrains.compose.resources.StringResource

abstract class Card(
    val id: String,
    val emoji: StringResource,
    val label: StringResource,
    val description: StringResource,
    val info: StringResource
)
package com.delacrixmorgan.kingscup.data.card.model

import org.jetbrains.compose.resources.StringResource

abstract class Rule(
    val id: String,
    val emoji: StringResource,
    val label: StringResource,
    val description: StringResource,
    val info: StringResource
)
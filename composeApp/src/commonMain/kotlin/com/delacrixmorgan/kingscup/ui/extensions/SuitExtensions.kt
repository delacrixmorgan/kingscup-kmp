package com.delacrixmorgan.kingscup.ui.extensions

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import com.delacrixmorgan.kingscup.data.card.model.Card

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
fun Card.SuitType?.getMaterialShape(): Shape = when (this) {
    Card.SuitType.Spade -> MaterialShapes.Arrow
    Card.SuitType.Heart -> MaterialShapes.Heart
    Card.SuitType.Club -> MaterialShapes.Clover4Leaf
    Card.SuitType.Diamond -> MaterialShapes.Diamond
    Card.SuitType.Joker -> MaterialShapes.SoftBurst
    else -> MaterialShapes.Circle
}.toShape()
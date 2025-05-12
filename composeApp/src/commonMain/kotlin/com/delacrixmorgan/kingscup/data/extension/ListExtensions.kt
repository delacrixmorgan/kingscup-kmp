package com.delacrixmorgan.kingscup.data.extension

fun <T> MutableList<T>.popOrNull(index: Int): T? =
    if (isNotEmpty()) removeAt(index) else null

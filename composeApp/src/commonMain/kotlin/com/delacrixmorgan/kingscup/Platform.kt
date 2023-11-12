package com.delacrixmorgan.kingscup

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
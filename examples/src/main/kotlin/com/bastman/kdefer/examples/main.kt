package com.bastman.kdefer.examples

import com.bastman.kdefer.Defer
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    val defer = Defer()

    defer.add { println("shudown action 1") }
    defer.add { println("shudown action 2") }
    defer.addGraceful {
        println("shudown action 3 with exception")

        throw RuntimeException("some error")
    }
    defer.add { println("shudown action 4") }

    Runtime.getRuntime().addShutdownHook(thread(start = false) {
        println("shutdown hook")
        defer.close()
    })


}

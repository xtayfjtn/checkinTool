package com.izhangqian.checkintool.actorsdk

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ActorExcutor constructor() {
    var threadPoolExecutor = ThreadPoolExecutor(1, 5, 1, TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>(100))
    init {
        instance = this
    }
    fun addTask(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }
    companion object {
        var instance : ActorExcutor? = null
    }
}
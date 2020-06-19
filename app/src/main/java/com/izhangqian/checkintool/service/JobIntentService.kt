package com.izhangqian.checkintool.service

import android.app.IntentService
import android.content.Context
import android.content.Intent


/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions and extra parameters.
 */
class JobIntentService : IntentService("JobIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val action = intent.action
            if (action == START_CLICK_ACTION) {
//                var clickActor = ClickActor()
//                ClickActor.getInstance().performClick(this)
            }
        }
    }

    companion object {
        val START_CLICK_ACTION = "com.izhangqian.com.CLICK"

        fun startClick(context : Context) {
            var intent = Intent(context, JobIntentService::class.java)
            intent.action = START_CLICK_ACTION;
            context.startService(intent)
        }
    }
}

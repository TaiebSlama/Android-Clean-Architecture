package com.slama.cleanarchitecture.helper.shared

/**
 * Created by Slama Taieb (slama.taieb.contact@gmail.com)
 * Date : 10/1/2022
 * []
 */

class Constant {

    class Global {
        companion object {
            const val NAVIGATION_USE_CASE_KEY = "useCase"
        }
    }

    class AndroidHelper {
        companion object {
            // maximum value of intent called from activity
            const val MAX_CODE_INTENT_FOR_ACTIVITY = 0X100
        }
    }
}
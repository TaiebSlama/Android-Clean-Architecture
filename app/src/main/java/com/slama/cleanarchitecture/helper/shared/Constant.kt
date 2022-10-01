package com.slama.cleanarchitecture.helper.shared

/**
 * Created by Slama Taieb (slama.taieb.contact@gmail.com)
 * Date : 10/1/2022
 * []
 */

class Constant {

    class Global {
        companion object {
            const val STRING_EMPTY = ""
            const val BLANK_STRING = "********"
            const val ZERO = "0.0"
            const val ZERO_WITHOUT_COMMA = "0"
            const val ONE = "1"
            const val NO_VALUE = "----"
            const val NAVIGATION_USE_CASE_KEY = "useCase"
            const val TVA = 0.19
            const val DATE_FORMAT = "yyyy/M/dd hh:mm:ss"
            const val PERIOD_DAILY_WEEKLY_REPORT_FORMAT = "yyyy/M/dd"
            const val PERIOD_MONTHLY_REPORT_FORMAT = "M/yyyy"
            const val DATE_FORMAT_CALENDAR = "EEEE, d MMMM yyyy"

        }
    }

    class AndroidHelper {
        companion object {
            // maximum value of intent called from activity
            const val MAX_CODE_INTENT_FOR_ACTIVITY = 0X100
        }
    }
}
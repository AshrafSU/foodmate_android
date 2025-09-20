package com.food.foodmate.utility

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.Calendar


object Helper {

    fun getDate(createdAt: String): String {
        return try {
            val odt = OffsetDateTime.parse(createdAt) // ThreeTenBP import
            val localTime = odt.atZoneSameInstant(ZoneId.systemDefault())
            val formatter = DateTimeFormatter.ofPattern("MMMM d, HH:mm")
            localTime.format(formatter)
        } catch (e: Exception) {
            createdAt
        }
    }

    fun getGreetingBasedOnTime(): String {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        return when (currentHour) {
            in 5..11 -> "Good Morning! ☀️"
            in 12..14 -> "Good Noon! 🌞"
            in 15..17 -> "Good Afternoon! ☕"
            in 18..20 -> "Good Evening! 🌇"
            else -> "Good Night! 🌙"
        }
    }


}

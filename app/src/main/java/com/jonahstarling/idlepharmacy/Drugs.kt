package com.jonahstarling.idlepharmacy

import android.content.SharedPreferences

class Drugs {
    companion object {
        fun loadDrugUnlocks(preferences: SharedPreferences) {
            allDrugs[0].unlocked = preferences.getBoolean("drugOne", true)
            allDrugs[1].unlocked = preferences.getBoolean("drugTwo", false)
            allDrugs[2].unlocked = preferences.getBoolean("drugThree", false)
            allDrugs[3].unlocked = preferences.getBoolean("drugFour", false)
            allDrugs[4].unlocked = preferences.getBoolean("drugFive", false)
            allDrugs[5].unlocked = preferences.getBoolean("drugSix", false)
            allDrugs[6].unlocked = preferences.getBoolean("drugSeven", false)
            allDrugs[7].unlocked = preferences.getBoolean("drugEight", false)
            allDrugs[8].unlocked = preferences.getBoolean("drugNine", false)
            allDrugs[9].unlocked = preferences.getBoolean("drugTen", false)
            allDrugs[10].unlocked = preferences.getBoolean("drugEleven", false)
            allDrugs[11].unlocked = preferences.getBoolean("drugTwelve", false)
        }

        fun saveDrugUnlocks(editor: SharedPreferences.Editor) {
            editor.putBoolean("drugOne", allDrugs[0].unlocked)
            editor.putBoolean("drugTwo", allDrugs[1].unlocked)
            editor.putBoolean("drugThree", allDrugs[2].unlocked)
            editor.putBoolean("drugFour", allDrugs[3].unlocked)
            editor.putBoolean("drugFive", allDrugs[4].unlocked)
            editor.putBoolean("drugSix", allDrugs[5].unlocked)
            editor.putBoolean("drugSeven", allDrugs[6].unlocked)
            editor.putBoolean("drugEight", allDrugs[7].unlocked)
            editor.putBoolean("drugNine", allDrugs[8].unlocked)
            editor.putBoolean("drugTen", allDrugs[9].unlocked)
            editor.putBoolean("drugEleven", allDrugs[10].unlocked)
            editor.putBoolean("drugTwelve", allDrugs[11].unlocked)
        }

        val allDrugs = listOf(
            Drug("Sugar", R.drawable.bottle_one,1, 1, 0, true),
            Drug("Caffeine", R.drawable.bottle_two,5, 3, 10),
            Drug("Good Times", R.drawable.bottle_three, 12, 7, 100),
            Drug("Chill Pills", R.drawable.bottle_four,20, 10, 500),
            Drug("Fake Benadryl", R.drawable.bottle_five,50, 14, 1000),
            Drug("Twice A Day", R.drawable.bottle_six,100, 28, 10000),
            Drug("Placebo", R.drawable.bottle_seven,150, 30, 25000),
            Drug("Gummy Vitamins", R.drawable.bottle_eight,300, 6, 50000),
            Drug("Not Bath Salts", R.drawable.bottle_nine,500, 90, 100000),
            Drug("Smarties", R.drawable.bottle_ten,750, 120, 500000),
            Drug("Pixie Dust", R.drawable.bottle_eleven,1000, 300, 1000000),
            Drug("Something Really Bad", R.drawable.bottle_twelve,10000, 500, 1000000000)
        )
    }
}

class Drug(val name: String,
           val drugImageResource: Int,
           val price: Int,
           val quantity: Int,
           val cost: Int,
           var unlocked: Boolean = false)

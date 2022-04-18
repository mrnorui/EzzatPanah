package academy.nouri.ezatpanahcourse

import academy.nouri.ezatpanahcourse.room_database.db.NoteDatabase
import academy.nouri.ezatpanahcourse.utils.NOTE_DATABASE
import android.app.Application
import nouri.`in`.goodprefslib.GoodPrefs
import android.R
import androidx.room.Room

import io.github.inflationx.calligraphy3.CalligraphyConfig

import io.github.inflationx.calligraphy3.CalligraphyInterceptor

import io.github.inflationx.viewpump.ViewPump

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //GoodPrefs
        GoodPrefs.init(applicationContext)
        //Calligraphy
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/iransans.ttf")
                            .build()
                    )
                )
                .build()
        )
    }
}
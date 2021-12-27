package ru.gavarent.lighthouse

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.gavarent.lighthouse.db.AppDatabase
import ru.gavarent.lighthouse.screen.MapViewModel

class LighthouseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE) //https://github.com/InsertKoinIO/koin/issues/1188
            androidContext(this@LighthouseApplication)
            modules(buildAppModule())
        }
    }

    private fun buildAppModule(): Module {
        return module {
            // single<HelloRepository> { HelloRepositoryImpl() }

            //  factory { MySimplePresenter(get()) }
            single {
                Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "prepopulate.db"
                )
                         .createFromAsset("database/prepopulate.db")
                    .build()
            }
            viewModel { MapViewModel(get()) }
        }
    }
}
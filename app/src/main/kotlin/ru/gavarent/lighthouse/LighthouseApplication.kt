package ru.gavarent.lighthouse

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.gavarent.lighthouse.db.SeaMarkTable
import ru.gavarent.lighthouse.screen.MapViewModel

class LighthouseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LighthouseApplication)
            modules(buildAppModule())
        }
    }

    private fun buildAppModule(): Module {
        return module {
            // single<HelloRepository> { HelloRepositoryImpl() }

            //  factory { MySimplePresenter(get()) }
            single { SeaMarkTable() }
            viewModel { MapViewModel(get()) }
        }
    }
}
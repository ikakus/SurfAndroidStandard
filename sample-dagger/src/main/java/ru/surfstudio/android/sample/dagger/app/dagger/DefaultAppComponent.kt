package ru.surfstudio.android.sample.dagger.app.dagger

import android.content.Context
import android.content.SharedPreferences
import dagger.Component
import ru.surfstudio.android.connection.ConnectionProvider
import ru.surfstudio.android.core.app.ActiveActivityHolder
import ru.surfstudio.android.core.app.StringsProvider
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.rx.extension.scheduler.SchedulersProvider
import ru.surfstudio.android.shared.pref.NO_BACKUP_SHARED_PREF
import javax.inject.Named

@PerApplication
@Component(modules = [
    DefaultAppModule::class,
    DefaultSharedPrefModule::class
])
interface DefaultAppComponent {
    fun context(): Context
    fun activeActivityHolder(): ActiveActivityHolder
    fun connectionProvider(): ConnectionProvider
    fun schedulerProvider(): SchedulersProvider
    fun stringsProvider(): StringsProvider
    @Named(NO_BACKUP_SHARED_PREF) fun sharedPreferences(): SharedPreferences
}

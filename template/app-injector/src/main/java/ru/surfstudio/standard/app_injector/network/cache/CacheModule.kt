package ru.surfstudio.standard.app_injector.network.cache

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.filestorage.utils.AppDirectoriesProvider
import ru.surfstudio.android.network.BaseUrl
import ru.surfstudio.android.network.cache.SimpleCacheFactory
import ru.surfstudio.android.network.cache.SimpleCacheInterceptor
import ru.surfstudio.android.network.cache.SimpleCacheUrlConnector
import ru.surfstudio.standard.i_network.cache.SimpleCacheInfoStorage

/**
 * Dagger-модуль для удовлетворения зависимостей классов, использующихся для кэширования
 */
@Module
class CacheModule {

    @Provides
    @PerApplication
    internal fun provideSimpleCacheInterceptor(
            simpleCacheFactory: SimpleCacheFactory,
            simpleCacheUrlConnector: SimpleCacheUrlConnector
    ): SimpleCacheInterceptor {
        return SimpleCacheInterceptor(simpleCacheFactory, simpleCacheUrlConnector)
    }

    @Provides
    @PerApplication
    internal fun provideSimpleCacheFactory(
            context: Context,
            cacheUrlConnector: SimpleCacheUrlConnector
    ): SimpleCacheFactory {
        return SimpleCacheFactory(AppDirectoriesProvider.provideBackupStorageDir(context), cacheUrlConnector)
    }

    @Provides
    @PerApplication
    internal fun providesSimpleCacheConnector(
            baseUrl: BaseUrl,
            simpleCacheInfoStorage: SimpleCacheInfoStorage
    ): SimpleCacheUrlConnector {
        return SimpleCacheUrlConnector(baseUrl, simpleCacheInfoStorage.simpleCaches)
    }
}
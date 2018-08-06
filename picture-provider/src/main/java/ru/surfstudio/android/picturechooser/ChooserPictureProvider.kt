package ru.surfstudio.android.picturechooser

import android.app.Activity
import android.content.Context
import android.content.Intent
import io.reactivex.Observable
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.core.ui.navigation.activity.route.ActivityWithResultRoute

/**
 * Позволяет получить одно или несколько изображений из любого места на устройстве
 */
class ChooserPictureProvider(private val activityNavigator: ActivityNavigator,
                             private val activity: Activity) {

    //region Функции для выбора одного изображения
    fun createChooserForSingleImage(message: String): Observable<String> {
        val route = ChooserSingleImageRoute(message)
        val result = observeSingleScreenResult(activityNavigator, route)
        activityNavigator.startForResult(route)
        return result
    }

    fun createChooserForSingleImageUri(message: String): Observable<String> {
        val route = ChooserSingleImageUriRoute(message)
        val result = observeSingleScreenResult(activityNavigator, route)
        activityNavigator.startForResult(route)
        return result
    }

    fun createChooserForSingleImageUriWrapper(message: String): Observable<UriWrapper> {
        val route = ChooserSingleImageUriWrapperRoute(message)
        val result = observeSingleScreenResult(activityNavigator, route)
        activityNavigator.startForResult(route)
        return result
    }
    //endregion

    //region Функции для выбора нескольких изображений
    fun createChooserForMultipleImage(message: String): Observable<List<String>> {
        val route = ChooserMultipleImageRoute(message)
        val result = observeMultipleScreenResult(activityNavigator, route)
        activityNavigator.startForResult(route)
        return result
    }

    fun createChooserForMultipleImageUri(message: String): Observable<List<String>> {
        val route = ChooserMultipleImageUriRoute(message)
        val result = observeMultipleScreenResult(activityNavigator, route)
        activityNavigator.startForResult(route)
        return result
    }

    fun createChooserForMultipleImageUriWrapper(message: String): Observable<List<UriWrapper>> {
        val route = ChooserMultipleImageUriWrapperRoute(message)
        val result = observeMultipleScreenResult(activityNavigator, route)
        activityNavigator.startForResult(route)
        return result
    }
    //endregion

    //region Роутеры для выбора одного изображения
    /**
     * Роутер, возвращащий путь к изображению
     */
    private inner class ChooserSingleImageRoute(private val chooserMessage: String) : ActivityWithResultRoute<String>() {

        override fun prepareIntent(context: Context?) = createChooserIntentForSingleImage(chooserMessage)

        override fun parseResultIntent(intent: Intent?): String? {
            return parseSingleResultIntent(intent) { it.getRealPath(activity) }
        }
    }

    /**
     * Роутер, возвращающий Uri изображения, преобразованный в String
     */
    private inner class ChooserSingleImageUriRoute(private val chooserMessage: String) : ActivityWithResultRoute<String>() {

        override fun prepareIntent(context: Context?) = createChooserIntentForSingleImage(chooserMessage)

        override fun parseResultIntent(intent: Intent?): String? {
            return parseSingleResultIntent(intent) { it.toString() }
        }
    }

    /**
     * Роутер, возвращающий класс-обертку над Uri изображения
     */
    private inner class ChooserSingleImageUriWrapperRoute(private val chooserMessage: String) : ActivityWithResultRoute<UriWrapper>() {

        override fun prepareIntent(context: Context?) = createChooserIntentForSingleImage(chooserMessage)

        override fun parseResultIntent(intent: Intent?): UriWrapper? {
            return parseSingleResultIntent(intent) { UriWrapper(it) }
        }
    }
    //endregion

    //region Роутеры для выбора нескольких изображений
    /**
     * Роутер, возвращающий список путей к выбранным изображениям
     */
    private inner class ChooserMultipleImageRoute(private val chooserMessage: String) : ActivityWithResultRoute<ArrayList<String>>() {

        override fun prepareIntent(context: Context?) = createChooserIntentForMultipleImage(chooserMessage)

        override fun parseResultIntent(intent: Intent?): ArrayList<String>? {
            return parseMultipleResultIntent(intent) { it.getRealPath(activity) }
        }
    }

    /**
     * Роутер, возвращающий список Uri выбранных изображений, преобразованных в String
     */
    private inner class ChooserMultipleImageUriRoute(private val chooserMessage: String) : ActivityWithResultRoute<ArrayList<String>>() {

        override fun prepareIntent(context: Context?) = createChooserIntentForMultipleImage(chooserMessage)

        override fun parseResultIntent(intent: Intent?): ArrayList<String>? {
            return parseMultipleResultIntent(intent) { it.toString() }
        }
    }

    /**
     * Роутер, возвращающий список элементов типа класса-обертки над Uri выбранных изображений
     */
    private inner class ChooserMultipleImageUriWrapperRoute(private val chooserMessage: String) : ActivityWithResultRoute<ArrayList<UriWrapper>>() {

        override fun prepareIntent(context: Context?) = createChooserIntentForMultipleImage(chooserMessage)

        override fun parseResultIntent(intent: Intent?): ArrayList<UriWrapper>? {
            return parseMultipleResultIntent(intent) { UriWrapper(it) }
        }
    }
    //endregion

    //region Вспомогательные функции для роутеров
    private fun createChooserIntentForSingleImage(message: String): Intent {
        return createChooser(getIntentForSingleImageFromGallery(), message)
    }

    private fun createChooserIntentForMultipleImage(message: String) : Intent {
        return createChooser(getIntentForMultipleImageFromGallery(), message)
    }
    //endregion
}
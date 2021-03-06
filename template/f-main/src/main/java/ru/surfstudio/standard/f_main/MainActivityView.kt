package ru.surfstudio.standard.f_main

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.LayoutRes
import ru.surfstudio.standard.base_ui.component.provider.ComponentProvider
import ru.surfstudio.android.core.mvp.activity.BaseRenderableActivityView
import ru.surfstudio.android.core.mvp.presenter.CorePresenter
import ru.surfstudio.android.core.ui.FragmentContainer
import ru.surfstudio.android.template.f_main.R
import javax.inject.Inject

/**
 * Вью главного экрана
 */
class MainActivityView : BaseRenderableActivityView<MainScreenModel>(), FragmentContainer {

    @Inject
    lateinit var presenter: MainPresenter

    override fun getPresenters(): Array<CorePresenter<*>> = arrayOf(presenter)

    override fun createConfigurator() = ComponentProvider.createActivityScreenConfigurator(intent, this::class)

    @LayoutRes
    override fun getContentView(): Int = R.layout.activity_main

    override fun getContentContainerViewId() = R.id.container

    override fun onCreate(
            savedInstanceState: Bundle?,
            persistentState: PersistableBundle?,
            viewRecreated: Boolean
    ) {
        initViews()
        initListeners()
    }

    override fun getScreenName(): String = "MainActivityView"

    override fun renderInternal(screenModel: MainScreenModel) {
    }

    private fun initViews() {
    }

    private fun initListeners() {
    }
}

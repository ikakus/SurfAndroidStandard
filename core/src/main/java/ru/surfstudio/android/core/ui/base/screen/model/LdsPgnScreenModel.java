package ru.surfstudio.android.core.ui.base.screen.model;


import ru.surfstudio.android.core.ui.base.screen.model.state.LoadState;
import ru.surfstudio.android.core.ui.base.screen.model.state.PaginationState;

/**
 * модель экрана с поддержкой
 * {@link LoadState}
 * {@link PaginationState}
 * <p>
 * работает в связке c BaseLdsSwrPgn...View
 * В случае изменения LoadState, SwipeRefreshState устанавливается в SwipeRefreshState.HIDE
 * <p>
 * также см {@link ScreenModel}
 */
public class LdsPgnScreenModel extends LdsScreenModel {
    private PaginationState paginationState = PaginationState.COMPLETE;

    public PaginationState getPaginationState() {
        return paginationState;
    }

    public void setPaginationState(PaginationState paginationState) {
        this.paginationState = paginationState;
    }

    public void setNormalPaginationState(boolean canLoadMore) {
        setPaginationState(canLoadMore ? PaginationState.READY : PaginationState.COMPLETE);
    }

    public void setErrorPaginationState() {
        setPaginationState(PaginationState.ERROR);
    }
}
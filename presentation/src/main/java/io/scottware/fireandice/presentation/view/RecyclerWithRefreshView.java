package io.scottware.fireandice.presentation.view;

import android.support.v7.widget.RecyclerView;

public interface RecyclerWithRefreshView extends IView {
    void setupSwipeToRefresh(Runnable onRefresh);

    void hideSwipeToRefreshProgress();

    void setupRecyclerView(RecyclerView.Adapter adapter);

    void addOnScrollListener(OnScrolled onScrolled);

    interface OnScrolled {
        void onScrolled(int visibleItemCount, int totalItemCount, int firstVisibleItemPosition);
    }
}

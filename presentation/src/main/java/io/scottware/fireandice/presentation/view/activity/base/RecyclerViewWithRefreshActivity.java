package io.scottware.fireandice.presentation.view.activity.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.scottware.fireandice.presentation.R;
import io.scottware.fireandice.presentation.view.RecyclerWithRefreshView;

public abstract class RecyclerViewWithRefreshActivity extends BasePresenterActivity<RecyclerWithRefreshView> implements RecyclerWithRefreshView {

    private static final int gridSpan = 2;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        getPresenter().setView(this);
        getPresenter().create();
    }

    @Override
    public void setupSwipeToRefresh(final Runnable onRefresh) {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefresh.run();
            }
        });
    }

    @Override
    public void hideSwipeToRefreshProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setupRecyclerView(RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridSpan));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    public void addOnScrollListener(final OnScrolled onScrolled) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                onScrolled.onScrolled(visibleItemCount, totalItemCount, firstVisibleItemPosition);
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
    }

    @Override
    public String getInitialData(String key) {
        return getIntent().getStringExtra(key);
    }

}

package com.lifesum.lifesummob.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * A utility to perform general {@link RecyclerView} operations.
 */
public class RecyclerViewManager {

    /**
     * Set the properties to make an horizontal {@link RecyclerView}
     *
     * @param recyclerView    the  recyclerView that should be operated
     * @param adapter         the adapter to set, or null to set no adapter.
     * @param hasFixedSize    true if adapter changes cannot affect the size of the RecyclerView
     * @param nestedScrolling used if the parent is scrollable
     */
    public static void setHorizontalRecyclerView(
            RecyclerView recyclerView, RecyclerView.Adapter<?> adapter,
            boolean hasFixedSize, boolean nestedScrolling) {

        LinearLayoutManager layoutManager = new LinearLayoutManager
                (recyclerView.getContext());

        setRecyclerView(recyclerView, adapter, hasFixedSize,
                nestedScrolling, layoutManager);
    }

    private static void setRecyclerView(
            RecyclerView recyclerView, RecyclerView.Adapter<?> adapter,
            boolean hasFixedSize, boolean nestedScrolling,
            LinearLayoutManager layoutManager) {

        recyclerView.setHasFixedSize(hasFixedSize);
        recyclerView.setNestedScrollingEnabled(nestedScrolling);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

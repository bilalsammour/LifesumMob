package com.lifesum.lifesummob.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lifesum.lifesummob.R;
import com.lifesum.lifesummob.models.loaders.CategoriesLoader;
import com.lifesum.lifesummob.util.RecyclerViewManager;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {

    private CategoriesLoader loader;

    public MainActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainActivityFragment.
     */
    @SuppressWarnings("unused")
    public static MainActivityFragment newInstance() {
        return new MainActivityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loader = new CategoriesLoader(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        loader.retrieve();
    }

    @Override
    protected void initViews(View rootView) {
        RecyclerView list = (RecyclerView)
                rootView.findViewById(R.id.list);
        RecyclerViewManager.setVerticalRecyclerView(list, loader.getAdapter(),
                true, false);
    }

    /**
     * Search for a query
     *
     * @param query to be searched
     */
    public void search(String query) {
        Log.i("search", query);

        loader.setQuery(query);
        loader.retrieve();
    }
}

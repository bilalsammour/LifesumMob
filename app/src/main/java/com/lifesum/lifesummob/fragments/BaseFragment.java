package com.lifesum.lifesummob.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * The top parent for all fragments
 */
public abstract class BaseFragment extends Fragment {

    protected abstract void initViews(View rootView);
}

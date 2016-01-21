package com.lifesum.lifesummob.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lifesum.lifesummob.R;
import com.lifesum.lifesummob.models.thin.FoodModel;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends BaseFragment {

    private static final String ARG_FOOD = "ARG_FOOD";

    private FoodModel foodModel;

    public DetailActivityFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param foodModel the food object to show detail
     * @return A new instance of fragment DetailActivityFragment.
     */
    public static DetailActivityFragment newInstance(FoodModel foodModel) {
        DetailActivityFragment fragment = new DetailActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_FOOD, foodModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        foodModel = (FoodModel) bundle.getSerializable(ARG_FOOD);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        initViews(rootView);

        return rootView;
    }

    @Override
    protected void initViews(View rootView) {
        TextView title = (TextView) rootView.findViewById(R.id.title);
        title.setText(foodModel.getTitle());

        TextView description = (TextView) rootView.findViewById(R.id.description);
        description.setText(foodModel.getCategory());

        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        TextView fat = (TextView) rootView.findViewById(R.id.fat);
        fat.setText(nf.format(foodModel.getFat()));

        TextView calories = (TextView) rootView.findViewById(R.id.calories);
        calories.setText(nf.format(foodModel.getCalories()));
    }
}

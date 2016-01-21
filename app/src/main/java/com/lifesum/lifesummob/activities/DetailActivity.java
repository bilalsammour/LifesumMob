package com.lifesum.lifesummob.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.lifesum.lifesummob.R;
import com.lifesum.lifesummob.fragments.DetailActivityFragment;
import com.lifesum.lifesummob.models.thin.FoodModel;

public class DetailActivity extends BaseActivity {

    private static final String ARG_FOOD = "ARG_FOOD";

    private FoodModel foodModel;

    /**
     * Use this factory method to create a new instance of {@link Intent}
     * related to this activity
     *
     * @param context   a Context of the application package implementing this class.
     * @param foodModel the food object to show detail
     * @return a new Intent instance
     */
    public static Intent getIntentInstance(Context context, FoodModel foodModel) {
        Intent intent = new Intent(context.getApplicationContext(), DetailActivity.class);
        intent.putExtra(ARG_FOOD, foodModel);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        FoodModel foodModel = (FoodModel) intent.getSerializableExtra(ARG_FOOD);

        if (foodModel != null) {
            DetailActivityFragment fragment = DetailActivityFragment.newInstance(foodModel);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment).commit();
        }
    }
}

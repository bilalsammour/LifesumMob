package com.lifesum.lifesummob.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lifesum.lifesummob.R;
import com.lifesum.lifesummob.models.thin.CategoryModel;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Categories adapter
 */
public class CategoriesAdapter extends BaseListAdapter
        <CategoriesAdapter.CategoriesViewHolder, CategoryModel> {

    /**
     * Create an instance
     *
     * @param activity the context as {@link Activity}
     */
    public CategoriesAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected void initItems() {
        setList(new ArrayList<CategoryModel>());
    }

    @Override
    protected CategoriesViewHolder onCreateRegularViewHolder
            (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_category,
                parent, false);

        return new CategoriesViewHolder(view);
    }

    @Override
    protected void onRegularBindViewHolder
            (CategoriesViewHolder holder, int position, CategoryModel item) {
        holder.getTitle().setText(item.getName());

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        holder.getDescription().setText(numberFormat.format(item.getSource()));
    }

    protected class CategoriesViewHolder extends BaseListAdapter.ItemBaseViewHolder {

        private TextView title;
        private TextView description;

        public CategoriesViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }
    }
}

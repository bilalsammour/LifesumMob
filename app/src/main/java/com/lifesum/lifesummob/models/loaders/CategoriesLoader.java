package com.lifesum.lifesummob.models.loaders;

import android.app.Activity;
import android.content.Context;

import com.lifesum.lifesummob.adapters.CategoriesAdapter;
import com.lifesum.lifesummob.models.services.CategoriesService;
import com.lifesum.lifesummob.models.thin.CategoriesObjectModel;

/**
 * The loader for the categories
 */
public class CategoriesLoader extends BaseAdapterLoader
        <CategoriesService, CategoriesObjectModel, CategoriesAdapter> {

    private String query = "";

    /**
     * Create an instance
     *
     * @param context a Context of the application package implementing
     *                this class.
     */
    public CategoriesLoader(Context context) {
        super(context, CategoriesService.class);
    }

    /**
     * Init the required adapter
     *
     * @param activity the context as activity
     */
    @Override
    protected void initAdapter(Activity activity) {
        setAdapter(new CategoriesAdapter(activity));
    }

    @Override
    protected void addItemsToAdapter(CategoriesObjectModel response) {
        if (response != null && response.getCategories() != null)
            getAdapter().addItems(response.getCategories());
        else
            getAdapter().refreshWithNoChanges();
    }

    /**
     * Define the retrofit method from the interface
     */
    @Override
    protected void defineMethod() {
        if (query != null)
            setCaller(getService().getCategories(query));
    }

    /**
     * Setter for query
     *
     * @param query to search
     */
    public void setQuery(String query) {
        this.query = query;
    }
}

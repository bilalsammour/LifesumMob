package com.lifesum.lifesummob.models.loaders;

import android.app.Activity;
import android.content.Context;

import com.lifesum.lifesummob.adapters.FoodAdapter;
import com.lifesum.lifesummob.models.services.CategoriesService;
import com.lifesum.lifesummob.models.thin.CategoriesObjectModel;
import com.lifesum.lifesummob.util.ObjectCache;

import java.io.IOException;

/**
 * The loader for the categories that gets the data
 * from the given API and fills the adapter
 */
public class CategoriesLoader extends BaseAdapterLoader
        <CategoriesService, CategoriesObjectModel, FoodAdapter> {

    private static final String KEY_CACHE = "KEY_CACHE";

    private Context context;
    private String query;

    /**
     * Create an instance
     *
     * @param context a Context of the application package implementing
     *                this class.
     */
    public CategoriesLoader(Context context) {
        super(context, CategoriesService.class);
        this.context = context;

        readCachedObject(context);
        getAdapter().refreshWithNoChanges();
    }

    private void readCachedObject(Context context) {
        try {
            CategoriesObjectModel cached = ObjectCache.readObject(context, KEY_CACHE);

            if (cached != null)
                onResponse(cached);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Init the required adapter
     *
     * @param activity the context as activity
     */
    @Override
    protected void initAdapter(Activity activity) {
        setAdapter(new FoodAdapter(activity));
    }

    @Override
    protected void onResponse(CategoriesObjectModel response) {
        super.onResponse(response);

        cacheObject(response);
    }

    private void cacheObject(CategoriesObjectModel response) {
        if (response != null) {
            try {
                ObjectCache.writeObject(context, KEY_CACHE, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void addItemsToAdapter(CategoriesObjectModel response) {
        if (response != null && response.getFood() != null)
            getAdapter().addItems(response.getFood());
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

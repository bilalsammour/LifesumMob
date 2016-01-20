package com.lifesum.lifesummob.models.loaders;

import android.app.Activity;
import android.content.Context;

import com.lifesum.lifesummob.adapters.BaseListAdapter;

/**
 * The base loader for any loader that used to retrieve data list
 * that will shown in an adapter
 *
 * @param <TService> the retrofit service type
 * @param <TReturn>  the type of return
 * @param <TAdapter> the type of the adapter
 */
public abstract class BaseAdapterLoader<TService, TReturn,
        TAdapter extends BaseListAdapter>
        extends LoaderRequestManager<TService, TReturn> {

    private TAdapter adapter;
    private boolean append;

    /**
     * Create an instance
     *
     * @param context         a Context of the application package implementing
     *                        this class.
     * @param classOfTService the component class that is to be used for the intent
     */
    public BaseAdapterLoader(Context context, Class<TService> classOfTService) {
        super(context, classOfTService);

        initAdapter((Activity) context);
    }

    @Override
    protected void onResponse(TReturn response) {
        super.onResponse(response);

        if (!isAppend())
            adapter.resetItems();

        addItemsToAdapter(response);
    }

    protected abstract void addItemsToAdapter(TReturn response);

    /**
     * Retrieve data and clear the old
     */
    public void retrieve() {
        setAppend(false);

        defineMethod();

        enqueue();
    }

    /**
     * Init the required adapter
     *
     * @param activity the context as activity
     */
    protected abstract void initAdapter(Activity activity);

    /**
     * Define the retrofit method from the interface
     */
    protected abstract void defineMethod();

    /**
     * Retrieves the previously set adapter or null if no adapter is set
     *
     * @return the previously set adapter
     */
    public TAdapter getAdapter() {
        return adapter;
    }

    /**
     * Set a new adapter to provide child views on demand.
     *
     * @param adapter the new adapter to set, or null to set no adapter.
     */
    public void setAdapter(TAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Retrieves the appending value.
     * {@code true} means that the new data will be appended to the old.
     * {@code false}  means that the old data will be cleared.
     *
     * @return the appending value
     */
    public boolean isAppend() {
        return append;
    }

    /**
     * set the appending value
     *
     * @param append the appending value
     *               {@code true} means that the new data will be appended to the old.
     *               {@code false}  means that the old data will be cleared.
     */
    public void setAppend(boolean append) {
        this.append = append;
    }
}

package com.lifesum.lifesummob.models.loaders;

import android.content.Context;

import com.lifesum.lifesummob.R;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A manager to handle all shared Retrofit processes
 *
 * @param <TService> the retrofit service type
 * @param <TReturn>  the type of return
 */
@SuppressWarnings("unused")
public class LoaderRequestManager<TService, TReturn> {

    private Class<TService> classOfTService;
    private String baseUrl;
    private TService service;
    private Call<TReturn> caller;
    private OnResponseReceived<TReturn> onResponseReceived;
    private Callback<TReturn> callback = new Callback<TReturn>() {
        @Override
        public void onResponse(Response<TReturn> response, Retrofit retrofit) {
            onLocalResponse(response.body());
        }

        @Override
        public void onFailure(Throwable t) {
            onLocalFailure(t);
        }
    };

    /**
     * Create an instance
     *
     * @param context         a Context of the application package implementing
     *                        this class.
     * @param classOfTService the component class that is to be used for the intent
     */
    public LoaderRequestManager(Context context, Class<TService> classOfTService) {
        this.classOfTService = classOfTService;

        init(context);
    }

    private void init(Context context) {
        this.baseUrl = context.getString(R.string.base_url);

        service = buildService();
    }

    private TService buildService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(classOfTService);
    }

    public TService getService() {
        return service;
    }

    protected void setCaller(Call<TReturn> caller) {
        this.caller = caller;
    }

    /**
     * Set the response callback
     *
     * @param onResponseReceived the response callback
     */
    public void setOnResponseReceived(OnResponseReceived<TReturn> onResponseReceived) {
        this.onResponseReceived = onResponseReceived;
    }

    /**
     * execute with callback
     */
    protected void enqueue() {
        this.caller.enqueue(callback);
    }

    /**
     * execute with return
     *
     * @return extracted data
     * @throws IOException
     */
    @SuppressWarnings("unused")
    protected Response<TReturn> execute() throws IOException {
        return this.caller.execute();
    }

    private void onLocalResponse(TReturn response) {
        onResponse(response);

        if (onResponseReceived != null)
            onResponseReceived.onResponse(response);
    }

    protected void onResponse(TReturn response) {
    }

    private void onLocalFailure(Throwable throwable) {
        onFailure(throwable);

        if (onResponseReceived != null)
            onResponseReceived.onFailure(throwable);
    }

    protected void onFailure(Throwable throwable) {
    }

    /**
     * The callback object
     *
     * @param <TReturn> the type of return
     */
    public interface OnResponseReceived<TReturn> {
        void onResponse(TReturn response);

        void onFailure(Throwable throwable);
    }
}

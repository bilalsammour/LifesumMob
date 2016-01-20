package com.lifesum.lifesummob.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lifesum.lifesummob.R;

import java.util.List;

/**
 * The base adapter for list to be used for {@link RecyclerView}
 *
 * @param <VH>     the ViewHolder
 * @param <TModel> the object type used in the {@link List}
 */
@SuppressWarnings("unused")
public abstract class BaseListAdapter
        <VH extends BaseListAdapter.ItemBaseViewHolder, TModel>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Activity activity;
    private List<TModel> list;
    private ItemsStates itemsStates = ItemsStates.LOADING;
    private boolean isLoadingMore;
    private OnItemClickListener onItemClickListener;

    /**
     * Create an instance
     * @param activity the context as {@link Activity}
     */
    public BaseListAdapter(Activity activity) {
        this.activity = activity;
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        switch (getItemsStates()) {
            case ITEMS:
                return getItemsCountWithFooter();

            case LOADING:
                return 1;

            case EMPTY:
                return 1;

            default:
                return getItemsCountWithFooter();
        }
    }

    private int getItemsCountWithFooter() {
        int realCount = list == null ? 0 : list.size();

        return isLoadingMore ? realCount + 1 : realCount;
    }

    /**
     * Return the view type of the item at <code>position</code> for the purposes
     * of view recycling.
     * <p/>
     * <p>The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * <code>position</code>. Type codes need not be contiguous.
     */
    @Override
    public int getItemViewType(int position) {
        switch (getItemsStates()) {
            case ITEMS:
                if (isLoadingMore && position == list.size())
                    return RowType.LOADING.ordinal();

                else
                    return RowType.ITEM.ordinal();

            case LOADING:
                return RowType.LOADING.ordinal();

            case EMPTY:
                return RowType.NO_ITEMS.ordinal();

            default:
                return RowType.ITEM.ordinal();
        }
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent
     * an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowType rowType = RowType.values()[viewType];
        switch (rowType) {
            case ITEM:
                return onCreateRegularViewHolder(parent, viewType);

            case LOADING:
                return onCreateLoadingViewHolder(parent);

            case NO_ITEMS:
                return onCreateNoItemsViewHolder(parent);

            default:
                return onCreateRegularViewHolder(parent, viewType);
        }
    }

    protected abstract VH onCreateRegularViewHolder(ViewGroup parent, int viewType);

    private RecyclerView.ViewHolder onCreateLoadingViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.progress, parent, false);

        return new EmptyViewHolder(view);
    }

    private RecyclerView.ViewHolder onCreateNoItemsViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.no_items, parent, false);

        return new EmptyViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TModel item;

        chooseBestItemsStates();
        int viewType = getItemViewType(position);
        RowType rowType = RowType.values()[viewType];

        if (rowType == RowType.ITEM) {
            item = getList().get(position);
            onRegularBindViewHolder((VH) holder, position, item);
        }
    }

    private void chooseBestItemsStates() {
        if (list == null)
            setItemsStates(ItemsStates.LOADING);

        else if (list.size() == 0)
            setItemsStates(ItemsStates.EMPTY);
    }

    protected abstract void onRegularBindViewHolder(VH holder, int position, TModel item);

    /**
     * Append list to the current list
     *
     * @param newList the new list to be added
     */
    public void addItems(@NonNull List<TModel> newList) {
        checkToInit();

        list.addAll(newList);
        setItemsStates(ItemsStates.ITEMS);
        notifyDataSetChanged();
    }

    /**
     * Append a new item to the current list
     *
     * @param item the new item to be added
     */
    public void addItem(@NonNull TModel item) {
        checkToInit();

        if (!list.contains(item)) {
            list.add(item);
            setItemsStates(ItemsStates.ITEMS);
            notifyDataSetChanged();
        }
    }

    /**
     * Remove an item rom the current list
     *
     * @param item the item to be removed
     */
    public void removeItem(@NonNull TModel item) {
        checkToInit();

        int indexOfItem = list.indexOf(item);
        if (indexOfItem != -1) {
            list.remove(indexOfItem);

            if (list.size() == 0)
                setItemsStates(ItemsStates.EMPTY);
            else
                setItemsStates(ItemsStates.ITEMS);

            notifyDataSetChanged();
        }
    }

    /**
     * Clear all items
     */
    public void clearItems() {
        checkToInit();

        list.clear();
        setItemsStates(ItemsStates.EMPTY);
        notifyDataSetChanged();
    }

    /**
     * Reset the list and make it {@link null}
     */
    public void resetItems() {
        list = null;
        setItemsStates(ItemsStates.LOADING);
        notifyDataSetChanged();
    }

    private void checkToInit() {
        if (list == null)
            initItems();
    }

    protected abstract void initItems();

    /**
     * Get a specific item
     *
     * @param index the index of the item
     * @return the item of type {@link TModel}
     */
    public TModel getItem(int index) {
        if (list != null && list.get(index) != null) {
            return list.get(index);
        } else {
            throw new IllegalArgumentException("Item with index " + index + " doesn't exist, list is " + list);
        }
    }

    /**
     * Get the list used in this adapter
     *
     * @return the list used in this adapter
     */
    public List<TModel> getList() {
        return list;
    }

    /**
     * Set the list used in this adapter
     *
     * @param list the list to be used in this adapter
     */
    public void setList(List<TModel> list) {
        this.list = list;
    }

    /**
     * Get the type of items
     *
     * @return the type of items
     */
    public ItemsStates getItemsStates() {
        return itemsStates;
    }

    /**
     * Set the type of items
     *
     * @param itemsStates the type of items
     */
    public void setItemsStates(ItemsStates itemsStates) {
        this.itemsStates = itemsStates;
    }

    /**
     * Is loading data performing now
     *
     * @return {@link true} if loading data is performing now
     */
    public boolean isLoadingMore() {
        return isLoadingMore;
    }

    /**
     * Spesify that loading data is performing now
     *
     * @param isLoadingMore {@link true} if loading data is performing now
     */
    public void setIsLoadingMore(boolean isLoadingMore) {
        this.isLoadingMore = isLoadingMore;
    }

    /**
     * Register a callback to be invoked when an item of the list is clicked
     *
     * @param onItemClickListener the callback that will run
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected void onItemClick(int position) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(position);
    }

    /**
     * The type of item at all
     */
    public enum ItemsStates {
        LOADING, ITEMS, EMPTY
    }

    protected enum RowType {
        ITEM, LOADING, NO_ITEMS
    }

    /**
     * Interface definition for a callback to be invoked when an item is clicked
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * The base class for ViewHolder.
     * It registers {@link BaseListAdapter.OnItemClickListener}
     *
     * @see {@link #setOnItemClickListener(OnItemClickListener)}
     */
    protected class ItemBaseViewHolder extends RecyclerView.ViewHolder {

        public ItemBaseViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(getLayoutPosition());
                }
            });
        }
    }

    /**
     * A ViewHolder for items that do not need any ViewHolder
     */
    protected class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}

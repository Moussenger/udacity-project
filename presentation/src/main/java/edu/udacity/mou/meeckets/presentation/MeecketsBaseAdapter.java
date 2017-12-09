package edu.udacity.mou.meeckets.presentation;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;

/**
 * Created by mou on 11/22/17.
 */

public abstract class MeecketsBaseAdapter<T extends MeecketsViewHolder, M extends Comparable<M>> extends RecyclerView.Adapter<T> {
    private Context context;
    private List<M> items;
    private StorageMapper<M> mapper;
    private IMeecketsListListener<M> listener;

    public MeecketsBaseAdapter(Context context, StorageMapper<M> mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(getLayout(), parent, false);

        return createViewHolder(item);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.setListener(listener);
        holder.bind(context, items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setCursor(Cursor cursor) {
        items = getMapper().all(cursor);
        onNewItems(items);
        Collections.sort(items);
        notifyDataSetChanged();
    }

    public void setListener(IMeecketsListListener<M> listener) {
        this.listener = listener;
        notifyDataSetChanged();
    }

    protected StorageMapper<M> getMapper() {
        return mapper;
    }

    protected List<M> getItems() {
        return this.items;
    }

    public abstract void onNewItems(List<M> items);

    public abstract int getLayout();

    public abstract T createViewHolder(View item);

    public interface IMeecketsListListener<M> {
        void onClick(M data);
    }

}

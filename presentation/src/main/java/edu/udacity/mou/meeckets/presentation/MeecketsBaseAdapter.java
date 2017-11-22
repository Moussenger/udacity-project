package edu.udacity.mou.meeckets.presentation;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;

/**
 * Created by mou on 11/22/17.
 */

public abstract class MeecketsBaseAdapter<T extends MeecketsViewHolder, M> extends RecyclerView.Adapter<T> {
    private Context context;
    private Cursor cursor;
    private StorageMapper<M> mapper;

    public MeecketsBaseAdapter(Context context, StorageMapper<M> mapper) {
        this.context = context;
        cursor = null;
        this.mapper = mapper;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(getLayout(), parent, false);

        return createViewHolder(item);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        cursor.moveToPosition(position);
        holder.bind(context, cursor);
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    protected StorageMapper<M> getMapper() {
        return mapper;
    }

    public abstract int getLayout();

    public abstract T createViewHolder(View item);

}

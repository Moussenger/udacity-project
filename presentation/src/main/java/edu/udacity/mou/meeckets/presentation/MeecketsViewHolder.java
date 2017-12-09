package edu.udacity.mou.meeckets.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;

/**
 * Created by mou on 11/22/17.
 */

public abstract class MeecketsViewHolder<T> extends RecyclerView.ViewHolder {
    private StorageMapper<T> mapper;
    private MeecketsBaseAdapter.IMeecketsListListener<T> listener;
    private T item;

    public MeecketsViewHolder(View itemView, StorageMapper<T> mapper) {
        super(itemView);
        this.mapper = mapper;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener((view) -> {
            if (listener != null) {
                listener.onClick(getItem());
            }
        });
    }

    public void setListener(MeecketsBaseAdapter.IMeecketsListListener<T> listener) {
        this.listener = listener;
    }

    protected StorageMapper<T> getMapper() {
        return mapper;
    }

    protected T getItem() {
        return item;
    }

    protected void setItem(T item) {
        this.item = item;
    }

    public abstract void bind(Context context, T item);
}

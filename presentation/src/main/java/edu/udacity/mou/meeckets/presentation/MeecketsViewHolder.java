package edu.udacity.mou.meeckets.presentation;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;

/**
 * Created by mou on 11/22/17.
 */

public abstract class MeecketsViewHolder<T> extends RecyclerView.ViewHolder {
    private StorageMapper<T> mapper;

    public MeecketsViewHolder(View itemView, StorageMapper<T> mapper) {
        super(itemView);
        this.mapper = mapper;
        ButterKnife.bind(this, itemView);
    }

    protected StorageMapper<T> getMapper() {
        return mapper;
    }

    public abstract void bind(Context context, Cursor cursor);
}

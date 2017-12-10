package edu.udacity.mou.meeckets.presentation.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mou on 12/10/17.
 */

public class EmptyRecyclerView extends RecyclerView {
    @Nullable
    private View emptyView;

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setEmptyView(View view) {
        this.emptyView = view;
    }

    public View getEmptyView() {
        return this.emptyView;
    }

    private void showEmptyView() {
        emptyView.setVisibility(VISIBLE);
        this.setVisibility(GONE);
    }

    private void hideEmptyView() {
        emptyView.setVisibility(GONE);
        this.setVisibility(VISIBLE);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyDataObserver);
        }

        emptyDataObserver.onChanged();
    }

    private AdapterDataObserver emptyDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();

            if (adapter != null && getEmptyView() != null) {
                processDataChange(adapter);
            }
        }

        private void processDataChange(Adapter<?> adapter) {
            switch (adapter.getItemCount()) {
                case 0:
                    showEmptyView();
                    break;
                default:
                    hideEmptyView();
            }
        }
    };
}

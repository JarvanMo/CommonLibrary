package com.jarvanmo.common.widget.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.annotation.Keep;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * My goal is to make creating adapter  easier.
 * So,this adapter is based on {@link DataBindingUtil} and reflection.
 * I never test performance of this adapter
 * if you really concern about performance, you'd better not use this adapter.
 */
@Keep
public abstract class RecyclerViewAdapter<I> extends RecyclerView.Adapter<RecyclerViewAdapter.BaseViewHolder> {

    private List<I> data = new ArrayList<>();


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return null;
        }
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new BaseViewHolder(binding);
    }


    @Override
    public int getItemViewType(int position) {
        return getItemLayout(position);
    }

    @CallSuper
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        onBind(holder,position,data.get(position));
    }

    public abstract void onBind(BaseViewHolder holder,int position,I item);

    @LayoutRes
    protected abstract int getItemLayout(int position);


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public void setData(List<I> newData) {
        data.clear();
        addAll(newData);
    }

    public void add(I item) {
        data.add(item);
        notifyItemInserted(data.size() == 0 ? 0 : data.size() - 1);
    }

    public void change(int position){
        if(position < 0 || position >= data.size()){
            return;
        }
        notifyItemChanged(position);
    }

    public void addAll(List<I> newData) {
        if (newData == null) {
            return;
        }
        int oldSize = data.size();
        data.addAll(newData);
        notifyItemRangeInserted(oldSize, newData.size());
    }

    public void set(int position, I item) {
        if (position < 0 || position >= data.size()) {
            return;
        }
        data.set(position, item);
        notifyItemChanged(position);
    }


    public void remove(int position) {
        if (position < 0 || position >= data.size()) {
            return;
        }

        data.remove(position);
        notifyItemRemoved(position);
    }


    public void remove(I item) {
        int index = data.indexOf(item);
        if (data.remove(item)) {
            notifyItemRemoved(index);
        }
    }


    public void remove(int start, int end) {
        if (start > end) {
            return;
        }
        if (start < 0 || start >= data.size()) {
            return;
        }
        if (end < 0 || end >= data.size()) {
            return;
        }

        for (int i = start; i <= end; i++) {
            remove(i);
        }


    }

    public boolean contains(I item) {
        return data != null && data.contains(item);
    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public void clear() {

        if (data != null) {
            notifyDataSetChanged();
            data.clear();
        }

    }

    protected static class BaseViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mBinding;

        BaseViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }

}


package com.example.mo.commonutil;

import com.jarvanmo.common.widget.recyclerview.RecyclerViewAdapter;

/**
 * Created by mo on 17-3-28.
 * Copyright © 2017, cnyanglao, Co,. Ltd. All Rights Reserve
 */

public class TestAdapter extends RecyclerViewAdapter<Test> {


    @Override
    protected int getItemLayout(int position) {
            return R.layout.item_rc2;
    }


    @Override
    public void onBind(BaseViewHolder holder, int position, Test item) {
        holder.getBinding().setVariable(com.example.mo.commonutil.BR.v,item);
        holder.getBinding().executePendingBindings();
    }
}

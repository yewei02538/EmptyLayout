package me.weyye.emptylayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private  List<String> mList;

    public MyAdapter(List<String> data) {
        this.mList=data;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAdapter.MyViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        private  TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }

}

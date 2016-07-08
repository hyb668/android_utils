package com.dou.gank.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dou.gank.domain.enity.NowList;
import com.wu.allen.zhuanlan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 16/6/21.
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {

    private final ArrayList<NowList.ResultsBean> sampleData = new ArrayList<>();

    // 用于创建控件
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        // 获得列表项控件（LinearLayer对象）

        // list_basic_item.xml布局文件中只包含一个<LinearLayer>标签，在该标签中包含
        // 了一个<TextView>标签
        //  item是LinearLayout对象
        View item = LayoutInflater.from(parentViewGroup.getContext()).inflate(
                R.layout.list_basic_item, parentViewGroup, false);

        return new ViewHolder(item);

    }

    // 为控件设置数据
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        //  获取当前item中显示的数据
        final NowList.ResultsBean rowData = sampleData.get(position);

        //  设置要显示的数据
        viewHolder.textViewSample.setText(rowData.getDesc());
        viewHolder.itemView.setTag(rowData);
    }

    @Override
    public int getItemCount() {

        return sampleData.size();
    }

    public void addData(List<NowList.ResultsBean> list) {
        sampleData.addAll(list);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewSample;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewSample = (TextView) itemView.findViewById(R.id.textViewSample);
        }
    }
}

package com.zhangyan.myssc.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhangyan.myssc.R;
import com.zhangyan.myssc.bean.BaseData;

/**
 * 基础数据列表的适配器
 * @author 张颜
 * @date 2017年4月14日 17:10:09
 */
public class BaseDataListAdapter extends BaseAdapter {
    private Context context;
    public List<BaseData> basedataList;

    public BaseDataListAdapter(Context context, List<BaseData> basedataList) {
        this.context = context;
        this.basedataList = basedataList;
    }

    @Override
    public int getCount() {
        return basedataList.size();
    }

    @Override
    public BaseData getItem(int position) {
        return basedataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams") @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_basedata_list_adapter, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.tv_expect.setText((position + 1)+"");
        viewHolder.tv_openCode.setText(basedataList.get(position).getOpenCode());
        viewHolder.tv_openTime.setText(basedataList.get(position).getOpenTime());
        
        viewHolder.tv_openTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Intent intent = new Intent(context, BanBiaoDetailActivity.class);
				Bundle bundle=new Bundle();  
	            bundle.putSerializable("banbiao", banbiaoList.get(position));
	            intent.putExtras(bundle);
				context.startActivity(intent);
*/				
			}
		});
        return convertView;
    }

    class ViewHolder {
        TextView tv_expect;
        TextView tv_openCode;
        TextView tv_openTime;
        ViewHolder(View view) {
        	tv_expect = (TextView) view.findViewById(R.id.tv_expect);
        	tv_openCode = (TextView) view.findViewById(R.id.tv_openCode);
        	tv_openTime = (TextView) view.findViewById(R.id.tv_openTime);
        }
    }
}

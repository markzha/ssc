package com.zhangyan.myssc.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhangyan.myssc.R;
import com.zhangyan.myssc.bean.LuBoFile;

/**
 * 文件名二级列表的适配器
 * @author 张颜
 * @date 2017年4月14日 17:10:09
 */
public class FileListAdapter extends BaseAdapter {
	
	private Context context;
	/*录波文件列表*/
	public List<LuBoFile> luboFileList;

    public FileListAdapter(Context context, List<LuBoFile> luboFileList) {
        this.context = context;
        this.luboFileList = luboFileList;
    }

    @Override
    public int getCount() {
        return luboFileList.size();
    }

    @Override
    public LuBoFile getItem(int position) {
        return luboFileList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_file_list_adapter, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.tv_index.setText((position + 1)+"");
        viewHolder.tv_fileName.setText(luboFileList.get(position).getFileName());
        viewHolder.tv_reason.setText(luboFileList.get(position).getReason());
        try {
			String time = luboFileList.get(position).getTime();
			String year = time.substring(0, 4);
			String month = time.substring(4, 6);
			String day = time.substring(6, 8);
			String hour = time.substring(8, 10);
			String minute = time.substring(10, 12);
			String second = time.substring(12, 14);
			String msecond = time.substring(14, 17);
			viewHolder.tv_time.setText(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second+":"+msecond);
		} catch (Exception e) {
			viewHolder.tv_time.setText("暂未获取时间");
		}
        return convertView;
    }

    class ViewHolder {
        TextView tv_index;
        TextView tv_reason;
        TextView tv_time;
        TextView tv_fileName;
        ViewHolder(View view) {
        	tv_index = (TextView) view.findViewById(R.id.tv_index);
        	tv_fileName = (TextView) view.findViewById(R.id.tv_fileName);
        	tv_reason = (TextView) view.findViewById(R.id.tv_reason);
        	tv_time = (TextView) view.findViewById(R.id.tv_time);
        }
    }
}

package com.zhangyan.myssc.receiver;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DateChangeReceiver extends BroadcastReceiver {

	private static final String TAG = "DateChangeReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		updateTime(context);
	}

	private void updateTime(Context context) {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		/*if (minute == 0) {
			switch (hour) {
			case 0:
			case 6:
			case 9:
			case 12:
			case 15:
			case 19:
			case 22:
				AddedCityDaoImpl addedCityDaoImpl = new AddedCityDaoImpl(context);
				List<AddedCityBean> list = addedCityDaoImpl.queryAllAddedCity();
				for (AddedCityBean addedCityBean : list) {
					Intent intent = new Intent(context,SearchWeatherService.class);
					intent.putExtra("startMode", URLData.MODE_NET);
					intent.putExtra("cityName", addedCityBean.cityName);
					intent.putExtra("area_id", addedCityBean.cityID);
					intent.putExtra("activity", "fragment");
					context.startService(intent);
				}

				break;
			}
		}*/




	}

}

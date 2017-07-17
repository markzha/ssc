package com.zhangyan.myssc.util;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zhangyan.myssc.frame.NkProperty;
import com.zhangyan.myssc.frame.NkPropertyForUpdate;
import com.zhangyan.myssc.frame.NkStatusProperty;
import com.zhangyan.myssc.frame.NkTable;
import com.zhangyan.myssc.frame.NoDbProperty;


public class MyDbUtil extends SQLiteOpenHelper {

	private static final int VERSION = 10;
	private static final String DBNAME = "myssc.db";
	SQLiteDatabase db;
	String TAG = "MyDbUtil";
	public Context context;

	public SQLiteDatabase getDb() {
		return db;
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}

	public MyDbUtil(Context context) {
		/*super(context, context.getFilesDir()+ "/" + DBNAME, null,
				VERSION);*/

		super(context, context.getExternalFilesDir(Context.STORAGE_SERVICE).getAbsolutePath()
				+ File.separator + "db" + File.separator + DBNAME, null,
				VERSION);
		db = this.getWritableDatabase();
		this.context = context;
	}

	/**
	 * 指定update语句
	 * 
	 * @param sql
	 * @param params
	 * @throws Exception
	 */
	private void executeUpdate(String sql, Object[] params) throws Exception {
		if (params == null) {
			db.execSQL(sql);
		} else {
			db.execSQL(sql, params);
		}
	}

	/**
	 * 根据指定的一个字段查询实体
	 * 
	 * @param obj
	 * @param column
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public <T> T getObject(T obj, String column, String param) throws Exception {
		T t = null;
		String sql = this.getSelectSql(obj, column);
		Cursor cursor = db.rawQuery(sql, new String[] { param });
		if (cursor.moveToNext()) {
			t = parseObj(obj, cursor);

		}
		cursor.close();
		return t;

	}

	/**
	 * 根据自定义的sql语句查询实体
	 * 
	 * @param obj
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public <T> T getObject(T obj, String sql, String[] params) throws Exception {
		// SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(sql, params);
		T t = null;
		if (cursor.moveToNext()) {
			t = parseObj(obj, cursor);
		}
		cursor.close();
		return t;
	}

	/**
	 * 根据自定义的sql语句，查询结果列表
	 * 
	 * @param obj
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getList(T obj, String sql, String[] params)
			throws Exception {
		// SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(sql, params);
		List<T> list = parseList(obj, cursor);
		cursor.close();
		return list;
	}
	
	/**
	 * 添加一个实体
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void addObj(Object obj) throws Exception {
		executeUpdate(this.getInsertSql(obj), getParameterValueAll(obj));
	}

	/**
	 * update上传实体的状态, 1）如果为0--“检查未上传”update为"检查已上传"
	 * 2）如果是1--“维修未上传”updaee为"维修已上传"
	 */
	public void updateObjUploadStatus(Object obj) throws Exception {
		String updateSql = getUpdateObjUploadStatusSql(obj);
		// 如果返回值不是“no-need”,就执行数据状态的update语句
		if (!"no-need".equals(updateSql)) {
			executeUpdate(updateSql, null);
		}
	}

	/**
	 * update上传状态 的sql语句的生成语句 1）如果为0--“检查未上传”update为"检查已上传"
	 * 2）如果是1--“维修未上传”updaee为"维修已上传"
	 **/
	private String getUpdateObjUploadStatusSql(Object obj) throws Exception {

		// 是否需要做状态的update，判断的依据是实体是否有“NkStatusProperty”属性
		boolean needUpateStatus = false;

		StringBuffer sb = new StringBuffer();
		// 主键字段
		String primaryKey = "";
		// 主键的值
		Object primaryValue = "";
		if (obj.getClass().isAnnotationPresent(NkTable.class)) {
			NkTable des = (NkTable) obj.getClass().getAnnotation(NkTable.class);
			String tableName = des.name();
			sb.append(" update ").append(tableName).append(" set ");
		}
		Method[] methods = obj.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(NkStatusProperty.class)) {
				needUpateStatus = true;
				NkProperty nkProperty = methods[i]
						.getAnnotation(NkProperty.class);
				// 设置上传的状态
				String status = (String) methods[i]
						.invoke(obj, new Object[] {});
				int intStatus = Integer.parseInt(status);
				if (intStatus == 0) {
					status = "1";
				} else if (intStatus == 2) {
					status = "3";
				}

				sb.append(nkProperty.name()).append(" = " + status + ",");
			}
			if (methods[i].isAnnotationPresent(NkPropertyForUpdate.class)) {
				NkPropertyForUpdate nkPropertyForUpdate = methods[i]
						.getAnnotation(NkPropertyForUpdate.class);
				primaryKey = nkPropertyForUpdate.name();
				primaryValue = methods[i].invoke(obj, new Object[] {});

			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" where  ").append(primaryKey)
				.append(" = '" + primaryValue + "' ");
		if (needUpateStatus == true) {
			return sb.toString();
		} else {
			return "no-need";
		}

	}

	/**
	 * update 一个实体
	 * 
	 * @param sql
	 * @param objs
	 * @throws Exception
	 */
	public void updateObj(Object obj) throws Exception {
		executeUpdate(getUpdateSql(obj), this.getParameterValueForUpdate(obj));
	}

	/**
	 * 删除一个实体
	 * 
	 * @param obj
	 * @param id
	 * @throws Exception
	 */
	public void delObj(Object obj, String id) throws Exception {
		executeUpdate(this.getDelSql(obj), new Object[] { id });
	}
	/**
	 * 
	* @param sql
	* @throws Exception 
	* void
	 */
	public void delAllObj(String sql) throws Exception{
		executeUpdate(sql,null);
	}

	/**
	 * 得到参数的数组, 其中不包括id属性
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] getParameterValueAll(Object obj) throws Exception {
		List list = new ArrayList();
		Method[] methods = obj.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(NkProperty.class)
					&& !methods[i].isAnnotationPresent(NoDbProperty.class)) {
				Object myobj = methods[i].invoke(obj, new Object[] {});
				list.add(myobj);
			}
		}
		return list.toArray();

	}

	/**
	 * 得到update操作的参数数组, 其中不包括id属性
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] getParameterValueForUpdate(Object obj) throws Exception {
		List list = new ArrayList();
		Method[] methods = obj.getClass().getMethods();
		Object upKey = null;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(NkProperty.class)
					&& !methods[i].isAnnotationPresent(NoDbProperty.class)) {
				Object myobj = methods[i].invoke(obj, new Object[] {});

				if (methods[i].isAnnotationPresent(NkPropertyForUpdate.class)) {
					upKey = myobj;
				}

				list.add(myobj);
			}
		}
		list.add(upKey);
		return list.toArray();

	}

	/**
	 * insert 的sql语句的生成语句
	 **/
	private String getInsertSql(Object obj) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (obj.getClass().isAnnotationPresent(NkTable.class)) {
			NkTable des = (NkTable) obj.getClass().getAnnotation(NkTable.class);
			String tableName = des.name();
			sb.append(" insert into ").append(tableName).append(" (");
		}
		Method[] methods = obj.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(NkProperty.class)
					&& !methods[i].isAnnotationPresent(NoDbProperty.class)) {
				NkProperty nkProperty = methods[i]
						.getAnnotation(NkProperty.class);
				sb.append(nkProperty.name()).append(",");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(") values (");
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(NkProperty.class)
					&& !methods[i].isAnnotationPresent(NoDbProperty.class)) {
				methods[i].getAnnotation(NkProperty.class);
				sb.append("?,");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}

	/**
	 * update 的sql语句的生成语句
	 **/
	private String getUpdateSql(Object obj) throws Exception {
		StringBuffer sb = new StringBuffer();
		String primaryKey = "";
		if (obj.getClass().isAnnotationPresent(NkTable.class)) {
			NkTable des = (NkTable) obj.getClass().getAnnotation(NkTable.class);
			String tableName = des.name();
			sb.append(" update ").append(tableName).append(" set ");
		}
		Method[] methods = obj.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(NkProperty.class)
					&& !methods[i].isAnnotationPresent(NoDbProperty.class)) {
				NkProperty nkProperty = methods[i]
						.getAnnotation(NkProperty.class);
				sb.append(nkProperty.name()).append(" = ?,");
			}
			if (methods[i].isAnnotationPresent(NkPropertyForUpdate.class)) {
				NkPropertyForUpdate nkPropertyForUpdate = methods[i]
						.getAnnotation(NkPropertyForUpdate.class);
				primaryKey = nkPropertyForUpdate.name();
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" where  ").append(primaryKey).append(" = ? ");
		//System.out.println("sb=========" + sb.toString());
		return sb.toString();
	}

	/**
	 * 得到根据id删除实体的语句
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private String getDelSql(Object obj) throws Exception {
		StringBuffer sb = new StringBuffer();
		String primaryKey = "";
		if (obj.getClass().isAnnotationPresent(NkTable.class)) {
			NkTable des = (NkTable) obj.getClass().getAnnotation(NkTable.class);
			String tableName = des.name();

			Method[] methods = obj.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].isAnnotationPresent(NkPropertyForUpdate.class)) {
					NkPropertyForUpdate nkPropertyForUpdate = methods[i]
							.getAnnotation(NkPropertyForUpdate.class);
					primaryKey = nkPropertyForUpdate.name();
				}
			}

			sb.append(" delete from ").append(tableName).append(" where ")
					.append(primaryKey).append(" = ? ");
			System.out.println("getDelSql=  sb===" + sb);
		}
		return sb.toString();
	}

	/**
	 * 得到查询的sql语句
	 * 
	 * @param clz
	 * @throws Exception
	 */
	private String getSelectSql(Object obj, String column) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select ");

		Method[] methods = obj.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(NkProperty.class)) {
				NkProperty nkProperty = methods[i]
						.getAnnotation(NkProperty.class);
				sb.append(nkProperty.name()).append(",");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" from ");
		if (obj.getClass().isAnnotationPresent(NkTable.class)) {
			NkTable des = (NkTable) obj.getClass().getAnnotation(NkTable.class);
			sb.append(des.name());
		}
		sb.append(" where ").append(column);
		sb.append(" = ? ");
		return sb.toString();
	}

	/**
	 * parsing 一个结果集成一个列表
	 * 
	 * @param obj
	 * @param cursor
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> parseList(T obj, Cursor cursor) throws Exception {
		List<T> list = new ArrayList<T>();
		while (cursor.moveToNext()) {
			list.add(parseObj(obj, cursor));
		}
		return list;

	}

	/**
	 * 根据查询呢到的结果,parsing成一个实体
	 * 使用该方法之前，一定要moveToNext,这里不进行moveToNext操作的原因是:要和查询List共用代码
	 * 
	 * @param obj
	 * @param cursor
	 * @return
	 * @throws Exception
	 */
	@SuppressLint("DefaultLocale")
	@SuppressWarnings("unchecked")
	private <T> T parseObj(T obj, Cursor cursor) throws Exception {
		Method[] methods = obj.getClass().getDeclaredMethods();
		Map<String, String> map = new HashMap<String, String>();

		T outCome = (T) obj.getClass().newInstance();
		// 字段名称
		String colName = "";
		// if(cursor.moveToNext()){
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("get")) {
				NkProperty nkProperty = methods[i]
						.getAnnotation(NkProperty.class);
				if (nkProperty != null) {
					colName = nkProperty.name();
					map.put(methods[i].getName().substring(3),
							colName.toLowerCase());
				} else {
					colName = methods[i].getName().substring(3, 4)
							.toLowerCase()
							+ methods[i].getName().substring(4);
					map.put(methods[i].getName().substring(3), colName);
				}

			}

		}

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("set")) {

				if (map.get(methods[i].getName().substring(3)) == null) {
					NkProperty nkProperty = methods[i]
							.getAnnotation(NkProperty.class);
					if (nkProperty == null) {
						continue;
					}
				}

				if (cursor.getColumnIndex(map.get(methods[i].getName().substring(3))) != -1) {
					
					methods[i].invoke(outCome, cursor.getString(cursor
							.getColumnIndex(map.get(methods[i].getName()
									.substring(3)))));
				} else if (cursor.getColumnIndex(map.get(methods[i].getName().substring(3)).toUpperCase()) != -1) {
					methods[i].invoke(outCome, cursor.getString(cursor
							.getColumnIndex(map.get(
									methods[i].getName().substring(3))
									.toUpperCase())));
				}
			}
		}

		return outCome;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		/**
		 * 在调getReadableDatabase或getWritableDatabase时，会判断指定的数据库是否存在，
		 * 不存在则调SQLiteDatabase.create创建， onCreate只在数据库第一次创建时才执行
		 * 数据库第一次创建执行，只执行一次，以后不再执行
		 */
	}
	//只有在版本  VERSION发生变化的时候才会调用该方法
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e("TAG", "当前数据库版本(旧版本)=="+oldVersion );
		Log.e("TAG", "要更新到的版本(新版本)=="+newVersion );
		for (int j = oldVersion; j < newVersion; j++) {

			switch (j) {
			case 9:
				updateDB9_10(db);
				break;
			case 10:
				updateDB10_11(db);
				break;
			case 11:
				updateDB11_12(db);
				break;
			case 12:
				updateDB12_13(db);
				break;
			default:
				break;
			}
		}
	}
	
	//应用从1.10升1.11   数据库从版本8升级到版本9
	public void updateDB9_10(SQLiteDatabase db) {
		Log.e("当前数据库版本升级====", "updateDB8_9");
		//数据库变动
		//new UpdateDBSql().getSQL9_10(db);
	}
	//应用从1.7.1升1.8   数据库从版本10升级到版本11
	public void updateDB10_11(SQLiteDatabase db) {
		Log.e("当前数据库版本升级====", "updateDB10_11");
		//数据库变动
		//new UpdateDBSql().getSQL10_11(db);
	}
	//应用从1.9升1.9.1   数据库从版本11升级到版本12
	public void updateDB11_12(SQLiteDatabase db) {
		Log.e("当前数据库版本升级====", "updateDB11_12");
		//数据库变动
		//new UpdateDBSql().getSQL11_12(db);
	}
	public void updateDB12_13(SQLiteDatabase db) {
		Log.e("当前数据库版本升级====", "updateDB12_13");
		//数据库变动
		//new UpdateDBSql().getSQL12_13(db);
	}
	
}

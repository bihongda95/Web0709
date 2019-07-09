package com.woniu.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO<T> {
	
	Connection conn;
	PreparedStatement stat;
	ResultSet rs;
	
	
	public void update(String sql,Object[] objs) {
		try {
			conn = JDBCUtil.getConn();
			stat = conn.prepareStatement(sql);
			for(int i=0;i<objs.length;i++) {
				stat.setObject(i+1,objs[i]);
			}
			stat.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.closeAll(rs, stat, conn);
		}
		
	}
	
	public List<T> select(String sql,Object[] objs,Class c){
		List<T> list = new ArrayList<T>();
		try {
			conn = JDBCUtil.getConn();
			stat = conn.prepareStatement(sql);
			for(int i=0;i<objs.length;i++) {
				stat.setObject(i+1,objs[i]);
			}
			rs = stat.executeQuery();
			while(rs.next()) {
				Object obj = c.newInstance();
				Method[] ms = c.getDeclaredMethods();
				for(Method m:ms) {
					String methodName = m.getName();
					if(methodName.startsWith("set")) {
						String fieldName = methodName.substring(3);
						Class[] cs = m.getParameterTypes();
						if(cs[0]==int.class) {
							m.invoke(obj, rs.getInt(fieldName));
						}else if(cs[0]==String.class) {
							m.invoke(obj, rs.getString(fieldName));
						}if(cs[0]==Double.class) {
							m.invoke(obj, rs.getDouble(fieldName));
						}
					}
				}
				list.add((T) obj);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.closeAll(rs, stat, conn);
		}
		
		return list;
	}

}

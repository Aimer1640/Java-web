package com.example.lab_12.dao;



import com.example.lab_12.utils.ConfigTools;

import java.sql.*;

/**
 * ���ݿ����ӹ�����
 * @author
 *
 */
public class BaseDao1 {
	
	protected ResultSet rs = null;
	protected PreparedStatement ps = null;
	protected Connection conn = null;
	
	public static void main(String[] args) {
		System.out.println();
	}
	//������ݿ�����
	public boolean getConnection() {

		try {
			//��������
			Class.forName(ConfigTools.getProperties().getProperty("driver"));
			//׼��URL �������ݿ�
			String url = ConfigTools.getProperties().getProperty("url");
			//׼���˺�����
			String uname = ConfigTools.getProperties().getProperty("username");
			String upwd = ConfigTools.getProperties().getProperty("userpwd");
//			//��������
//			Class.forName(ConfigTools.getProperties("driver"));
//			//׼��URL �������ݿ�
//			String url = ConfigTools.getProperties("url");
//			//׼���˺�����
//			String uname = ConfigTools.getProperties("username");
//			String upwd = ConfigTools.getProperties("userpwd");
			//ͨ��DriverManager.getConnection���Connection����
			 conn = DriverManager.
					getConnection(url, uname, upwd);
			 
			 return true;
			 
		} catch (Exception e) {
			e.printStackTrace();
			 return false;
		}
		
		
	}
	/**
	 * �����޸�  ���  ɾ��
	 * @param sql
	 * @param objs
	 * @return
	 */
	public int executUpdate(String sql,Object[] objs) {
		int updateRows = 0;
		try {
			
			ps = conn.prepareStatement(sql);
			if(objs!=null) {
				//���ռλ��
				for(int i = 0;i<objs.length;i++)
				{
					ps.setObject((i+1), objs[i]);
				}
				
			}
			updateRows = ps.executeUpdate();
			return updateRows;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return updateRows;
		}
	}
	
	
	/**
	 * ������ѯ
	 * @param sql  Я��ռλ��  ��
	 * @param objs  �������� 
	 * @return
	 */
	public ResultSet executeSQL(String sql,Object[] objs) {
		try {
		
			ps = conn.prepareStatement(sql);
			if(objs!=null) {
				//���ռλ��
				for(int i = 0;i<objs.length;i++)
				{
					ps.setObject((i+1), objs[i]);
				}
				
			}
			rs = ps.executeQuery();
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * �ͷ���Դ
	 * @return
	 */
	public void closeResources() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

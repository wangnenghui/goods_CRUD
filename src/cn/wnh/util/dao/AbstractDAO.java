package cn.wnh.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import cn.wnh.util.dbc.DatabaseConnection;

public class AbstractDAO {
	
	protected Connection conn ;
	protected PreparedStatement pstmt ;
	public AbstractDAO() {
		this.conn = DatabaseConnection.get() ;
	}
	protected boolean doRemoveBatchHandleByInt(Set<Integer> ids,String tableName,String column)throws SQLException{
		StringBuffer buf=new StringBuffer();
		buf.append("delete from ").append(tableName).append(" where ").append(column).append(" in (" );
		Iterator<Integer> iter=ids.iterator();
		while(iter.hasNext()){
			buf.append(iter.next()).append(",");
			buf.delete(buf.length()-1,buf.length() ).append(")");
			this.pstmt=this.conn.prepareStatement(buf.toString());
			return this.pstmt.executeUpdate()==ids.size();
		}
		return false;
	}
	
	protected Integer getAllCountHandle(String tableName)throws SQLException {
		String sql="select count(*) from " + tableName;
		this.pstmt=this.conn.prepareStatement(sql);
		ResultSet rs=this.pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	protected Integer getAllCountHandle(String tableName,String column,String keyWord)throws SQLException {
		String sql="select count(*) from " + tableName;
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%"+keyWord+"%");
		ResultSet rs=this.pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
		
	}
	protected Integer getCurrentValueHandle(String sequenceName)throws SQLException{
		String sql="select "+ sequenceName+".currval from dual";
		this.pstmt=this.conn.prepareStatement(sql);
		ResultSet rs=this.pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

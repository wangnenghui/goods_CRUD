package cn.wnh.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.wnh.dao.IITemDAO;
import cn.wnh.util.dao.AbstractDAO;
import cn.wnh.vo.Item;

public class ItmemDAOImpl extends AbstractDAO implements IITemDAO {

	@Override
	public boolean doCreate(Item vo) throws Exception {
		String sql="insert into item (iid,title)values(?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getIid());
		super.pstmt.setString(2, vo.getTitle());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(Item vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="update item set title=?  iid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setInt(2, vo.getIid());
		
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public Item findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Item vo=null;
		String sql="select iid,title from item where iid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()){
			vo=new Item();
			vo.setIid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
		}
		return vo;
	}

	@Override
	public List<Item> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Item> all=new ArrayList<Item>();
		String sql="select iid,title from item";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()){
			Item vo=new Item();
			vo.setIid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Item> findAllSplit(Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

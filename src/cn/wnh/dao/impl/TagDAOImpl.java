package cn.wnh.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.wnh.dao.ITagDAO;
import cn.wnh.util.dao.AbstractDAO;
import cn.wnh.vo.Item;
import cn.wnh.vo.Tag;

public class TagDAOImpl extends AbstractDAO implements ITagDAO {

	@Override
	public boolean doCreate(Tag vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into tag(tid,title)values(?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getTid());
		super.pstmt.setString(2, vo.getTitle());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(Tag vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="update tag set title=? where tid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setInt(2, vo.getTid());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tag findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Tag vo=null;
		String sql="select tid,title from tag where tid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()){
			vo=new Tag();
			vo.setTid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
		}
		return vo;
	}

	@Override
	public List<Tag> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Tag> all=new ArrayList<Tag>();
		String sql="select tid,title from tag";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()){
			Tag vo=new Tag();
			vo.setTid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Tag> findAllSplit(Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
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

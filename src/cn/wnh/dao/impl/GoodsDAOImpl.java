package cn.wnh.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.wnh.dao.IGoodsDAO;
import cn.wnh.util.dao.AbstractDAO;
import cn.wnh.vo.Goods;

public class GoodsDAOImpl extends AbstractDAO implements IGoodsDAO {

	@Override
	public boolean doCreate(Goods vo) throws Exception {
		String sql="insert into goods(gid,title,price,photo,iid)values(goods_seq.nextval,?,?,?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setDouble(2, vo.getPrice());
		super.pstmt.setString(3, vo.getPhoto());
		super.pstmt.setInt(4, vo.getIid());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(Goods vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="update goods set title=?,price=?,photo=?,iid=? where gid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setDouble(2, vo.getPrice());
		super.pstmt.setString(3, vo.getPhoto());
		super.pstmt.setInt(4, vo.getIid());
		super.pstmt.setInt(5, vo.getGid());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return super.doRemoveBatchHandleByInt(ids, "goods", "gid");
	}

	@Override
	public Goods findById(Integer id) throws Exception {
		Goods vo=null;
		String sql=" select gid,title,price,photo,iid from goods"
				+ " where gid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1,id);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()){
			vo=new Goods(); 
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getInt(5));
		}
		return vo;
	}

	@Override
	public List<Goods> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize) throws Exception {
		List<Goods> all=new ArrayList<Goods>();
		String sql="select * from ("
				+ " select gid,title,price,photo,iid,rownum rn from goods"
				+ " where rownum<=? ) temp"
				+ " where temp.rn>?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1,currentPage*lineSize);
		super.pstmt.setInt(2,(currentPage-1)*lineSize);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()){
			Goods vo=new Goods();
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getInt(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Goods> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception {
		List<Goods> all=new ArrayList<Goods>();
		String sql="select * from ("
				+ " select gid,title,price,photo,iid,rownum rn from goods"
				+ " where " + column+ " like ? and rownum<=? ) temp"
				+ " where temp.rn>?";
		super.pstmt=super.conn.prepareStatement(sql);
		
		super.pstmt.setString(1, "%"+keyWord+"%");
		super.pstmt.setInt(2,currentPage*lineSize);
		super.pstmt.setInt(3,(currentPage-1)*lineSize);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()){
			Goods vo=new Goods();
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getInt(5));
			all.add(vo);
		}
		return all;
	}
	@Override
	public Integer getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return super.getAllCountHandle("goods");
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return super.getAllCountHandle("goods",column,keyWord);
	}

	@Override
	public boolean doCreateGoodsAndTag(Goods vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql="insert into goods_tag(gid,tid)values(?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		Iterator<Integer> iter=vo.getTids().iterator();
		while(iter.hasNext()){
			super.pstmt.setInt(1, vo.getGid());
			super.pstmt.setInt(2, iter.next());
			super.pstmt.addBatch();
		}
		int result[] = super.pstmt.executeBatch();
		for(int x=0;x<result.length;x++){
			if(result[x]==0){
				return false;
			}
		}
		return true;
	}

	@Override
	public Integer getId() throws SQLException {
		// TODO Auto-generated method stub
	
		return super.getCurrentValueHandle("goods_seq");
	}

	@Override
	public Set<Integer> findAllTagByGoods(Integer id) throws SQLException {
		Set<Integer> allTids=new HashSet<Integer>();
		String sql="select tid from goods_tag where gid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()){
			allTids.add(rs.getInt(1));
		}
		return allTids;
	}

	@Override
	public boolean doRemoveTagByGoods(Integer id) throws SQLException {
		String sql="delete from goods_tag where gid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		return super.pstmt.executeUpdate()>0;
	}

	
	

}

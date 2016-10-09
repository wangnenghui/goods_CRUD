package cn.wnh.dao;

import java.sql.SQLException;
import java.util.Set;

import cn.wnh.util.dao.IDAO;
import cn.wnh.vo.Goods;

public interface IGoodsDAO 	extends IDAO<Integer,Goods> {
	
	public boolean doCreateGoodsAndTag(Goods vo)throws SQLException;
	public Integer getId()throws SQLException;
	public Set<Integer> findAllTagByGoods(Integer id)throws SQLException;
	public boolean doRemoveTagByGoods(Integer id)throws SQLException;
	

}

package cn.wnh.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.wnh.dao.IGoodsDAO;
import cn.wnh.dao.IITemDAO;
import cn.wnh.dao.ITagDAO;
import cn.wnh.dao.impl.GoodsDAOImpl;
import cn.wnh.dao.impl.ItmemDAOImpl;
import cn.wnh.dao.impl.TagDAOImpl;
import cn.wnh.service.IGoodsService;
import cn.wnh.vo.Goods;
import cn.wnhj.util.factory.DAOFactory;
public class GoodServiceImpl implements IGoodsService {

	@Override
	public Map<String, Object> addPre() throws Exception {
		
		IITemDAO itemDAO=DAOFactory.getInstance(ItmemDAOImpl.class);
		ITagDAO tagDAO=DAOFactory.getInstance(TagDAOImpl.class);
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("allItems", itemDAO.findAll());
		map.put("allTags", tagDAO.findAll());
		return map;
	}

	@Override
	public boolean add(Goods vo) throws Exception {
		IGoodsDAO goodsDAO=DAOFactory.getInstance(GoodsDAOImpl.class);
		if(goodsDAO.doCreate(vo)){
			 vo.setGid(goodsDAO.getId());
			 return goodsDAO.doCreateGoodsAndTag(vo);
		}
		return false;
	}

	@Override
	public Map<String, Object> list(String column, String keyWord, int currentPage, int lineSize) throws Exception {
	IGoodsDAO goodsDAO=DAOFactory.getInstance(GoodsDAOImpl.class);
	Map<String,Object> map=new HashMap<String,Object>();
	if(column==null||"".equals(column)||keyWord==null||"".equals(keyWord)){
		map.put("allGoods",goodsDAO.findAllSplit(currentPage, lineSize));
		map.put("goodsCount",goodsDAO.getAllCount());
	}else{
		map.put("goodsGoods",goodsDAO.findAllSplit(column,keyWord,currentPage, lineSize));
		map.put("goodsCount",goodsDAO.getAllCount(column,keyWord));
	}
	IITemDAO itemDAO=DAOFactory.getInstance(ItmemDAOImpl.class);
	map.put("allItems", itemDAO.findAll());
		return map;
	}

	@Override
	public Map<String, Object> editPre(int gid) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		ITagDAO tagDAO=DAOFactory.getInstance(TagDAOImpl.class);
		IITemDAO itemDAO=DAOFactory.getInstance(ItmemDAOImpl.class);
		IGoodsDAO goodsDAO=DAOFactory.getInstance(GoodsDAOImpl.class);
			map.put("allItems",itemDAO.findAll());
			map.put("allTags",tagDAO.findAll());
			Goods goods=goodsDAO.findById(gid);
			goods.setTids(goodsDAO.findAllTagByGoods(gid));
			map.put("goods",goods);
			return map;
	}

	@Override
	public boolean edit(Goods vo) throws Exception {
		IGoodsDAO goodsDAO=DAOFactory.getInstance(GoodsDAOImpl.class);
		if(goodsDAO.doUpdate(vo)){
			if(goodsDAO.doRemoveTagByGoods(vo.getGid())){
				 return goodsDAO.doCreateGoodsAndTag(vo);
			}
		}
		return false;
	}

	@Override
	public boolean remove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		if(ids==null||"".equals(ids)||ids.size()==0){
			return false;
		}
		IGoodsDAO goodsDAO=DAOFactory.getInstance(GoodsDAOImpl.class);
		return goodsDAO.doRemoveBatch(ids);
	}

	
	
	
	
	
	
}

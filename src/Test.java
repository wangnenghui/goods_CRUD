import cn.wnh.service.IGoodsService;
import cn.wnh.service.impl.GoodServiceImpl;
import cn.wnh.vo.Goods;
import cn.wnhj.util.factory.ServiceFactory;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		IGoodsService gs=ServiceFactory.getInstance(GoodServiceImpl.class);
		Goods vo=new Goods();
		vo.setIid(3);
		vo.setPrice(45.34);
		vo.setTitle("sgdsg");
		gs.add(vo);
	}

}

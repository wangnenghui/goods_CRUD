package cn.wnh.vo;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("serial")
public class Goods implements Serializable {
	private Integer gid ;
	private String title ;
	private Double price ;
	private String photo ;
	private Integer iid ;
	private Set<Integer> tids;
	
	public Set<Integer> getTids() {
		return tids;
	}
	public void setTids(Set<Integer> tids) {
		this.tids = tids;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getIid() {
		return iid;
	}
	public void setIid(Integer iid) {
		this.iid = iid;
	}
}

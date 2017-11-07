package xin.liaozhixing.class14ba2.utils;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
	private Integer totalPage;//��ҳ��
	private Integer currentPage;//��ǰҳ
	private Integer totalCount;//������
	private Integer currentCount;//��ǰ��ʾ����
	private List<T> list = new ArrayList<T>();
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}

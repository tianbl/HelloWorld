package com.eastsoft.pub.uitl.pager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pager implements Serializable {

	private static final long serialVersionUID = -3489256976218350378L;

	// 排序方式
	public enum OrderType {
		asc, desc
	}

	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	private Integer pageNumber = 1;// 当前页码
	private PageIndex pageIndex;// 页码开始索引和结束索引

	private Integer pageSize = 10;// 每页记录数
	private Integer totalCount = 0;// 总记录数
	private Integer pageCount = 0;// 总页数
	private String property;// 查找属性名称
	private String propertyid;// 多层ID

	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, Object> mapbyOnly = new HashMap<String, Object>();

	private String keyword;// 查找关键字
	private String orderBy = "";// 排序字段
	private OrderType orderType = OrderType.asc;// 排序方式
	@SuppressWarnings("rawtypes")
	private List list;// 数据List

	private String sqlcount = null;
	private String sql = null;
//	private Object[] sqlvalues = null;

	public Pager() {
		super();
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	public PageIndex getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(PageIndex pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount++;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
		this.pageIndex = PageIndex
				.getPageIndex(pageSize, pageNumber, pageCount);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPropertyid() {
		return propertyid;
	}

	public void setPropertyid(String propertyid) {
		this.propertyid = propertyid;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Map<String, Object> getMapbyOnly() {
		return mapbyOnly;
	}

	public void setMapbyOnly(Map<String, Object> mapbyOnly) {
		this.mapbyOnly = mapbyOnly;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}

	public String getSqlcount() {
		return sqlcount;
	}

	public void setSqlcount(String sqlcount) {
		this.sqlcount = sqlcount;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

/*	public Object[] getSqlvalues() {
		return sqlvalues;
	}

	public void setSqlvalues(Object[] sqlvalues) {
		this.sqlvalues = sqlvalues;
	}
*/
	public int getOffset() {
		return (pageNumber - 1) * pageSize;
	}

	public int getLimit() {
		return pageSize;
	}
}
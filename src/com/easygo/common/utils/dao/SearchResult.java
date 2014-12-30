package com.easygo.common.utils.dao;

import java.util.List;

public class SearchResult<T> extends QueryResult<T> {
	private Integer pageSize;
	private Integer pageNum;
	private Integer totalPage;
	private Integer totalCount;

	public SearchResult(List<T> resultList, Class<T> type, 
			int pageSize, int pageNum, int totalPage, int totalCount) {
		super(resultList, type);
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
	}
	
	@Override
	public int totalCount() {
		return totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}
}

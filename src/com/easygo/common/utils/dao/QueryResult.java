package com.easygo.common.utils.dao;

import java.util.List;

import org.springframework.util.Assert;

public class QueryResult<T> {
	private List<T> resultList;

	public QueryResult(List<T> resultList) {
		Assert.notNull(resultList);
		this.resultList = resultList;
	}
	
	public List<T> getResultList() {
		return resultList;
	}
}

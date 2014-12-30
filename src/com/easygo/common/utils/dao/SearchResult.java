package com.easygo.common.utils.dao;

import java.util.List;

public class SearchResult<T> extends QueryResult<T> {

	public SearchResult(List<T> resultList, Class<T> type) {
		super(resultList, type);
	}

}

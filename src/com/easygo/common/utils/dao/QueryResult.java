package com.easygo.common.utils.dao;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.util.Assert;

public class QueryResult<T> {
	protected List<T> resultList;
	protected Class<T> type;

	public QueryResult(List<T> resultList, Class<T> type) {
		Assert.notNull(resultList);
		this.resultList = resultList;
		this.type = type;
	}
	
	public List<T> getResultList() {
		return resultList;
	}
	
	public int totalCount(){
		return resultList != null ? resultList.size() : 0;
	}
	
	public T[] toArray(){
		int count = totalCount();
		if(count < 1){
			return null;
		}
		
		T[] array = (T[]) Array.newInstance(this.type, count);
		for(int i = 0; i < count; i++){
			array[i] = resultList.get(i);
		}
		
		return array;
	}
	
}

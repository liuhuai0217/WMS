package com._520it.ssh.dao;

import java.util.List;

import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

/**
 * @author Administrator
 *这个时通用的泛型类dao
 * @param <T>
 */
public interface IGenericDAO<T>{
	   void save(T dept);

	    void delete(Long dept);
	   
	    void update(T dept);
	  
	    T get(Long id);

	    List<T> listAll();
	    
	//    PageQuery  queryAndHigh(QueryObject qo);
	    PageQuery<?> queryAndHigh(QueryObject qo);
	    
}

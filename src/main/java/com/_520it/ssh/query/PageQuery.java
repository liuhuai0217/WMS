package com._520it.ssh.query;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Setter@Getter
public class PageQuery<T> {
	private int currentPage;
	private int pageSize;
	private int totalCount;//总条数
	private List<T> pageResult;//结果集
	private int nextPage;//上一页
	private int prevPage;//下一页
	private int totalPage;//总页数
	public PageQuery(int currentPage, int pageSize, List<T> pageResult,
			int totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pageResult = pageResult;
		this.totalCount = totalCount;
		totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		nextPage = currentPage+1<=totalPage?currentPage+1:totalPage;
		prevPage = currentPage-1>=1?currentPage-1:1;
	}
	@Override
	public String toString() {
		return "PageQuery [currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", totalCount=" + totalCount + ", pageResult="
				+ pageResult + ", nextPage=" + nextPage + ", prevPage="
				+ prevPage + ", totalPage=" + totalPage + "]";
	}
	
}

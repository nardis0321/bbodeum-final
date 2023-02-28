package com.bbodeum.dto;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageBean<T> {
	public final static int CNT_PER_PAGE_GROUP=2;
	
	private List<T> list;
	private int totalCnt;
	private int totalPage;
	private int startPage;
	private int endPage;
	private int currentPage;
	private int cntPerPage;
	
	public PageBean(int currentPage, List<T>list, int totalCnt, int cntPerPage) {
		this.currentPage = currentPage;
		this.list = list;
		this.totalCnt = totalCnt;
		this.cntPerPage = cntPerPage;
		totalPage = (int) Math.ceil( (double)totalCnt/cntPerPage);
		startPage = (currentPage-1)/CNT_PER_PAGE_GROUP*CNT_PER_PAGE_GROUP+1;
		endPage = startPage + CNT_PER_PAGE_GROUP -1;
	}
}
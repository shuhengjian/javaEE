package com.musicbar.core.base;

import java.io.Serializable;
/**
 *  分页类
 * @author Administrator
 *
 */
public class Page implements Serializable {
	private int pageSize = 1;//每页显示数
	private int pageIndex;//当前页码
	private int prevIndex;//上一页页码
	private int nextIndex;//下一页页码
	private int totalPage;//总页数
	private int count;//总记录数
	private int firstRow;//当前页第一条记录的位置，从0开始计算
	private int beginPage;//开始页
	private int endPage;//结束一页
	/**
	 * 传入整型页码
	 * @param pageIndex
	 */
	public Page(int pageIndex) {	
		this.pageIndex = pageIndex;
	}
	public Page(int pageIndex,int pageSize) {	
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	public Page(int pageIndex,int pageSize,long count) {	
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		setCount((int)count);
	}
	/**
	 * 传入字符串页码，从前端提交的页码一般都为字符串
	 * @param pageIndex
	 */
	public Page(String pageIndex){
		this(pageIndex != null? Integer.parseInt(pageIndex) : 1);
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		 this.pageSize = pageSize;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	
	public int getPrevIndex() {
		return prevIndex;
	}
	
	public int getNextIndex() {
		return nextIndex;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		//计算总页数
		totalPage = (int)Math.ceil((double)count / pageSize);
		//计算下一页
		this.nextIndex = pageIndex + (pageIndex == totalPage? 0 : 1);
		//计算上一页
		this.prevIndex = pageIndex - (pageIndex == 1? 0 : 1);
		//计算当前页第一条的位置
		this.firstRow = (this.pageIndex -1) *  this.pageSize;
		
		if(pageIndex > 2){
			beginPage = pageIndex - 2;
			
		}else{
			beginPage = 1;
		}
		endPage = beginPage + 4;
		if(endPage > totalPage){
			endPage = totalPage;
			beginPage = endPage - 4;
		}else{
			
		}
		if(beginPage < 1){
			beginPage = 1;
		}
	}
	
	public int getFirstRow() {
		return firstRow;
	}
	public int getBeginPage() {
		return beginPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}

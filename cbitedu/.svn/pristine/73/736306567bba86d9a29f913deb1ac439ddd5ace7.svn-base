package com.creatorblue.cbitedu.core.page;

public class Page implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected int pageSize = 8; // 每页默认10条数据
    protected int currentPage = 1; // 当前页
    protected int totalPages = 0; // 总页数
    protected int totalRows = 0; // 总数据1数
    protected int pageStartRow = 0; // 每页的起始行数
    protected int pageEndRow = 0; // 每页显示数据的终止行数
    protected boolean pagination = true; //是否分页
    boolean hasNextPage = false; // 是否有下一页
    boolean hasPreviousPage = false; // 是否有前一页
    protected String pagedView; // 用于页面显示
    private String sortName; //排序字段
    private String sortOrder; //排序方式
    private int beginPage;
	private int endPage;
	
    public int getBeginPage() {
		return beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	Object obj; //参数对象与返回对象
                //List<Object> resultList; // 返回的结果
                //Map<String,Object>  param;      //查询入参	

    public Page(int rows, int pageSize) {
        this.init(rows, pageSize);
    }

    public Page() {
    }

    /**
     * 初始化分页参数:需要先设置totalRows
     *
     */
    public void init(int rows, int pageSize) {
        this.pageSize = pageSize;

        this.totalRows = rows;

        if ((totalRows % pageSize) == 0) {
            totalPages = totalRows / pageSize;
        } else {
            totalPages = (totalRows / pageSize) + 1;
        }
    }

    public void init(int rows, int pageSize, int currentPage) {
        this.pageSize = pageSize;

        this.totalRows = rows;

        if ((totalRows % pageSize) == 0) {
            totalPages = totalRows / pageSize;
        } else {
            totalPages = (totalRows / pageSize) + 1;
        }

        if (currentPage != 0) {
            gotoPage(currentPage);
        }

        // setPagedView();
    }

    /**
     * 计算当前页的取值范围：pageStartRow和pageEndRow
     *
     */
    private void calculatePage() {
        if ((currentPage - 1) > 0) {
            hasPreviousPage = true;
        } else {
            hasPreviousPage = false;
        }

        if (currentPage >= totalPages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }

        if ((currentPage * pageSize) < totalRows) { // 判断是否为最后一页
            pageEndRow = currentPage * pageSize;
            pageStartRow = pageEndRow - pageSize;
        } else {
            pageEndRow = totalRows;
            pageStartRow = pageSize * (totalPages - 1);
        }
        
        if(currentPage > 2){
        	beginPage = currentPage - 2;
			
		}else{
			beginPage = 1;
		}
        endPage = beginPage + 4;
		if(endPage > totalPages){
			endPage = totalPages;
			beginPage = endPage - 4;
		}else{
			
		}
		if(beginPage < 1){
			beginPage = 1;
		}
    }

    /**
     * 直接跳转到指定页数的页面
     *
     * @param page
     */
    public void gotoPage(int page) {
        currentPage = page;

        calculatePage();
    }

    public String getPagedView() {
        return pagedView;
    }

    /**
     * @return
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @return
     */
    public boolean isHasNextPage() {
        return hasNextPage;
    }

    /**
     * @return
     */
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    /**
     * @return
     */
    public int getPageEndRow() {
        return pageEndRow;
    }

    /**
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @return
     */
    public int getPageStartRow() {
        return pageStartRow;
    }

    /**
     * @return
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @return
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * @param i
     */
    public void setTotalPages(int i) {
        totalPages = i;
    }

    /**
     * @param i
     */
    public void setCurrentPage(int i) {
        currentPage = i;
    }

    /**
     * @param b
     */
    public void setHasNextPage(boolean b) {
        hasNextPage = b;
    }

    /**
     * @param b
     */
    public void setHasPreviousPage(boolean b) {
        hasPreviousPage = b;
    }

    /**
     * @param i
     */
    public void setPageEndRow(int i) {
        pageEndRow = i;
    }

    /**
     * @param i
     */
    public void setPageSize(int i) {
        pageSize = i;
    }

    /**
     * @param i
     */
    public void setPageStartRow(int i) {
        pageStartRow = i;
    }

    /**
     * @param i
     */
    public void setTotalRows(int i) {
        totalRows = i;
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

}

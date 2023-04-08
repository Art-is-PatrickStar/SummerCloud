package com.wsw.summercloud.api.basic;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页对象
 * @Author: wangsongwen
 * @Date: 2023/4/6 23:51
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 6040428852336436531L;

    /**
     * 当前页
     */
    private long currentPage = 1;

    /**
     * 每页数据
     */
    private long pageSize = 10;

    /**
     * 分页查询的结果集
     */
    private List<T> result;

    /**
     * 数据总条数
     */
    private long totalCount = 0;

    /**
     * 总页数
     */
    private long totalPage = 1;

    public PageInfo() {
    }

    public PageInfo(long currentPage, long pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageInfo(long currentPage, long pageSize, long totalCount, List<T> records) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.result = records;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public boolean haveNextPage() {
        return (currentPage - 1) * pageSize + result.size() < totalCount;
    }

    public boolean havePreviousPage() {
        return currentPage > 1;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageSize() {
        return pageSize;
    }

    public long getTotalPage() {
        if (1 == totalPage) {
            setTotalPage();
        }
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalPage() {
        if (pageSize != 0) {
            this.totalPage = (totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize);
        }
    }
}
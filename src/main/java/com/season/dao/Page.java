package com.season.dao;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private static int DEFAULT_PAGE_SIZE = 20;

    private long start; // 当前页第一条数据在List中的位置,从0开始

    private List data; // 当前页中存放的记录,类型一般为List

    private long totalCount; // 总记录数

    private int pageSize = DEFAULT_PAGE_SIZE;

    public Page(){
        this(0,0,DEFAULT_PAGE_SIZE,new ArrayList());
    }

    public Page(long start, long totalSize, int pageSize,List data) {
        this.start = start;
        this.pageSize = pageSize;
        this.totalCount = totalSize;
        this.data = data;
    }

    /**
     * 取每页数据容量.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 取当前页中的记录.
     */
    public List getData() {
        return data;
    }

    /**
     * 取该页当前页码,页码从1开始.
     */
    public long getCurrentPageNo() {
        return start / pageSize + 1;
    }

    /**
     * 取总记录数.
     */
    public long getTotalCount() {
        return this.totalCount;
    }


    /**
     * 取总页数.
     */
    public long getTotalPageCount() {
        return totalCount/(pageSize+1)+1;
    }
    /**
     * 该页是否有下一页.
     */
    public boolean isHasNextPage() {
        return this.getCurrentPageNo() < this.getTotalPageCount();
    }

    /**
     * 该页是否有上一页.
     */
    public boolean isHasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    /**
     * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
     *
     * @see #getStartOfPage(int,int)
     */
    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取任一页第一条数据在数据集的位置.
     *
     * @param pageNo   从1开始的页号
     * @param pageSize 每页记录条数
     * @return 该页第一条数据
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

}
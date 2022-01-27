package com.yunqi.starter.common.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 分页
 * Created by @author JsckChin on 2022/1/27
 */
public class Pagination implements Paginable, Serializable {

    private static final Logger log = LoggerFactory.getLogger(Pagination.class);

    private static final long serialVersionUID = 1L;

    /**
     * 改变这个，当每页大小超过 MAX_FETCH_SIZE 时，这个将是默认的 fetchSize
     */
    public static int DEFAULT_PAGE_SIZE = 10;

    /**
     * 页码
     */
    protected int pageNumber;

    /**
     * 每页几条数据
     */
    protected int pageSize;

    /**
     * 总页数
     */
    protected int pageCount;

    /**
     * 总共几条数据
     */
    protected int recordCount;

    public Pagination() {
        this.pageNumber = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Pagination(int pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        this.pageNumber = pageNumber;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Pagination(int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }


    /**
     * <p>
     *     总记录数
     * </p>
     * @return 总记录数
     */
    @Override
    public int getTotalCount() {
        return recordCount;
    }

    /**
     * <p>
     *     总页数
     * </p>
     *
     * @return  总页数
     */
    @Override
    public int getTotalPage() {
        if (pageCount < 0) {
            pageCount = (int) Math.ceil((double) recordCount / pageSize);
        }
        return pageCount;
    }

    /**
     * <p>
     *     每页记录数
     * </p>
     * @return 每页记录数
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * <p>
     *     当前页号， 从 1 开始
     * </p>
     * @return 当前页号
     */
    @Override
    public int getPageNo() {
        return pageNumber;
    }

    /**
     * <p>
     *     是否第一页
     * </p>
     * @return 是否第一页
     */
    @Override
    public boolean isFirstPage() {
        return pageNumber == 1;
    }

    /**
     * <p>
     *     是否最后一页
     * </p>
     * @return 是否最后一页
     */
    @Override
    public boolean isLastPage() {
        if (pageCount == 0) {
            return true;
        }
        return pageNumber == pageCount;
    }

    /**
     * <p>
     *     返回下页的页号
     * </p>
     * @return 返回下页的页号
     */
    @Override
    public int getNextPage() {
        if (isLastPage()) {
            return pageNumber;
        } else {
            return pageNumber + 1;
        }
    }

    /**
     * <p>
     *     返回上页的页号
     * </p>
     * @return 返回上页的页号
     */
    @Override
    public int getPrePage() {
        if (isFirstPage()) {
            return pageNumber;
        } else {
            return pageNumber - 1;
        }
    }

    @Override
    public Paginable setPageNo(int pageNumber) {
        if (1 > pageNumber && log.isInfoEnabled()) {
            log.info("PageNumber shall start at 1, but input is {}, that mean pager is disable", pageNumber);
        }
        this.pageNumber = pageNumber;
        return this;
    }

    @Override
    public Paginable setPageSize(int pageSize) {
        this.pageSize = (pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE);
        return resetPageCount();
    }

    @Override
    public Paginable setTotalCount(int recordCount) {
        this.recordCount = Math.max(recordCount, 0);
        this.pageCount = (int) Math.ceil((double) recordCount / pageSize);
        return this;
    }

    public Paginable resetPageCount() {
        pageCount = -1;
        return this;
    }

}

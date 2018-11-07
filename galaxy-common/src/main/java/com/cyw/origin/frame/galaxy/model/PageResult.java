package com.cyw.origin.frame.galaxy.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * @author yiwen.chang
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResult<T> extends Page {

    private static final long serialVersionUID = -2710078871095822229L;

    private long totalCount;

    private long totalPage;

    private List<T> results;

    private PageResult() {
    }

    public static <T> PageResult<T> empty(Page page) {
        return build(page, Collections.EMPTY_LIST, 0);
    }

    public static <T> PageResult<T> build(Page page, List<T> results) {
        return build(page, results, -1);
    }

    public static <T> PageResult<T> build(Page page, List<T> results, long totalCount) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNum(page.getPageNum());
        pageResult.setPageSize(page.getPageSize());
        if (page.isCountPage()) {
            pageResult.setTotalCount(totalCount);
            pageResult.setTotalPage((totalCount - 1) / page.getPageSize() + 1);
        }
        pageResult.setResults(results);
        return pageResult;
    }

}
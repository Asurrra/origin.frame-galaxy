package com.cyw.origin.frame.galaxy.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yiwen.chang
 */
@Data
public class Page implements Serializable {

    private static final long serialVersionUID = -3543456697018289887L;

    private long pageSize = 20;// 每页大小，默认是20

    private long pageNum = 1;// 页码，默认是第一页

    private boolean countPage;// 是否统计总页数

    private long startIndex = (pageNum - 1) * pageSize;

    protected Page() {
    }

    public static Page first() {
        return new Page();
    }

    /**
     * @param pageNum 页码 1开始
     * @param pageSize 每页数量
     * @return
     */
    public static Page build(Long pageNum, Long pageSize) {
        return build(pageNum, pageSize, false);
    }

    /**
     * @param pageNum 页码 1开始
     * @param pageSize 每页数量
     * @param countPage 是否统计页数,解析代码可以根据这个参数去判断是否多执行一次count操作
     * @return
     */
    public static Page build(Long pageNum, Long pageSize, boolean countPage) {
        Page page;
        if (pageNum != null) {
            page = new Page();
            page.setPageNum(pageNum);
        } else {
            page = first();
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }
        page.setCountPage(countPage);
        return page;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
        this.startIndex = (this.pageNum - 1) * this.pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
        this.startIndex = (this.pageNum - 1) * this.pageSize;
    }

}

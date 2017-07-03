package com.lanlinker.domain;

import java.util.List;

/**
 * Job视图层对象
 */
public class JobVO {
    private Long total;
    private List<Job> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Job> getRows() {
        return rows;
    }

    public void setRows(List<Job> rows) {
        this.rows = rows;
    }
}

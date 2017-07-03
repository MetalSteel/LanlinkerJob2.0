package com.lanlinker.domain;

import java.util.List;

/**
 * Resume视图层对象
 */
public class ResumeVO {
    private Long total;
    private List<Resume> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Resume> getRows() {
        return rows;
    }

    public void setRows(List<Resume> rows) {
        this.rows = rows;
    }
}

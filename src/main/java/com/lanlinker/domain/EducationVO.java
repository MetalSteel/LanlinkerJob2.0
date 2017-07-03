package com.lanlinker.domain;

import java.util.List;

/**
 * Education视图层对象
 */
public class EducationVO {
    private Long total;
    private List<Education> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Education> getRows() {
        return rows;
    }

    public void setRows(List<Education> rows) {
        this.rows = rows;
    }
}

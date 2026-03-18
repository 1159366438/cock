package com.example.dto;

/**
 * 查询部门请求参数类
 *
 * @author Attendance System Team
 * @since 2026-03-18
 */
public class QueryDepartmentRequest {
    /**
     * 部门名称
     */
    private String name;

    /**
     * 当前页码
     */
    private Long page;

    /**
     * 每页大小
     */
    private Long size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
package com.yjg.ec.platform.erp.web.auth.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class BaseGridVo {

    private String loginName;

    private String providerCode;

    /**
     * 总页数
     */
    private int total = 0;
    /**
     * 当前页
     */
    private int page = 1;
    /**
     * 总记录数
     */
    private int records = 0;
    /**
     * 计算后的每页显示行数
     */
    private int length = 0;
    /**
     * 分页查询开始的记录号
     */
    private int begin = 0;
    /**
     * 排序的字段
     */
    private String sidx;
    /**
     * 排序方法desc/asc
     */
    private String sord;
    /**
     * 操作类型
     */
    private String oper;
    /**
     * 列表数据
     */
    private List jsonList;

    public List getJsonList() {
        return jsonList;
    }

    public void setJsonList(List jsonList) {
        this.jsonList = jsonList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        if (records != 0 && this.getLength() != 0) {
            this.setTotal((records - 1) / this.getLength() + 1);
        }
        this.records = records;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
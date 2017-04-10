package com.yjg.ec.platform.erp.web.auth.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 资源参数vo
 *
 * @author gus
 */
public class ErpResourceParamVo extends BaseGridVo {

    private String name;
    private String path;
    private String description;
    private Integer depth;
    private Integer sort;
    private Integer pid;
    private Integer authority_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(Integer authority_id) {
        this.authority_id = authority_id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

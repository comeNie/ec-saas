package com.yjg.ec.platform.erp.auth.param.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 存储在session中的菜单树
 *
 * @author gus
 */
public class ErpResouceSessionParamDto implements Serializable {

    private static final long serialVersionUID = 3165594101178408904L;

    private Integer id;
    private String name;
    private String url;
    private Integer pid;
    private List<ErpResouceSessionParamDto> children = new ArrayList<ErpResouceSessionParamDto>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<ErpResouceSessionParamDto> getChildren() {
        return children;
    }

    public void setChildren(List<ErpResouceSessionParamDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

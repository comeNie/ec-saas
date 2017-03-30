package com.yjg.ec.platform.api.param.dto;

import java.io.Serializable;

/**
 * 
 * @author aoyasong
 *
 */
public class ParentParamDto implements Serializable {

	private static final long serialVersionUID = -794829391122712111L;

	private static final Integer DEFAULT_PAGE_NUM = 1;

	private static final Integer DEFAULT_PAGE_SIZE = 5;
	/**
	 */
	protected Integer pageNum;

	/**
	 */
	protected Integer pageSize;

	protected Long offset;

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Integer getPageNum() {
		return pageNum == null || pageNum < 1 ? DEFAULT_PAGE_NUM : pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}

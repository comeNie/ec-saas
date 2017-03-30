package com.yjg.ec.platform.common.constant;

public final class ReserveConstants {
	
	private ReserveConstants(){
		
	}
	
	/**
	 * 成功信息
	 */
	public static final String RESVERVE_SUCCESS_MESSAGE = "1";
	
	/**
	 * 失败信息
	 */
	public static final String RESVERVE_FAILURE_MESSAGE = "0";
	
	/**
	 * 预约已满
	 */
	public static final String RESVERVE_FAILURE_FULL_MESSAGE = "手慢了，别人提前�?步预约了";
	
	
	
	/**
	 * 预约状�?�待处理
	 */
	public static final Integer RESVERVE_STATUS_PROCESS = 0;
	
	/**
	 * 预约状�?�已结束
	 */
	public static final Integer RESVERVE_STATUS_OVER = 1;
	
	
	/**
	 * 预约状�?�待处理
	 */
	public static final Integer VSIIT_IS_MORNING = 1;
	
	/**
	 * 预约状�?�已结束
	 */
	public static final Integer VSIIT_IS_AFTERNOON = 1;
	
	
	/**
	 * 患�?�可见预约天�?
	 */
	public static final int PATIENT_VIEW_RESERVE_DAYS = 21;
	
}

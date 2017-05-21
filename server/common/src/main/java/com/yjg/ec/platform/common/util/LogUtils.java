package com.yjg.ec.platform.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yjg.ec.platform.common.constant.BuriedPointConstants;
import com.yjg.ec.platform.common.domain.LogInfo;

public final class LogUtils {
	private LogUtils() {

	}

	private static final Logger LOGGER = LoggerFactory.getLogger(BuriedPointConstants.BURIED_POINT);

	public static void buriedLogById(LogInfo logInfo) {
		LOGGER.info("[usertype:" + logInfo.getUserType() + "-userid:" + logInfo.getUserId() + "-"
				+ logInfo.getModelName() + "]" + logInfo.getDesc());
	}

	public static void buriedLogByName(LogInfo logInfo) {
		LOGGER.info("[usertype:" + logInfo.getUserType() + "-loginName:" + logInfo.getLoginName() + "-"
				+ logInfo.getModelName() + "]" + logInfo.getDesc());
	}

}

package com.yjg.ec.platform.common.util;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yjg.ec.platform.common.id.IDGenerator;

@Component
public final class IDGenerateUtil {
	
	@Resource(name="iPIdGenerator")
	private IDGenerator iPIdGenerator;
	
	public static String newUUID(){  
        return UUID.randomUUID().toString();  
    }
	
	public long generateID(){
		return iPIdGenerator.generateId().longValue();
	}

}

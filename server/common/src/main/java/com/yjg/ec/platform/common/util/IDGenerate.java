package com.yjg.ec.platform.common.util;

import java.util.UUID;

public class IDGenerate {
	
	public static String newUUID(){  
        return UUID.randomUUID().toString();  
    }
	
	public static long generateID(){
	    byte[] guidArray = newUUID().getBytes();
	    
	    
	    return 0;
	}

}

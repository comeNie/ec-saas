package com.yjg.ec.platform.erp.web.auth.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;

public class BaseHandler {

	@Resource(name="dozerMapper")
	protected Mapper dozerMapper;
	
	public <T> List<T> mapList(Collection sourceList, Class<T> destinationClass)  
	  {  
	    List destinationList = new ArrayList();  
	    for (Iterator i$ = sourceList.iterator(); i$.hasNext(); ) { Object sourceObject = i$.next();  
	      Object destinationObject = dozerMapper.map(sourceObject, destinationClass);  
	      destinationList.add(destinationObject);  
	    }  
	    return destinationList;  
	  }  
	
	
}

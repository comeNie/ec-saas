package com.yjg.ec.platform.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by zhangyunfei on 27/11/2016.
 */
public class ParamException extends RuntimeException {

    private String message;

    public ParamException(Errors errors) {
        if (errors != null && errors.hasErrors()) {
            StringBuffer errorMessage = new StringBuffer();
            List<ObjectError> oeList = errors.getAllErrors();
            for (int i = 0; i < oeList.size(); i++) {
                ObjectError oe = oeList.get(i);
                errorMessage.append(oe.getDefaultMessage());
                if (i != oeList.size()-1) {
                    errorMessage.append(",");
                }
            }
            message = errorMessage.toString();
        } else {
            message = "";
        }

    }

    public ParamException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

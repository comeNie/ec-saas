package com.yjg.ec.platform.common.constant;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangyunfei on 06/01/2017.
 */
public class PatientConstants {

    public static final Date MIN_BIRTHDAY;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1940, 1 ,1);
        MIN_BIRTHDAY = calendar.getTime();
    }

}

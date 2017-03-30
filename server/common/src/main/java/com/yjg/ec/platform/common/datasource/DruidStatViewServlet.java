package com.yjg.ec.platform.common.datasource;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by zhangyunfei on 30/12/2016.
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams={
                @WebInitParam(name="allow",value=""),// IP白名�? (没有配置或�?�为空，则允许所有访�?)
                @WebInitParam(name="deny",value=""),// IP黑名�? (存在共同时，deny优先于allow)
                @WebInitParam(name="loginUsername",value="nrb"),// 用户�?
                @WebInitParam(name="loginPassword",value="nanrenbang"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功�?
        })
public class DruidStatViewServlet extends StatViewServlet {

}
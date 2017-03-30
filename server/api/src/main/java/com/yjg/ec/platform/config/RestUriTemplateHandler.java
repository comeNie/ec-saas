package com.yjg.ec.platform.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriTemplateHandler;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by zhangyunfei on 22/12/2016.
 */
@Component
public class RestUriTemplateHandler extends DefaultUriTemplateHandler {

    @Value("${rest.address}")
    private String address;

    @Override
    protected UriComponentsBuilder initUriComponentsBuilder(String uriTemplate) {
        if (StringUtils.isNotBlank(address) && StringUtils.isNotBlank(uriTemplate)) {
            if (address.endsWith("/") && uriTemplate.startsWith("/")) {
                uriTemplate = StringUtils.removeEnd(address, "/") + uriTemplate;
            } else if (!address.endsWith("/") && !uriTemplate.startsWith("/")) {
                uriTemplate = address + "/" + uriTemplate;
            } else {
                uriTemplate = address + uriTemplate;
            }
        }
        return super.initUriComponentsBuilder(uriTemplate);
    }
}

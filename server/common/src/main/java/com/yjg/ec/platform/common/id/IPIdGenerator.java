package com.yjg.ec.platform.common.id;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

@Component
public class IPIdGenerator implements IDGenerator {

	private final CommonSelfIdGenerator commonSelfIdGenerator = new CommonSelfIdGenerator();

    static {
        initWorkerId();
    }

    static void initWorkerId() {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        byte[] ipAddressByteArray = address.getAddress();
        CommonSelfIdGenerator.setWorkerId((long) (((ipAddressByteArray[ipAddressByteArray.length - 2] & 0B11) << Byte.SIZE) + (ipAddressByteArray[ipAddressByteArray.length - 1] & 0xFF)));
    }

    @Override
    public Number generateId() {
        return commonSelfIdGenerator.generateId();
    }

}

package com.yjg.ec.platform.encryption;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by zhangyunfei on 02/12/2016.
 */
@Configuration
public class PassEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    public static void main(String args[]) {
        System.out.println( new StandardPasswordEncoder().encode("123456"));
    }

}

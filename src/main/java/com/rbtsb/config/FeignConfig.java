package com.rbtsb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.Response;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Slf4j
public class FeignConfig {

    private final ObjectMapper objectMapper;

    public FeignConfig() {
        objectMapper = new ObjectMapper();
    }

    @Bean
    @Primary
    @Scope(SCOPE_PROTOTYPE)
    Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new FormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    public class CustomErrorDecoder implements ErrorDecoder {

        private final ErrorDecoder defaultErrorDecoder = new Default();

        @Override
        public Exception decode(String methodKey, Response response) {
            return defaultErrorDecoder.decode(methodKey, response);
        }
    }
}

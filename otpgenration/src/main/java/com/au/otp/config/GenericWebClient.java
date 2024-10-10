package com.au.otp.config;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.au.otp.exception.CustomTimeoutException;
import com.au.otp.payload.util.OTPData;


import io.netty.handler.timeout.ReadTimeoutException;

import java.net.ConnectException;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Slf4j
@Configuration
public class GenericWebClient {

	private static final Logger log = LoggerFactory.getLogger(GenericWebClient.class);
	
    @Autowired
    private WebClient webClient;

    @Autowired
    private OTPData otpData;

    public <K, T> K post(String url, T request, Class<K> clazz) {
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        log.info("Inside generic webclient request: {} response type: {} URL: {}", request, clazz, uri);

        long requestTime = System.currentTimeMillis();
        log.info("API Request Time: {}", requestTime);

        K response = webClient.post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(clazz)
                .timeout(Duration.ofSeconds(Long.valueOf(otpData.getConnectionTimeOut()))) // timeout
                .onErrorMap(ReadTimeoutException.class, ex -> new CustomTimeoutException("Read Timeout"))
                .onErrorMap(ConnectException.class, ex -> new CustomTimeoutException("Connection Exception"))
                .onErrorMap(TimeoutException.class, ex -> new CustomTimeoutException("No response from Worldline"))
                .block();

        log.info("Response from ESB in: {} milliseconds", System.currentTimeMillis() - requestTime);
        log.info("Generic webClient response: {}", response);

        return response;
    }
}

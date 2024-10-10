package com.au.otp.config;

import java.time.Duration;

import javax.net.ssl.SSLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfiguration { 
	@Bean 
	WebClient webclient() throws SSLException { 
		SslContext sslContext = SslContextBuilder 
				.forClient() 
				.trustManager(InsecureTrustManagerFactory.INSTANCE) 
				.build();
                 ConnectionProvider provider =ConnectionProvider.builder("custom")
				.maxConnections(50)
				.maxIdleTime(Duration.ofSeconds(20))
				.maxLifeTime(Duration.ofSeconds(60))
				.pendingAcquireTimeout(Duration.ofSeconds(60))
				.evictInBackground(Duration.ofSeconds(120)) 
				.build(); HttpClient httpClient = HttpClient.create(provider).secure(t -> t.sslContext(sslContext)); return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
	}
	}
package com.soni.device.connector.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app.websocket")
public class WebSocketProperties {
    /**
     * Prefix used for WebSocket destination mappings
     */
    private String applicationPrefix = "/ws";
    /**
     * Prefix used by topics
     */
    private String topicPrefix = "/topic";
    /**
     * Endpoint that can be used to connect to
     */
    private String endpoint = "/live-connect";
    /**
     * Allowed origins
     */
    private String[] allowedOrigins = new String[0];
	public String getEndpoint() {
		// TODO Auto-generated method stub
		return endpoint;
	}
}

package com.userinfoservice.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserUsageRequest {

    @JsonProperty(value = "userId", required = true)
    @NotNull
	private String userId;
	
    @JsonProperty(value = "type", required = true)
    @NotNull
	private String type;
	
    @JsonProperty(value = "startTime", required = true)
    @NotNull
	private String startTime;
     
}

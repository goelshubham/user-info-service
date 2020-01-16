package com.userinfoservice.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UsageRequest {

    @JsonProperty(value = "userId", required = true)
    @NotNull
	private String userId;
	
    @JsonProperty(value = "usageType", required = true)
    @NotNull
	private String usageType;
	
    @JsonProperty(value = "usageTime", required = true)
    @NotNull
	private String usageTime;
     
}

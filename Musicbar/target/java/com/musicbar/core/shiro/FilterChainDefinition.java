package com.musicbar.core.shiro;

import java.util.Map;

public interface FilterChainDefinition {
	public Map<String,String> getFilterChainDefinitionMap();
	
	public Map<String,String> getUrlMap();
}

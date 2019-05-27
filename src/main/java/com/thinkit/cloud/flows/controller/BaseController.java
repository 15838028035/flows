package com.thinkit.cloud.flows.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.thinkit.cloud.flows.util.JwtUtil;

/**
 * 
 * 基类
 *
 */
public class BaseController {

	public Long getLoginId(HttpServletRequest request) {
		Map<String, Object> map = JwtUtil.validateTokenAndGetClaims(request);
		return Long.valueOf((String) map.get("USER_ID"));
	}

	public String getUserName(HttpServletRequest request) {
		Map<String, Object> map = JwtUtil.validateTokenAndGetClaims(request);
		return (String) map.get("USER_NAME");
	}

	/**
	 * 获取文件拓展名
	 * 
	 */
	public static String getExt(String path) {

		if (path == null || path.equals("") || !path.contains(".")) {
			return null;
		} else {
			return path.substring(path.lastIndexOf('.') + 1, path.length());
		}

	}

}

package com.thinkit.cloud.flows.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkit.cloud.flows.bean.UpmUser;
import com.thinkit.cloud.flows.util.JwtUtil;
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 管理
 */
@Api(value = "服务", tags = "服务接口")
@RestController()
public class UpmUserLoginController extends BaseController {

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@ApiOperation(value = "登录")
	@PostMapping(value = "/api/UpmUser/login")
	public RestAPIResult2 login( UpmUser upmUser, HttpServletRequest request, RedirectAttributes attributes) {

		RestAPIResult2 restAPIResult = new RestAPIResult2();
		restAPIResult.setRespCode(1);
		restAPIResult.setRespMsg("登录成功");

		if (upmUser.getUserNo() == null || "".equals(upmUser.getUserNo()) || upmUser.getUserPass() == null
				|| "".equals(upmUser.getUserPass())) {
			restAPIResult.setRespCode(0);
			restAPIResult.setRespMsg("参数为空");
			return restAPIResult;
		}
		

		if (!"admin".equals(upmUser.getUserNo()) && !"123456".equals(upmUser.getUserPass())) {
			restAPIResult.setRespCode(0);
			restAPIResult.setRespMsg("用户名或密码输入错误");
		} else {

			String token = JwtUtil.generateToken("admin", "1",60000L);
			restAPIResult.setToken(token);
			restAPIResult.setDataCode(String.valueOf(60000));
		}

		return restAPIResult;
	}

}

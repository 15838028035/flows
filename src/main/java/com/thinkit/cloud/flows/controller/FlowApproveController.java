package com.thinkit.cloud.flows.controller;

import com.thinkit.cloud.flows.bean.FlowApprove;
import com.thinkit.cloud.flows.service.FlowApproveService;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *流程审批表管理
 */
@Api(value = "流程审批表服务", tags = "流程审批表服务接口")
@RestController()
public class FlowApproveController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlowApproveService flowApproveService;
	
	@ApiOperation(value = "分页列表")
	@GetMapping(value = "/api/FlowApprove")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  flowApproveService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/FlowApprove")
		public RestAPIResult2 create(@ModelAttribute FlowApprove flowApprove,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					flowApprove.setCreateUserId(createBy);
					flowApprove.setCreateUserName(getUserName(request));
					flowApprove.setCreateTime(new Date());
					flowApproveService.insertSelective(flowApprove);
					
				}catch(Exception e) {
					logger.error("[流程审批表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/FlowApprove/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute FlowApprove flowApprove,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					flowApprove.setUpdateUserId(createBy);
					flowApprove.setUpdateUserName(getUserName(request));
					flowApprove.setUpdateTime(new Date());
					flowApproveService.updateByPrimaryKeySelective(flowApprove);
					
				}catch(Exception e) {
					logger.error("[流程审批表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/FlowApprove/{id}")
	public FlowApprove show(@PathVariable("id") java.lang.Long id )  {
		FlowApprove flowApprove =flowApproveService.selectByPrimaryKey(id);
		if(flowApprove== null) {
			flowApprove = new FlowApprove();
		}
		return flowApprove;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/FlowApprove/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		flowApproveService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/FlowApprove/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		FlowApprove flowApprove =flowApproveService.selectByPrimaryKey(id);
		if(flowApprove== null) {
			flowApprove = new FlowApprove();
		}
		
		retMap.put("flowApprove", flowApprove);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/FlowApprove/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<FlowApprove> list = flowApproveService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}


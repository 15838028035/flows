package com.thinkit.cloud.flows.controller;

import com.thinkit.cloud.flows.bean.FlowTaskActorHist;
import com.thinkit.cloud.flows.service.FlowTaskActorHistService;


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
 *流程任务参与者历史管理
 */
@Api(value = "流程任务参与者历史服务", tags = "流程任务参与者历史服务接口")
@RestController()
public class FlowTaskActorHistController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlowTaskActorHistService flowTaskActorHistService;
	
	@ApiOperation(value = "分页列表")
	@GetMapping(value = "/api/FlowTaskActorHist")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  flowTaskActorHistService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/FlowTaskActorHist")
		public RestAPIResult2 create(@ModelAttribute FlowTaskActorHist flowTaskActorHist,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					flowTaskActorHist.setCreateUserId(createBy);
					flowTaskActorHist.setCreateUserName(getUserName(request));
					flowTaskActorHist.setCreateTime(new Date());
					flowTaskActorHistService.insertSelective(flowTaskActorHist);
					
				}catch(Exception e) {
					logger.error("[流程任务参与者历史]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/FlowTaskActorHist/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute FlowTaskActorHist flowTaskActorHist,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					flowTaskActorHist.setUpdateUserId(createBy);
					flowTaskActorHist.setUpdateUserName(getUserName(request));
					flowTaskActorHist.setUpdateTime(new Date());
					flowTaskActorHistService.updateByPrimaryKeySelective(flowTaskActorHist);
					
				}catch(Exception e) {
					logger.error("[流程任务参与者历史]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/FlowTaskActorHist/{id}")
	public FlowTaskActorHist show(@PathVariable("id") java.lang.Long id )  {
		FlowTaskActorHist flowTaskActorHist =flowTaskActorHistService.selectByPrimaryKey(id);
		if(flowTaskActorHist== null) {
			flowTaskActorHist = new FlowTaskActorHist();
		}
		return flowTaskActorHist;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/FlowTaskActorHist/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		flowTaskActorHistService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/FlowTaskActorHist/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		FlowTaskActorHist flowTaskActorHist =flowTaskActorHistService.selectByPrimaryKey(id);
		if(flowTaskActorHist== null) {
			flowTaskActorHist = new FlowTaskActorHist();
		}
		
		retMap.put("flowTaskActorHist", flowTaskActorHist);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/FlowTaskActorHist/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<FlowTaskActorHist> list = flowTaskActorHistService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}


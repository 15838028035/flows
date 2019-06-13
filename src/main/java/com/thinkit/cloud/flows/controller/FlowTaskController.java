package com.thinkit.cloud.flows.controller;

import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.service.FlowTaskService;


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
 *流程任务表管理
 */
@Api(value = "流程任务表服务", tags = "流程任务表服务接口")
@RestController()
public class FlowTaskController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlowTaskService flowTaskService;
	
	@ApiOperation(value = "分页列表")
	@GetMapping(value = "/api/FlowTask")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  flowTaskService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/FlowTask")
		public RestAPIResult2 create(@ModelAttribute FlowTask flowTask,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					flowTask.setCreateUserId(createBy);
					flowTask.setCreateUserName(getUserName(request));
					flowTask.setCreateTime(new Date());
					flowTaskService.insertSelective(flowTask);
					
				}catch(Exception e) {
					logger.error("[流程任务表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/FlowTask/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute FlowTask flowTask,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					flowTask.setUpdateUserId(createBy);
					flowTask.setUpdateUserName(getUserName(request));
					flowTask.setUpdateTime(new Date());
					flowTaskService.updateByPrimaryKeySelective(flowTask);
					
				}catch(Exception e) {
					logger.error("[流程任务表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/FlowTask/{id}")
	public FlowTask show(@PathVariable("id") java.lang.Long id )  {
		FlowTask flowTask =flowTaskService.selectByPrimaryKey(id);
		if(flowTask== null) {
			flowTask = new FlowTask();
		}
		return flowTask;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/FlowTask/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		flowTaskService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/FlowTask/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		FlowTask flowTask =flowTaskService.selectByPrimaryKey(id);
		if(flowTask== null) {
			flowTask = new FlowTask();
		}
		
		retMap.put("flowTask", flowTask);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/FlowTask/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<FlowTask> list = flowTaskService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}


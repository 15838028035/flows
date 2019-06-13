package com.thinkit.cloud.flows.controller;

import com.thinkit.cloud.flows.bean.FlowTaskActor;
import com.thinkit.cloud.flows.service.FlowTaskActorService;


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
 *流程任务参与者管理
 */
@Api(value = "流程任务参与者服务", tags = "流程任务参与者服务接口")
@RestController()
public class FlowTaskActorController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlowTaskActorService flowTaskActorService;
	
	@ApiOperation(value = "分页列表")
	@GetMapping(value = "/api/FlowTaskActor")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  flowTaskActorService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/FlowTaskActor")
		public RestAPIResult2 create(@ModelAttribute FlowTaskActor flowTaskActor,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					flowTaskActor.setCreateUserId(createBy);
					flowTaskActor.setCreateUserName(getUserName(request));
					flowTaskActor.setCreateTime(new Date());
					flowTaskActorService.insertSelective(flowTaskActor);
					
				}catch(Exception e) {
					logger.error("[流程任务参与者]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/FlowTaskActor/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute FlowTaskActor flowTaskActor,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					flowTaskActor.setUpdateUserId(createBy);
					flowTaskActor.setUpdateUserName(getUserName(request));
					flowTaskActor.setUpdateTime(new Date());
					flowTaskActorService.updateByPrimaryKeySelective(flowTaskActor);
					
				}catch(Exception e) {
					logger.error("[流程任务参与者]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/FlowTaskActor/{id}")
	public FlowTaskActor show(@PathVariable("id") java.lang.Long id )  {
		FlowTaskActor flowTaskActor =flowTaskActorService.selectByPrimaryKey(id);
		if(flowTaskActor== null) {
			flowTaskActor = new FlowTaskActor();
		}
		return flowTaskActor;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/FlowTaskActor/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		flowTaskActorService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/FlowTaskActor/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		FlowTaskActor flowTaskActor =flowTaskActorService.selectByPrimaryKey(id);
		if(flowTaskActor== null) {
			flowTaskActor = new FlowTaskActor();
		}
		
		retMap.put("flowTaskActor", flowTaskActor);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/FlowTaskActor/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<FlowTaskActor> list = flowTaskActorService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}


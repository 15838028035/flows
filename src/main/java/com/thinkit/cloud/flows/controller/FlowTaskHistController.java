package com.thinkit.cloud.flows.controller;

import com.thinkit.cloud.flows.bean.FlowTaskHist;
import com.thinkit.cloud.flows.service.FlowTaskHistService;


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
 *流程任务历史表管理
 */
@Api(value = "流程任务历史表服务", tags = "流程任务历史表服务接口")
@RestController()
public class FlowTaskHistController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlowTaskHistService flowTaskHistService;
	
	@ApiOperation(value = "分页列表")
	@GetMapping(value = "/api/FlowTaskHist")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  flowTaskHistService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/FlowTaskHist")
		public RestAPIResult2 create(@ModelAttribute FlowTaskHist flowTaskHist,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					flowTaskHist.setCreateUserId(createBy);
					flowTaskHist.setCreateUserName(getUserName(request));
					flowTaskHist.setCreateTime(new Date());
					flowTaskHistService.insertSelective(flowTaskHist);
					
				}catch(Exception e) {
					logger.error("[流程任务历史表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/FlowTaskHist/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute FlowTaskHist flowTaskHist,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					flowTaskHist.setUpdateUserId(createBy);
					flowTaskHist.setUpdateUserName(getUserName(request));
					flowTaskHist.setUpdateTime(new Date());
					flowTaskHistService.updateByPrimaryKeySelective(flowTaskHist);
					
				}catch(Exception e) {
					logger.error("[流程任务历史表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/FlowTaskHist/{id}")
	public FlowTaskHist show(@PathVariable("id") java.lang.Long id )  {
		FlowTaskHist flowTaskHist =flowTaskHistService.selectByPrimaryKey(id);
		if(flowTaskHist== null) {
			flowTaskHist = new FlowTaskHist();
		}
		return flowTaskHist;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/FlowTaskHist/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		flowTaskHistService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/FlowTaskHist/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		FlowTaskHist flowTaskHist =flowTaskHistService.selectByPrimaryKey(id);
		if(flowTaskHist== null) {
			flowTaskHist = new FlowTaskHist();
		}
		
		retMap.put("flowTaskHist", flowTaskHist);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/FlowTaskHist/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<FlowTaskHist> list = flowTaskHistService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}


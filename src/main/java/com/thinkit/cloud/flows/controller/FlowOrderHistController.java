package com.thinkit.cloud.flows.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.thinkit.cloud.flows.bean.FlowOrderHist;
import com.thinkit.cloud.flows.service.FlowOrderHistService;
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *流程实例历史表管理
 */
@Api(value = "流程实例历史表服务", tags = "流程实例历史表服务接口")
@RestController()
public class FlowOrderHistController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlowOrderHistService flowOrderHistService;
	
	@ApiOperation(value = "分页列表")
	@GetMapping(value = "/api/FlowOrderHist")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  flowOrderHistService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/FlowOrderHist")
		public RestAPIResult2 create(@ModelAttribute FlowOrderHist flowOrderHist,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					flowOrderHist.setCreateUserId(createBy);
					flowOrderHist.setCreateUserName(getUserName(request));
					flowOrderHist.setCreateTime(new Date());
					flowOrderHistService.insertSelective(flowOrderHist);
					
				}catch(Exception e) {
					logger.error("[流程实例历史表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/FlowOrderHist/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute FlowOrderHist flowOrderHist,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					flowOrderHist.setUpdateUserId(createBy);
					flowOrderHist.setUpdateUserName(getUserName(request));
					flowOrderHist.setUpdateTime(new Date());
					flowOrderHistService.updateByPrimaryKeySelective(flowOrderHist);
					
				}catch(Exception e) {
					logger.error("[流程实例历史表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/FlowOrderHist/{id}")
	public FlowOrderHist show(@PathVariable("id") java.lang.Long id )  {
		FlowOrderHist flowOrderHist =flowOrderHistService.selectByPrimaryKey(id);
		if(flowOrderHist== null) {
		//	flowOrderHist = new FlowOrderHist();
		}
		return flowOrderHist;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/FlowOrderHist/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		flowOrderHistService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/FlowOrderHist/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		FlowOrderHist flowOrderHist =flowOrderHistService.selectByPrimaryKey(id);
		if(flowOrderHist== null) {
		//	flowOrderHist = new FlowOrderHist();
		}
		
		retMap.put("flowOrderHist", flowOrderHist);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/FlowOrderHist/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<FlowOrderHist> list = flowOrderHistService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}


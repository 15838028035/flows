package com.thinkit.cloud.flows.controller;

import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.service.FlowOrderService;


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
 *流程实例表管理
 */
@Api(value = "流程实例表服务", tags = "流程实例表服务接口")
@RestController()
public class FlowOrderController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlowOrderService flowOrderService;
	
	@ApiOperation(value = "分页列表")
	@GetMapping(value = "/api/FlowOrder")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  flowOrderService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/FlowOrder")
		public RestAPIResult2 create(@ModelAttribute FlowOrder flowOrder,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					flowOrder.setCreateUserId(createBy);
					flowOrder.setCreateUserName(getUserName(request));
					flowOrder.setCreateTime(new Date());
					flowOrderService.insertSelective(flowOrder);
					
				}catch(Exception e) {
					logger.error("[流程实例表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/FlowOrder/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute FlowOrder flowOrder,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					flowOrder.setUpdateUserId(createBy);
					flowOrder.setUpdateUserName(getUserName(request));
					flowOrder.setUpdateTime(new Date());
					flowOrderService.updateByPrimaryKeySelective(flowOrder);
					
				}catch(Exception e) {
					logger.error("[流程实例表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/FlowOrder/{id}")
	public FlowOrder show(@PathVariable("id") java.lang.Long id )  {
		FlowOrder flowOrder =flowOrderService.selectByPrimaryKey(id);
		if(flowOrder== null) {
			flowOrder = new FlowOrder();
		}
		return flowOrder;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/FlowOrder/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		flowOrderService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/FlowOrder/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		FlowOrder flowOrder =flowOrderService.selectByPrimaryKey(id);
		if(flowOrder== null) {
			flowOrder = new FlowOrder();
		}
		
		retMap.put("flowOrder", flowOrder);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/FlowOrder/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<FlowOrder> list = flowOrderService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}


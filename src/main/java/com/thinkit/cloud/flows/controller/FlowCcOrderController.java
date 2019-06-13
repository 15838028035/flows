package com.thinkit.cloud.flows.controller;

import com.thinkit.cloud.flows.bean.FlowCcOrder;
import com.thinkit.cloud.flows.service.FlowCcOrderService;


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
 *流程抄送实例表管理
 */
@Api(value = "流程抄送实例表服务", tags = "流程抄送实例表服务接口")
@RestController()
public class FlowCcOrderController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlowCcOrderService flowCcOrderService;
	
	@ApiOperation(value = "分页列表")
	@GetMapping(value = "/api/FlowCcOrder")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  flowCcOrderService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/FlowCcOrder")
		public RestAPIResult2 create(@ModelAttribute FlowCcOrder flowCcOrder,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					flowCcOrder.setCreateUserId(createBy);
					flowCcOrder.setCreateUserName(getUserName(request));
					flowCcOrder.setCreateTime(new Date());
					flowCcOrderService.insertSelective(flowCcOrder);
					
				}catch(Exception e) {
					logger.error("[流程抄送实例表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/FlowCcOrder/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute FlowCcOrder flowCcOrder,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					flowCcOrder.setUpdateUserId(createBy);
					flowCcOrder.setUpdateUserName(getUserName(request));
					flowCcOrder.setUpdateTime(new Date());
					flowCcOrderService.updateByPrimaryKeySelective(flowCcOrder);
					
				}catch(Exception e) {
					logger.error("[流程抄送实例表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/FlowCcOrder/{id}")
	public FlowCcOrder show(@PathVariable("id") java.lang.Long id )  {
		FlowCcOrder flowCcOrder =flowCcOrderService.selectByPrimaryKey(id);
		if(flowCcOrder== null) {
			flowCcOrder = new FlowCcOrder();
		}
		return flowCcOrder;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/FlowCcOrder/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		flowCcOrderService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/FlowCcOrder/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		FlowCcOrder flowCcOrder =flowCcOrderService.selectByPrimaryKey(id);
		if(flowCcOrder== null) {
			flowCcOrder = new FlowCcOrder();
		}
		
		retMap.put("flowCcOrder", flowCcOrder);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/FlowCcOrder/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<FlowCcOrder> list = flowCcOrderService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}


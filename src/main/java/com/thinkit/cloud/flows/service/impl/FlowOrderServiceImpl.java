package com.thinkit.cloud.flows.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.flows.FlowConstains;
import com.thinkit.cloud.flows.api.FlowTaskServiceApi;
import com.thinkit.cloud.flows.bean.FlowCcOrder;
import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowOrderHist;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.bean.FlowTaskHist;
import com.thinkit.cloud.flows.dao.FlowOrderMapper;
import com.thinkit.cloud.flows.model.ProcessModel;
import com.thinkit.cloud.flows.service.FlowCcOrderService;
import com.thinkit.cloud.flows.service.FlowCompletionService;
import com.thinkit.cloud.flows.service.FlowEngine;
import com.thinkit.cloud.flows.service.FlowOrderHistService;
import com.thinkit.cloud.flows.service.FlowOrderService;
import com.thinkit.cloud.flows.service.FlowTaskActorHistService;
import com.thinkit.cloud.flows.service.FlowTaskActorService;
import com.thinkit.cloud.flows.service.FlowTaskHistService;
import com.thinkit.cloud.flows.service.FlowTaskService;
import com.thinkit.cloud.flows.util.Assert;
import com.thinkit.cloud.flows.util.JsonUtil;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.DateUtil;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

@Service
public class FlowOrderServiceImpl  implements FlowOrderService{
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private FlowEngineFacetsServiceImpl flowEngineFacets;

    @Autowired
    private FlowOrderHistService flowOrderHistService;
    /**
     * 任务业务类
     */
    @Autowired
    protected FlowTaskService flowTaskService;

    @Autowired
    private FlowTaskServiceApi flowTaskServiceApi;

    @Autowired
    private FlowCompletionService flowCompletionService;

    @Autowired
    private FlowTaskActorService flowTaskActorService;

    @Autowired
    private FlowTaskHistService flowTaskHistService;

    @Autowired
    private FlowCcOrderService flowCcorderService;

    @Autowired
    private FlowTaskActorHistService flowTaskActorHistService;
    
	@Autowired
	private FlowOrderMapper flowOrderMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return flowOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(FlowOrder flowOrder){
		return flowOrderMapper.insert(flowOrder);
	}

	@Override
	public java.lang.Long insertSelective(FlowOrder flowOrder) {
		return flowOrderMapper.insertSelective(flowOrder);
	}

	@Override
	public FlowOrder selectByPrimaryKey(java.lang.Long id) {
		return flowOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(FlowOrder flowOrder) {
		return flowOrderMapper.updateByPrimaryKeySelective(flowOrder);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(FlowOrder flowOrder) {
		return flowOrderMapper.updateByPrimaryKey(flowOrder);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = flowOrderMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<FlowOrder> selectByExample(Query query) {
		return flowOrderMapper.selectByExample(query);
	}

	/**
  *
  * @param process
  *          流程定义
  * @param operator
  *          操作人
  * @param args
  *          流程变量
  * @param parentId
  *          父Id
  * @param parentNodeName
  *          父节点名称
  * @return 流程实例
  */
 public FlowOrder createFlowOrder(FlowProcess process, String operator,
     Map<String, Object> args, String parentId, String parentNodeName) throws Exception {
   FlowOrder flowOrder = new FlowOrder();

   flowOrder.setFlowProcessId(process.getId());
   flowOrder.setParentId(Long.parseLong(parentId));
   flowOrder.setParentNodeName(parentNodeName);
   flowOrder.setVariable(JsonUtil.toJson(args));
  // flowOrder.setCreateByUname(operator);
  // flowOrder.setCreateDate(DateUtil.getNowDateYYYYMMddHHMMSS());

   ProcessModel model = process.getModel();
   if (model != null && args != null) {
     if (StringUtil.isNotBlank(model.getExpireTime())) {
       Date expireTime = DateUtil.formatDate(model.getExpireTime(), "yyyy-MM-dd");
       flowOrder.setExpireTime(expireTime);
     }
     String orderNo = (String) args.get(FlowEngine.ID);
     if (StringUtil.isNotBlank(orderNo)) {
       flowOrder.setOrderNo(orderNo);
     } else {
       flowOrder.setOrderNo(model.getGenerator().generate(model));
     }
   }

   Long retKey = this.insert(flowOrder);
   flowOrder = this.selectByPrimaryKey(retKey);

   FlowOrderHist history = new FlowOrderHist(flowOrder);
   history.setOrderState(FlowConstains.STATE_ACTIVE);
   flowOrderHistService.insert(history);
   return flowOrder;
 }

 /**
  * 流程实例数据会保存至活动实例表、历史实例表
  */
 public void saveOrder(FlowOrder flowOrder) throws Exception {
   FlowOrderHist history = new FlowOrderHist(flowOrder);
   history.setOrderState(FlowConstains.STATE_ACTIVE);
   Long retKey = this.insert(flowOrder);
   flowEngineFacets.getEngine().flowOrderHistService().insert(history);
 }

 /**
  * 流程实例正常完成
  * 
  * @param orderId
  *          流程实例id
  */
 public void complete(String orderId) {
   FlowOrderHist history = flowEngineFacets.getEngine().flowOrderHistService().getHistOrder(orderId);
 //fixme   history.setStatus(FlowConstains.STATE_FINISH);
   history.setEndTime(new Date());

   try {
     flowEngineFacets.getEngine().flowOrderHistService().updateByPrimaryKey(history);
   } catch (Exception e) {
     log.error("error",e);
   }
   this.deleteByPrimaryKey(Long.parseLong(orderId));
   FlowCompletionService completion = flowEngineFacets.getEngine().flowCompletionService();
   if (completion != null) {
     completion.complete(history);
   }
 }

 /**
  * 向活动实例临时添加全局变量数据
  * 
  * @param orderId
  *          实例id
  * @param args
  *          变量数据
  */
 public void addVariable(String orderId, Map<String, Object> args) throws Exception {
   FlowOrder flowOrder = (FlowOrder) this.selectByPrimaryKey(Long.valueOf(orderId));
   Map<String, Object> data = flowOrder.getVariableMap();
   data.putAll(args);
   flowOrder.setVariable(JsonUtil.toJson(data));
   this.updateByPrimaryKey(flowOrder);
 }

 /**
  * 强制中止流程实例
  */
 public void terminate(String orderId) throws Exception {
   terminate(orderId, null);
 }

 /**
  * 强制中止活动实例,并强制完成活动任务
  */
 public void terminate(String orderId, String operator) throws Exception {
   Map<String, Object> map = new HashMap<String, Object>();
   map.put("flowOrderId", orderId);

   List<FlowTask> tasks = flowTaskService.selectByExample(new Query(map));
   for (FlowTask task : tasks) {
     flowTaskServiceApi.complete(String.valueOf(task.getId()), operator);
   }
   FlowOrder flowOrder =  this.selectByPrimaryKey(Long.parseLong(orderId));
       

   FlowOrderHist flowOrderHist = new FlowOrderHist(flowOrder);
   flowOrderHist.setOrderState(FlowConstains.STATE_TERMINATION);
   flowOrderHist.setEndTime(new Date());

   flowOrderHistService.updateByPrimaryKey(flowOrderHist);
   this.deleteByPrimaryKey(Long.parseLong(orderId));
   flowCompletionService.complete(flowOrderHist);
 }

 /**
  * 级联删除指定流程实例的所有数据： 1.flow_order,flow_order_hist 2.flow_task,flow_task_hist 3.flow_task_actor,flow_task_actor_actor
  * 4.flow_ccOrder
  * 
  * @param orderId
  *          实例id
  */
 public void cascadeRemove(String orderId) {
   FlowOrderHist flowOrderHist = (FlowOrderHist) flowOrderHistService.selectByPrimaryKey(Long.parseLong(orderId));
   Assert.notNull(flowOrderHist, "流程历史实例不能为空");

   Map<String, Object> map = new HashMap<String, Object>();
   map.put("flowOrderId", orderId);
   List<FlowTask> activeTasks = flowTaskService.selectByExample(new Query(map));

   List<FlowTaskHist> historyTasks = flowTaskHistService.selectByExample(new Query().putFilter("flowOrderId", Integer.valueOf(orderId)));

   for (FlowTask task : activeTasks) {
     flowTaskActorService.deleteFlowTaskActorByTaskId(String.valueOf(task.getId()));
     flowTaskService.deleteByPrimaryKey(task.getId());
   }
   for (FlowTaskHist historyTask : historyTasks) {
     // TODO:删除历史任务参者
    // flowTaskActorHistService.deleteByPrimaryKey("deleteTaskActorByTaskId", historyTask.getId());
     flowTaskHistService.deleteByPrimaryKey(historyTask.getId());
   }

   Map<String, Object> ccOrdersMap = new HashMap<String, Object>();
   ccOrdersMap.put("flowOrderId", orderId);
   List<FlowCcOrder> ccOrders = flowCcorderService.selectByExample(new Query(ccOrdersMap));
   for (FlowCcOrder ccOrder : ccOrders) {
     flowCcorderService.deleteByPrimaryKey(ccOrder.getId());
   }

  FlowOrder flowOrder =  this.selectByPrimaryKey(Long.parseLong(orderId));
       
   flowOrderHistService.deleteByPrimaryKey(flowOrderHist.getId());
   this.deleteByPrimaryKey(flowOrder.getId());
 }
}

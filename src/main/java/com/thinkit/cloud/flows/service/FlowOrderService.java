package com.thinkit.cloud.flows.service;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowOrder;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface FlowOrderService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowOrder flowOrder);

    public java.lang.Long insertSelective(FlowOrder flowOrder);

    public FlowOrder selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowOrder flowOrder);

    public java.lang.Long updateByPrimaryKey(FlowOrder flowOrder);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<FlowOrder> selectByExample(Query query);
    

    /**
     *
     * @param process 流程定义
     * @param operator 操作人
     * @param args  流程变量
     * @param parentId 父Id
     * @param parentNodeName 父节点名称
     * @return 流程实例
     */
    public FlowOrder createFlowOrder(FlowProcess process, String operator,
        Map<String, Object> args, String parentId, String parentNodeName) throws Exception;

    /**
     * 流程实例正常完成
     * 
     * @param orderId
     *          流程实例id
     */
    public void complete(String orderId);

    /**
     * 向活动实例临时添加全局变量数据
     * 
     * @param orderId
     *          实例id
     * @param args
     *          变量数据
     */
    public void addVariable(String orderId, Map<String, Object> args) throws Exception;

    /**
     * 强制中止流程实例
     */
    public void terminate(String orderId) throws Exception;

    /**
     * 强制中止活动实例,并强制完成活动任务
     */
    public void terminate(String orderId, String operator) throws Exception;

    /**
     * 级联删除指定流程实例的所有数据： 1.flow_order,flow_order_hist 2.flow_task,flow_task_hist 3.flow_task_actor,flow_task_actor_actor
     * 4.flow_ccOrder
     * 
     * @param id
     *          实例id
     */
    public void cascadeRemove(String id);
}

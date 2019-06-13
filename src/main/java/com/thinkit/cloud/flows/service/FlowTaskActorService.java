package com.thinkit.cloud.flows.service;

import java.util.List;

import com.thinkit.cloud.flows.bean.FlowTaskActor;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface FlowTaskActorService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowTaskActor flowTaskActor);

    public java.lang.Long insertSelective(FlowTaskActor flowTaskActor);

    public FlowTaskActor selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowTaskActor flowTaskActor);

    public java.lang.Long updateByPrimaryKey(FlowTaskActor flowTaskActor);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<FlowTaskActor> selectByExample(Query query);
    
    /**
     * 获得任务执行者
     * 
     * @param taskId 任务Id
     * @return 获得任务执行者
     */
    public List<FlowTaskActor> getTaskActorsByTaskId(String taskId);

    /**
     * 移除任务参与者
     * 
     * @param taskId 任务Id
     * @param actors 参与者
     */
    public void removeTaskActor(String taskId, String... actors);

    /**
     * 移除任务参与者
     * 
     * @param taskId 任务Id
     */
    public void deleteFlowTaskActorByTaskId(String taskId);
}

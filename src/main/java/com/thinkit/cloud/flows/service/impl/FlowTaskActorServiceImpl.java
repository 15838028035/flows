package com.thinkit.cloud.flows.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.flows.dao.FlowTaskActorMapper;
import com.thinkit.cloud.flows.service.FlowTaskActorService;
import com.thinkit.cloud.flows.bean.FlowTaskActor;

import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class FlowTaskActorServiceImpl  implements FlowTaskActorService{
	
	@Autowired
	private FlowTaskActorMapper flowTaskActorMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return flowTaskActorMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(FlowTaskActor flowTaskActor){
		return flowTaskActorMapper.insert(flowTaskActor);
	}

	@Override
	public java.lang.Long insertSelective(FlowTaskActor flowTaskActor) {
		return flowTaskActorMapper.insertSelective(flowTaskActor);
	}

	@Override
	public FlowTaskActor selectByPrimaryKey(java.lang.Long id) {
		return flowTaskActorMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(FlowTaskActor flowTaskActor) {
		return flowTaskActorMapper.updateByPrimaryKeySelective(flowTaskActor);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(FlowTaskActor flowTaskActor) {
		return flowTaskActorMapper.updateByPrimaryKey(flowTaskActor);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = flowTaskActorMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<FlowTaskActor> selectByExample(Query query) {
		return flowTaskActorMapper.selectByExample(query);
	}
	

  /**
   * 获得任务执行者
   * 
   * @param taskId 任务Id
   * @return 获得任务执行者
   */
  public List<FlowTaskActor> getTaskActorsByTaskId(String taskId) {
    return this.selectByExample(new Query().putFilter("taskId", taskId));
  }

  /**
   * 移除任务参与者
   * 
   * @param taskId 任务Id
   * @param actors 参与者
   */
  public void removeTaskActor(String taskId, String... actors) {
    for (String actorId : actors) {
     FlowTaskActor flowTaskActor = new FlowTaskActor();
    //  flowTaskActor.setTaskId(taskId);
      flowTaskActor.setActorId(actorId);
   //   this.delete("deleteFlowTaskActorByTaskIdAndActorId", flowTaskActor);
    }
  }


  /**
   * 移除任务参与者
   * 
   * @param taskId 任务Id
   */
  public void deleteFlowTaskActorByTaskId(String taskId) {
//    this.delete("deleteFlowTaskActorByTaskId", taskId);
  }

}

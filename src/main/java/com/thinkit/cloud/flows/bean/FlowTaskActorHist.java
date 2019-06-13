package com.thinkit.cloud.flows.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*流程任务参与者历史
*/
@ApiModel(value = "流程任务参与者历史")
public class FlowTaskActorHist extends MyBaseEntity{
	
	/**
	 * ID  id
	 */
	@ApiModelProperty(value = "ID")
	private java.lang.Long id;
	
	/**
	 * 任务ID  TASK_ID
	 */
	@ApiModelProperty(value = "任务ID")
	private java.lang.Long taskId;
	
	/**
	 * 关联的参与者ID（参与者可以为用户、部门、角色)  ACTOR_ID
	 */
	@ApiModelProperty(value = "关联的参与者ID（参与者可以为用户、部门、角色)")
	private String actorId = "";
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setTaskId(java.lang.Long value) {
		this.taskId = value;
	}
	
	public java.lang.Long getTaskId() {
		return this.taskId;
	}
	
	public void setActorId(String value) {
		this.actorId = value;
	}
	
	public String getActorId() {
		return this.actorId;
	}
	
}


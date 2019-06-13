package com.thinkit.cloud.flows.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*流程抄送实例表
*/
@ApiModel(value = "流程抄送实例表")
public class FlowCcOrder extends MyBaseEntity{
	
	/**
	 * ID  id
	 */
	@ApiModelProperty(value = "ID")
	private java.lang.Long id;
	
	/**
	 * 流程实例编号  order_id
	 */
	@ApiModelProperty(value = "流程实例编号")
	private java.lang.Long orderId;
	
	/**
	 * 执行人  actor_id
	 */
	@ApiModelProperty(value = "执行人")
	private java.lang.Long actorId;
	
	/**
	 * 创建人  creator
	 */
	@ApiModelProperty(value = "创建人")
	private String creator = "";
	
	/**
	 * 完成时间  finish_Time
	 */
	@ApiModelProperty(value = "完成时间")
	private java.util.Date finishTime;
	
	 /**
	 * 完成时间Begin
	 */
	private String  finishTimeBegin;
	/**
	 * 完成时间End
	 */
	private String finishTimeEnd;
	/**
	 * 流程定义ID  FLOW_PROCESS_ID
	 */
	@ApiModelProperty(value = "流程定义ID")
	private java.lang.Long flowProcessId;
	
	/**
	 * 创建人  create_user_id
	 */
	@ApiModelProperty(value = "创建人")
	private java.lang.Long createUserId;
	
	/**
	 * 创建人姓名  create_user_name
	 */
	@ApiModelProperty(value = "创建人姓名")
	private String createUserName = "";
	
	/**
	 * 创建时间  create_time
	 */
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	
	 /**
	 * 创建时间Begin
	 */
	private String  createTimeBegin;
	/**
	 * 创建时间End
	 */
	private String createTimeEnd;
	/**
	 * 更新人  update_user_id
	 */
	@ApiModelProperty(value = "更新人")
	private java.lang.Long updateUserId;
	
	/**
	 * 更新人姓名  update_user_name
	 */
	@ApiModelProperty(value = "更新人姓名")
	private String updateUserName = "";
	
	/**
	 * 更新时间  update_time
	 */
	@ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;
	
	 /**
	 * 更新时间Begin
	 */
	private String  updateTimeBegin;
	/**
	 * 更新时间End
	 */
	private String updateTimeEnd;


	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setOrderId(java.lang.Long value) {
		this.orderId = value;
	}
	
	public java.lang.Long getOrderId() {
		return this.orderId;
	}
	
	public void setActorId(java.lang.Long value) {
		this.actorId = value;
	}
	
	public java.lang.Long getActorId() {
		return this.actorId;
	}
	
	public void setCreator(String value) {
		this.creator = value;
	}
	
	public String getCreator() {
		return this.creator;
	}
	
	public void setFinishTime(java.util.Date value) {
		this.finishTime = value;
	}
	
	public java.util.Date getFinishTime() {
		return this.finishTime;
	}
	
    	public void setFinishTimeBegin(String value) {
            this.finishTimeBegin = value;
        }
        
        public String  getFinishTimeBegin() {
            return this.finishTimeBegin;
        }
        
        public void setFinishTimeEnd(String value) {
            this.finishTimeEnd = value;
        }
        
        public String  getFinishTimeEnd() {
            return this.finishTimeEnd;
        }
	public void setFlowProcessId(java.lang.Long value) {
		this.flowProcessId = value;
	}
	
	public java.lang.Long getFlowProcessId() {
		return this.flowProcessId;
	}
	
	public void setCreateUserId(java.lang.Long value) {
		this.createUserId = value;
	}
	
	public java.lang.Long getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserName(String value) {
		this.createUserName = value;
	}
	
	public String getCreateUserName() {
		return this.createUserName;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
    	public void setCreateTimeBegin(String value) {
            this.createTimeBegin = value;
        }
        
        public String  getCreateTimeBegin() {
            return this.createTimeBegin;
        }
        
        public void setCreateTimeEnd(String value) {
            this.createTimeEnd = value;
        }
        
        public String  getCreateTimeEnd() {
            return this.createTimeEnd;
        }
	public void setUpdateUserId(java.lang.Long value) {
		this.updateUserId = value;
	}
	
	public java.lang.Long getUpdateUserId() {
		return this.updateUserId;
	}
	
	public void setUpdateUserName(String value) {
		this.updateUserName = value;
	}
	
	public String getUpdateUserName() {
		return this.updateUserName;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
    	public void setUpdateTimeBegin(String value) {
            this.updateTimeBegin = value;
        }
        
        public String  getUpdateTimeBegin() {
            return this.updateTimeBegin;
        }
        
        public void setUpdateTimeEnd(String value) {
            this.updateTimeEnd = value;
        }
        
        public String  getUpdateTimeEnd() {
            return this.updateTimeEnd;
        }

	
}


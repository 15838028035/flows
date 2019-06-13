package com.thinkit.cloud.flows.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*流程审批表
*/
@ApiModel(value = "流程审批表")
public class FlowApprove extends MyBaseEntity{
	
	/**
	 * ID  id
	 */
	@ApiModelProperty(value = "ID")
	private java.lang.Long id;
	
	/**
	 * 操作人  operator
	 */
	@ApiModelProperty(value = "操作人")
	private String operator = "";
	
	/**
	 * 操作时间  operate_Time
	 */
	@ApiModelProperty(value = "操作时间")
	private java.util.Date operateTime;
	
	 /**
	 * 操作时间Begin
	 */
	private String  operateTimeBegin;
	/**
	 * 操作时间End
	 */
	private String operateTimeEnd;
	/**
	 * 操作结果  opt_result
	 */
	@ApiModelProperty(value = "操作结果")
	private String optResult = "";
	
	/**
	 * 操作内容  opt_content
	 */
	@ApiModelProperty(value = "操作内容")
	private String optContent = "";
	
	/**
	 * 任务名称  task_Name
	 */
	@ApiModelProperty(value = "任务名称")
	private String taskName = "";
	
	/**
	 * 流程实例ID  flow_order_id
	 */
	@ApiModelProperty(value = "流程实例ID")
	private java.lang.Long flowOrderId;
	
	/**
	 * 任务ID  flow_task_id
	 */
	@ApiModelProperty(value = "任务ID")
	private java.lang.Long flowTaskId;
	
	/**
	 * 流程定义ID  process_Id
	 */
	@ApiModelProperty(value = "流程定义ID")
	private java.lang.Long processId;
	
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
	
	public void setOperator(String value) {
		this.operator = value;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public void setOperateTime(java.util.Date value) {
		this.operateTime = value;
	}
	
	public java.util.Date getOperateTime() {
		return this.operateTime;
	}
	
    	public void setOperateTimeBegin(String value) {
            this.operateTimeBegin = value;
        }
        
        public String  getOperateTimeBegin() {
            return this.operateTimeBegin;
        }
        
        public void setOperateTimeEnd(String value) {
            this.operateTimeEnd = value;
        }
        
        public String  getOperateTimeEnd() {
            return this.operateTimeEnd;
        }
	public void setOptResult(String value) {
		this.optResult = value;
	}
	
	public String getOptResult() {
		return this.optResult;
	}
	
	public void setOptContent(String value) {
		this.optContent = value;
	}
	
	public String getOptContent() {
		return this.optContent;
	}
	
	public void setTaskName(String value) {
		this.taskName = value;
	}
	
	public String getTaskName() {
		return this.taskName;
	}
	
	public void setFlowOrderId(java.lang.Long value) {
		this.flowOrderId = value;
	}
	
	public java.lang.Long getFlowOrderId() {
		return this.flowOrderId;
	}
	
	public void setFlowTaskId(java.lang.Long value) {
		this.flowTaskId = value;
	}
	
	public java.lang.Long getFlowTaskId() {
		return this.flowTaskId;
	}
	
	public void setProcessId(java.lang.Long value) {
		this.processId = value;
	}
	
	public java.lang.Long getProcessId() {
		return this.processId;
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


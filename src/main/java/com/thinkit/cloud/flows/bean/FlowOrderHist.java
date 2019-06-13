package com.thinkit.cloud.flows.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*流程实例历史表
*/
@ApiModel(value = "流程实例历史表")
public class FlowOrderHist extends MyBaseEntity{
	
	/**
	 * ID  id
	 */
	@ApiModelProperty(value = "ID")
	private java.lang.Long id;
	
	/**
	 * 流程编号  ORDER_NO
	 */
	@ApiModelProperty(value = "流程编号")
	private String orderNo = "";
	
	/**
	 * 流程版本  ORDER_VERSION
	 */
	@ApiModelProperty(value = "流程版本")
	private java.lang.Long orderVersion;
	
	/**
	 * 流程定义ID  FLOW_PROCESS_ID
	 */
	@ApiModelProperty(value = "流程定义ID")
	private java.lang.Long flowProcessId;
	
	/**
	 * 父流程实例ID  PARENT_ID
	 */
	@ApiModelProperty(value = "父流程实例ID")
	private java.lang.Long parentId;
	
	/**
	 * 父流程名称  parent_Node_Name
	 */
	@ApiModelProperty(value = "父流程名称")
	private String parentNodeName = "";
	
	/**
	 * 流程实例期望完成时间  expire_time
	 */
	@ApiModelProperty(value = "流程实例期望完成时间")
	private java.util.Date expireTime;
	
	 /**
	 * 流程实例期望完成时间Begin
	 */
	private String  expireTimeBegin;
	/**
	 * 流程实例期望完成时间End
	 */
	private String expireTimeEnd;
	/**
	 * 流程实例优先级  priority
	 */
	@ApiModelProperty(value = "流程实例优先级")
	private java.lang.Long priority;
	
	/**
	 * 流程实例附属变量  variable
	 */
	@ApiModelProperty(value = "流程实例附属变量")
	private String variable = "";
	
	/**
	 * 流程实例状态（0：结束；1：活动）  order_state
	 */
	@ApiModelProperty(value = "流程实例状态（0：结束；1：活动）")
	private java.lang.Long orderState;
	
	/**
	 * 流程结束时间  end_time
	 */
	@ApiModelProperty(value = "流程结束时间")
	private java.util.Date endTime;
	
	 /**
	 * 流程结束时间Begin
	 */
	private String  endTimeBegin;
	/**
	 * 流程结束时间End
	 */
	private String endTimeEnd;
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

	 /**
   * 构造函数
   * @param order 流程实例
   */
  public FlowOrderHist(FlowOrder order) {
    this.id = order.getId();
    this.flowProcessId = order.getFlowProcessId();
  //  this.setCreateDate(order.getCreateDate());
    this.expireTime = order.getExpireTime();
   // this.setCreateByUname(order.getCreateByUname());
   // this.setUpdateByUname(order.getUpdateByUname());
    this.parentId = order.getParentId();
    this.priority = order.getPriority();
    this.orderNo = order.getOrderNo();
    this.variable = order.getVariable();
  }

  /**
   * 根据历史实例撤回活动实例
   * 
   * @return 活动实例对象
   */
  public FlowOrder undo() {
    FlowOrder order = new FlowOrder();
    order.setId(this.id);
    order.setFlowProcessId(this.flowProcessId);
    order.setParentId(this.parentId);
  /*  order.setCreateByUname(this.getCreateByUname());
    order.setCreateDate(this.getCreateDate());
    order.setUpdateByUname(this.getUpdateByUname());
    order.setUpdateDate(this.getUpdateDate());*/
    order.setExpireTime(this.expireTime);
    order.setOrderNo(this.orderNo);
    order.setPriority(this.priority);
    order.setVariable(this.variable);
    order.setOrderVersion(0L);
    return order;
  }
  

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setOrderNo(String value) {
		this.orderNo = value;
	}
	
	public String getOrderNo() {
		return this.orderNo;
	}
	
	public void setOrderVersion(java.lang.Long value) {
		this.orderVersion = value;
	}
	
	public java.lang.Long getOrderVersion() {
		return this.orderVersion;
	}
	
	public void setFlowProcessId(java.lang.Long value) {
		this.flowProcessId = value;
	}
	
	public java.lang.Long getFlowProcessId() {
		return this.flowProcessId;
	}
	
	public void setParentId(java.lang.Long value) {
		this.parentId = value;
	}
	
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	
	public void setParentNodeName(String value) {
		this.parentNodeName = value;
	}
	
	public String getParentNodeName() {
		return this.parentNodeName;
	}
	
	public void setExpireTime(java.util.Date value) {
		this.expireTime = value;
	}
	
	public java.util.Date getExpireTime() {
		return this.expireTime;
	}
	
    	public void setExpireTimeBegin(String value) {
            this.expireTimeBegin = value;
        }
        
        public String  getExpireTimeBegin() {
            return this.expireTimeBegin;
        }
        
        public void setExpireTimeEnd(String value) {
            this.expireTimeEnd = value;
        }
        
        public String  getExpireTimeEnd() {
            return this.expireTimeEnd;
        }
	public void setPriority(java.lang.Long value) {
		this.priority = value;
	}
	
	public java.lang.Long getPriority() {
		return this.priority;
	}
	
	public void setVariable(String value) {
		this.variable = value;
	}
	
	public String getVariable() {
		return this.variable;
	}
	
	public void setOrderState(java.lang.Long value) {
		this.orderState = value;
	}
	
	public java.lang.Long getOrderState() {
		return this.orderState;
	}
	
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	
    	public void setEndTimeBegin(String value) {
            this.endTimeBegin = value;
        }
        
        public String  getEndTimeBegin() {
            return this.endTimeBegin;
        }
        
        public void setEndTimeEnd(String value) {
            this.endTimeEnd = value;
        }
        
        public String  getEndTimeEnd() {
            return this.endTimeEnd;
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


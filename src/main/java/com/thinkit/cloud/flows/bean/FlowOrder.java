package com.thinkit.cloud.flows.bean;
import java.util.Collections;
import java.util.Map;

import com.thinkit.cloud.flows.util.JsonUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*流程实例表
*/
@ApiModel(value = "流程实例表")
public class FlowOrder extends MyBaseEntity{
	
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

    /**
     * 变量map
     * @return 变量map
     */
    public Map<String, Object> getVariableMap() {
      Map<String, Object> map = JsonUtil.fromJson(this.variable, Map.class);
      if (map == null)  {
        return Collections.emptyMap();
      }
      return map;
    }

    /**
     * 重写
     */
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Order(id=").append(this.id);
      sb.append(",processId=").append(this.flowProcessId);
     // sb.append(",creator=").append(this.getCreateByUname());
     // sb.append(",createTime").append(this.getCreateDate());
      sb.append(",orderNo=").append(this.orderNo).append(")");
      return sb.toString();
    }
	
}


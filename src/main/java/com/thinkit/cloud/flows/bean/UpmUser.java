package com.thinkit.cloud.flows.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*系统用户表
*/
@ApiModel(value = "系统用户表")
public class UpmUser extends MyBaseEntity{
	
	/**
	 * ID  id
	 */
	@ApiModelProperty(value = "ID")
	private java.lang.Long id;
	
	/**
	 * 登陆账号  user_no
	 */
	@ApiModelProperty(value = "登陆账号")
	private String userNo;
	
	/**
	 * 登陆密码  user_pass
	 */
	@ApiModelProperty(value = "登陆密码")
	private String userPass;

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setUserNo(String value) {
		this.userNo = value;
	}
	
	public String getUserNo() {
		return this.userNo;
	}
	public void setUserPass(String value) {
		this.userPass = value;
	}
	
	public String getUserPass() {
		return this.userPass;
	}
	
}


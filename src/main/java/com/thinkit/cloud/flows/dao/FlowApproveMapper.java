package com.thinkit.cloud.flows.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowApprove;


public interface FlowApproveMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowApprove flowApprove);

    public java.lang.Long insertSelective(FlowApprove flowApprove);

    public FlowApprove selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowApprove flowApprove);

    public java.lang.Long updateByPrimaryKey(FlowApprove flowApprove);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<FlowApprove> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}

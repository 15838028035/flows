package com.thinkit.cloud.flows.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowTaskActorHist;


public interface FlowTaskActorHistMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowTaskActorHist flowTaskActorHist);

    public java.lang.Long insertSelective(FlowTaskActorHist flowTaskActorHist);

    public FlowTaskActorHist selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowTaskActorHist flowTaskActorHist);

    public java.lang.Long updateByPrimaryKey(FlowTaskActorHist flowTaskActorHist);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<FlowTaskActorHist> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}

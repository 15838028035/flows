package com.thinkit.cloud.flows.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.cache.Cache;
import com.thinkit.cloud.flows.cache.CacheFactory;
import com.thinkit.cloud.flows.dao.FlowProcessMapper;
import com.thinkit.cloud.flows.exceptions.FlowException;
import com.thinkit.cloud.flows.model.ProcessModel;
import com.thinkit.cloud.flows.parser.ModelParser;
import com.thinkit.cloud.flows.service.FlowProcessService;
import com.thinkit.cloud.flows.util.Assert;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.FileUtil;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

@Service
public class FlowProcessServiceImpl  implements FlowProcessService{
	
    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String DEFAULT_SEPARATOR = ".";
    /**
     * 流程定义对象cache名称
     */
    private static final String CACHE_ENTITY = "snaker.process.entity";
    /**
     * 流程id、name的cache名称
     */
    private static final String CACHE_NAME = "snaker.process.name";
    /**
     * 实体cache(key=name,value=entity对象)
     */
    private Cache entityCache;
    /**
     * 名称cache(key=id,value=name对象)
     */
    private Cache nameCache;

    public static final Integer FLOW_CACHED_TIME = 60 * 60 * 24;// 缓存一天
    
	@Autowired
	private FlowProcessMapper flowProcessMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return flowProcessMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(FlowProcess flowProcess){
		return flowProcessMapper.insert(flowProcess);
	}

	@Override
	public java.lang.Long insertSelective(FlowProcess flowProcess) {
		return flowProcessMapper.insertSelective(flowProcess);
	}

	@Override
	public FlowProcess selectByPrimaryKey(java.lang.Long id) {
		return flowProcessMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(FlowProcess flowProcess) {
		return flowProcessMapper.updateByPrimaryKeySelective(flowProcess);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(FlowProcess flowProcess) {
		return flowProcessMapper.updateByPrimaryKey(flowProcess);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = flowProcessMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<FlowProcess> selectByExample(Query query) {
		return flowProcessMapper.selectByExample(query);
	}
	
	/**
   * 检查流程定义对象
   * 
   * @param process
   *          流程定义对象
   * @param idOrName
   *          流程定义id/name
   */
  public void check(FlowProcess process, String idOrName) {

  }

  /**
   * 保存流程定义
   * 
   * @param process
   *          流程定义对象
   */
  public void saveFlowProcess(FlowProcess process) throws Exception {
    this.insert(process);
  }

  /**
   * 更新流程定义的类别
   * 
   * @param id
   *          流程定义id
   * @param type
   *          类别
   */
  public void updateType(String id, String type) throws Exception {
    FlowProcess entity = getProcessById(id);
    entity.setFlowType(type);
    this.updateByPrimaryKeySelective(entity);
    cache(entity);
  }

  /**
   * 缓存实体
   * 
   * @param entity
   *          流程定义对象
   */
  private void cache(FlowProcess entity) {
    Cache nameCache = ensureAvailableNameCache();
    Cache entityCache = ensureAvailableEntityCache();

    if (entity.getModel() == null && entity.getFlowContent() != null) {
      entity.setModel(ModelParser.parse(entity.getFlowContent()));
    }
    String processName = entity.getFlowName() + DEFAULT_SEPARATOR + entity.getFlowVersion();
    if (nameCache != null && entityCache != null) {
      if (log.isDebugEnabled()) {
        log.debug("cache process id is[{}],name is[{}]" + entity.getId() + processName);
      }
      entityCache.add(processName, entity, FLOW_CACHED_TIME);
      nameCache.add(entity.getId().toString(), processName, FLOW_CACHED_TIME);
    } else {
      if (log.isDebugEnabled()) {
        log.debug("no cache implementation class");
      }
    }
  }

  /**
   * 根据主键ID获取流程定义对象
   * 
   * @param id
   *          流程定义id
   * @return Process 流程定义对象
   */
  public FlowProcess getProcessById(String id) throws Exception {
    Assert.notNull(id);
    FlowProcess entity = null;
    String processName;
    Cache nameCache = ensureAvailableNameCache();
    Cache entityCache = ensureAvailableEntityCache();
    if (nameCache != null && entityCache != null) {
      processName = (String) nameCache.get(id);
      if (StringUtil.isNotBlank(processName)) {
        entity = (FlowProcess) entityCache.get(processName);
      }
    }
    if (entity != null) {
      if (log.isDebugEnabled()) {
        log.debug("obtain process[id={}] from cache." + id);
      }
      return entity;
    }
    entity = (FlowProcess) this.selectByPrimaryKey(Long.valueOf(id));
    if (entity != null) {
      if (log.isDebugEnabled()) {
        log.debug("obtain process[id={}] from database." + id);
      }
      cache(entity);
    }
    return entity;
  }

  /**
   * 根据流程name获取流程定义对象
   * 
   * @param name
   *          流程定义名称
   * @return Process 流程定义对象
   */
  public FlowProcess getProcessByName(String name) throws Exception {
    return getProcessByVersion(name, null);
  }

  /**
   * 根据流程name、version获取流程定义对象
   * 
   * @param name
   *          流程定义名称
   * @param version
   *          版本号
   * @return Process 流程定义对象
   */
  public FlowProcess getProcessByVersion(String name, Long version)
      throws Exception {
    if (version == null) {
      version = getLatestProcessVersion(name);
    }

    FlowProcess entity = null;
    String processName = name + DEFAULT_SEPARATOR + version;
    Cache entityCache = ensureAvailableEntityCache();
    if (entityCache != null) {
      entity = (FlowProcess) entityCache.get(processName);
    }
    if (entity != null) {
      if (log.isDebugEnabled()) {
        log.debug("obtain process[name={}] from cache." + processName);
      }
      return entity;
    }

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("flowName", name);
    map.put("flowVersion", version);

    List<FlowProcess> processs = this.selectByExample(new Query(map));

    if (processs != null && !processs.isEmpty()) {
      if (log.isDebugEnabled()) {
        log.debug("obtain process[name={}] from database." + processName);
      }
      entity = processs.get(0);
      cache(entity);
    }
    return entity;
  }

  /**
   * 根據InputStream輸入流，部署流程定义
   * 
   * @param input
   *          流程定义输入流
   * @return String 流程定义id
   */
  public String deploy(InputStream input) {
    return deploy(input, null);
  }

  /**
   * 根據input輸入流，部署流程定义
   * 
   * @param inputStr
   *          流程定义输入流
   * @param creator
   *          创建人
   * @return String 流程定义id
   */
  public String deploy(String inputStr, String creator) {
    InputStream input = FileUtil.getStreamFromString(inputStr);
    return deploy(input, creator);
  }

  /**
   * 根據InputStream輸入流，部署流程定义
   * 
   * @param input
   *          流程定义输入流
   * @param creator
   *          创建人
   * @return String 流程定义id
   */
  public String deploy(InputStream input, String creator) {
    Assert.notNull(input);
    try {

      InputStream input2 = null;

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int len;
      while ((len = input.read(buffer)) > -1) {
        baos.write(buffer, 0, len);
      }
      baos.flush();

      InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
      InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());

      byte[] bytes = FileUtil.readBytes(stream1);// FIXME:
      ProcessModel model = ModelParser.parse(stream2);

      Long version = getLatestProcessVersion(model.getName());
     FlowProcess entity = new FlowProcess();
      entity.setFlowNo(StringUtil.getUuidKey());
      if (version == null || version < 0) {
        entity.setFlowVersion(0L);
      } else {
        entity.setFlowVersion(version + 1);
      }
      entity.setFlowName(model.getName());
      entity.setDisplayName(model.getDisplayName());

    //  entity.setLockStatus(FlowConstains.STATE_ACTIVE.toString());
      entity.setModel(model);
      entity.setFlowContent(bytes);
     // entity.setCreateDate(DateUtil.getNowDateYYYYMMddHHMMSS());
     // entity.setCreateByUname(creator);
      insert(entity);
      cache(entity);
      return entity.getId().toString();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new FlowException(e.getMessage(), e.getCause());
    }
  }

  /**
   * 根據InputStream輸入流，部署流程定义
   * 
   * @param id
   *          流程定义id
   * @param inputStr
   *          流程定义xml内容
   */
  public void redeploy(String id, String inputStr) {
    InputStream input = FileUtil.getStreamFromString(inputStr);
    redeploy(id, input);
  }

  /**
   * 根據InputStream輸入流，部署流程定义
   * 
   * @param id
   *          流程定义id
   * @param input
   *          流程定义输入流
   */
  public void redeploy(String id, InputStream input) {
    Assert.notNull(input);
    try {
      FlowProcess entity = (FlowProcess) this
          .getProcessById(id);
      Assert.notNull(entity);
      String oldProcessName = entity.getFlowName();

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int len;
      while ((len = input.read(buffer)) > -1) {
        baos.write(buffer, 0, len);
      }
      baos.flush();

      InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());

      byte[] bytes = FileUtil.readBytes(stream1);

      ProcessModel model = ModelParser.parse(bytes);
      entity.setModel(model);
      entity.setFlowName(model.getName());
      entity.setDisplayName(model.getDisplayName());
      entity.setFlowContent(bytes);
      entity.setInstanceUrl(model.getInstanceUrl());
    //  entity.setUpdateDate(DateUtil.getNowDateYYYYMMddHHMMSS());

      this.updateByPrimaryKeySelective(entity);

      if (!oldProcessName.equalsIgnoreCase(entity.getFlowName())) {
        Cache entityCache = ensureAvailableEntityCache();
        if (entityCache != null) {
          entityCache.delete(oldProcessName + DEFAULT_SEPARATOR + entity.getFlowVersion());
        }
      }
      cache(entity);

    } catch (Exception e) {
      log.error(e.getMessage());
      throw new FlowException(e.getMessage(), e.getCause());
    }
  }

  /**
   * 卸载指定的流程定义，只更新状态
   * 
   * @param id
   *          流程定义id
   */
  public void undeploy(String id) throws Exception {
   FlowProcess flowProcess = this.getProcessById(id);
   // flowProcess.setStatus(FlowConstains.STATE_FINISH);
    this.updateByPrimaryKeySelective(flowProcess);
    cache(flowProcess);
  }

  /**
   * 获得流程最新版本
   * 
   * @param flowName 流程名称
   * @return 获得流程最新版本
   */
  public Long getLatestProcessVersion(String flowName) throws Exception {
    Map<String, Object> map = new HashMap<>();
    map.put("flowName", flowName);
    map.put("sortName", "id");
    map.put("sortOrder", "desc");

    List list = this.selectByExample(new Query(map));
    if (list != null && list.size() > 0) {
      return ((FlowProcess) list.get(0)).getFlowVersion();
    }
    return 0L;
  }

  /**
   * 清除实体
   * 
   * @param entity
   *          流程定义对象
   */
  private void clear(FlowProcess entity) {
    Cache nameCache = ensureAvailableNameCache();
    Cache entityCache = ensureAvailableEntityCache();
    String processName = entity.getFlowName() + DEFAULT_SEPARATOR + entity.getFlowVersion();
    if (nameCache != null && entityCache != null) {
      nameCache.delete(entity.getId().toString());
      entityCache.delete(processName);
    }
  }

  private Cache ensureAvailableEntityCache() {
    Cache entityCache = ensureEntityCache();
    if (entityCache == null) {
      nameCache = (Cache) CacheFactory.getCache().get(CACHE_ENTITY);
    }
    return entityCache;
  }

  private Cache ensureAvailableNameCache() {
    Cache nameCache = ensureNameCache();
    if (nameCache == null) {
      nameCache = (Cache) CacheFactory.getCache().get(CACHE_NAME);
    }
    return nameCache;
  }

  public Cache ensureEntityCache() {
    return entityCache;
  }

  public void setEntity(Cache entityCache) {
    this.entityCache = entityCache;
  }

  public Cache ensureNameCache() {
    return nameCache;
  }

  public void setNameCache(Cache nameCache) {
    this.nameCache = nameCache;
  }

}

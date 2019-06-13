package com.thinkit.cloud.flows.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thinkit.cloud.flows.bean.FlowBaseEntityVO;
import com.thinkit.cloud.flows.bean.FlowProcess;
import com.thinkit.cloud.flows.bean.FlowTask;
import com.thinkit.cloud.flows.bean.FlowTaskHist;
import com.thinkit.cloud.flows.model.ProcessModel;
import com.thinkit.cloud.flows.service.FlowEngineFacetsService;
import com.thinkit.cloud.flows.service.FlowProcessService;
import com.thinkit.cloud.flows.service.FlowTaskHistService;
import com.thinkit.cloud.flows.service.FlowTaskService;
import com.thinkit.cloud.flows.util.Assert;
import com.thinkit.cloud.flows.util.FileUtil;
import com.thinkit.cloud.flows.util.FlowUtil;
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *流程定义表管理
 */
@Api(value = "流程定义表服务", tags = "流程定义表服务接口")
@RestController()
public class FlowProcessController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlowProcessService flowProcessService;
	
	@Autowired
	private FlowEngineFacetsService flowEngineFacetsService;
	
	@Autowired
	private FlowTaskService flowTaskService;
	
	@Autowired
  private FlowTaskHistService flowTaskHistService;
	
	@ApiOperation(value = "分页列表")
	@GetMapping(value = "/api/FlowProcess")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  flowProcessService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/FlowProcess")
		public RestAPIResult2 create(@ModelAttribute FlowProcess flowProcess,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					flowProcess.setCreateUserId(createBy);
					flowProcess.setCreateUserName(getUserName(request));
					flowProcess.setCreateTime(new Date());
					flowProcessService.insertSelective(flowProcess);
					
				}catch(Exception e) {
					logger.error("[流程定义表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/FlowProcess/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute FlowProcess flowProcess,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					flowProcess.setUpdateUserId(createBy);
					flowProcess.setUpdateUserName(getUserName(request));
					flowProcess.setUpdateTime(new Date());
					flowProcessService.updateByPrimaryKeySelective(flowProcess);
					
				}catch(Exception e) {
					logger.error("[流程定义表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/FlowProcess/{id}")
	public FlowProcess show(@PathVariable("id") java.lang.Long id )  {
		FlowProcess flowProcess =flowProcessService.selectByPrimaryKey(id);
		if(flowProcess== null) {
			flowProcess = new FlowProcess();
		}
		return flowProcess;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/FlowProcess/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		flowProcessService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/FlowProcess/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		FlowProcess flowProcess =flowProcessService.selectByPrimaryKey(id);
		if(flowProcess== null) {
			flowProcess = new FlowProcess();
		}
		
		retMap.put("flowProcess", flowProcess);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/FlowProcess/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<FlowProcess> list = flowProcessService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
	
	/**
   * 部署文件
   * @return 页面
   * @throws Exception 异常
   */
	@PostMapping(value = "/api/FlowProcess/processDeployUploadFile")
  public RestAPIResult2 processDeployUploadFile(HttpServletRequest request, @RequestParam("templateFile") MultipartFile[] files)  {
    InputStream input = null;
    try {
        for (MultipartFile multipartFile : files) {
        input = multipartFile.getInputStream();
        String flowContent = FileUtil.readStreamToString(input);
        flowProcessService.deploy(flowContent, this.getUserName(request));
        }
    } catch (Exception e) {
      logger.error("部署失败",e);
      return new RestAPIResult2().respCode(0).respMsg("部署失败{} " ,e.getMessage());
    }

    return new RestAPIResult2();
  }

  /**
   * 部署
   * @return 页面
   * @throws Exception 异常
   */
	@PostMapping(value = "/api/FlowProcess/processDeploy")
  public RestAPIResult2 processDeploy(HttpServletRequest request,@RequestBody FlowBaseEntityVO flowBaseEntityVO) throws Exception {
    try {
        if (flowBaseEntityVO.getProcessId() != null) {
          FlowProcess  flowProcess = (FlowProcess) flowProcessService.getProcessById(flowBaseEntityVO.getProcessId());
        flowProcess.setFlowContentStr(StringUtil.byteToString(flowProcess.getFlowContent()));

        String flowContent = flowProcess.getFlowContentStr();
        flowProcessService.deploy(flowContent, this.getUserName(request));
      }

    } catch (Exception e) {
        logger.error("部署失败",e);
        return new RestAPIResult2().respCode(0).respMsg("部署失败{} " ,e.getMessage());
    }

    return new RestAPIResult2();
  }

  /**
   * 部署
   * @return 页面
   * @throws Exception 异常
   */
	@PostMapping(value = "/api/FlowProcess/processReDeploy")
  public RestAPIResult2 processReDeploy(HttpServletRequest request,@RequestBody FlowBaseEntityVO flowBaseEntityVO)  {
    try {
      if (flowBaseEntityVO.getProcessId() != null) {
          FlowProcess flowProcess = (FlowProcess) flowProcessService.getProcessById(flowBaseEntityVO.getProcessId());
        flowProcess.setFlowContentStr(StringUtil.byteToString(flowProcess.getFlowContent()));
        String flowContent = flowProcess.getFlowContentStr();
        flowProcessService.redeploy(flowBaseEntityVO.getProcessId(), flowContent);
      }

    } catch (Exception e) {
        logger.error("部署失败",e);
        return new RestAPIResult2().respCode(0).respMsg("部署失败{} " ,e.getMessage());
    }
      return new RestAPIResult2();
  }

  /**
   * 流程设计
   * @return 页面
   * @throws Exception 异常
   */
	 @GetMapping(value = "/api/FlowProcess/processDesigner")
  public RestAPIResult2 processDesigner(String id) throws Exception {
     String flowProcessJson ="";
     if (StringUtil.isNotBlank(id)) {
        FlowProcess flowProcess = flowProcessService.getProcessById(id.toString());
      ProcessModel processModel = flowProcess.getModel();
      if (processModel != null) {
        flowProcessJson = FlowUtil.getModelJson(processModel);
      }

    }
    return new RestAPIResult2().respData(flowProcessJson);
  }

  /**
   * 部署xml
   * @return null
   */
	 @PostMapping(value = "/api/FlowProcess/processDeployXml")
  public RestAPIResult2 processDeployXml(HttpServletRequest request, String flowContentStr, String id) {
    InputStream input = null;
    try {
      String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
      String flowContent = xml + FlowUtil.convertXml(flowContentStr);
      input = FileUtil.getStreamFromString(flowContent);
      if (StringUtil.isNotBlank(id)) {
        flowProcessService.redeploy(id, flowContent);
      } else {
        flowProcessService.deploy(flowContent, this.getUserName(request));
      }
    } catch (Exception e) {
        logger.error("部署xml失败",e);
        return new RestAPIResult2().respCode(0).respMsg("部署xml失败 " ,e.getMessage());
    }
    return new RestAPIResult2();
  }

  /**
   *  启动流程
   * @param request
   * @param flowName
   * @return
   * @throws Exception
   */
	 @PostMapping(value = "/api/FlowProcess/processStart")
  public RestAPIResult2 processStart(HttpServletRequest request,String flowName) throws Exception {
    flowEngineFacetsService.startInstanceByName(flowName, null, this.getUserName(request), null);
     return new RestAPIResult2();
  }

  /**
   * 流程查看
   * @return 页面
   */
  public String flowProcessView() {
   // FlowOrderHist order = flowEngine.flowQueryService().getHistOrder(orderId);
   // List<FlowTaskHist> tasks = flowEngine.flowQueryService().getHistoryTasks(orderId);

    return "flowProcessView";
  }

  /**
   * 显示独立的流程图
   */
  public String flowDiagram() {
    return "flowDiagram";
  }

  /**
   * 流程图json
   * @return json
   * @throws Exception 异常
   */
  @PostMapping(value = "/api/FlowProcess/diagramJson")
  public Map<String, String> diagramJson(@RequestBody FlowBaseEntityVO flowBaseEntityVO) throws Exception {
    FlowProcess process = flowProcessService.getProcessById(flowBaseEntityVO.getProcessId());

    Assert.notNull(process);
    ProcessModel model = process.getModel();
    Map<String, String> jsonMap = new HashMap<String, String>();
    if (model != null) {
      jsonMap.put("process", FlowUtil.getModelJson(model));
    }

    if (StringUtil.isNotBlank(flowBaseEntityVO.getOrderId())) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("flowOrderId", flowBaseEntityVO.getOrderId());
      List<FlowTask> tasks = flowTaskService.selectByExample(new Query(map));

      List<FlowTaskHist> historyTasks = flowTaskHistService.selectByExample(new Query(map));
      jsonMap.put("state", FlowUtil.getStateJson(model, tasks, historyTasks));
    }
    logger.debug(jsonMap.get("state"));
    // {"historyRects":{"rects":[{"paths":["TO 任务1"],"name":"开始"},{"paths":["TO 分支"],"name":"任务1"},{"paths":["TO
    // 任务3","TO 任务4","TO 任务2"],"name":"分支"}]}}
    // return jsonMap;
    return jsonMap;
  }

  /**
   * 下载文件应访问该地址 对应annotation注解里面的 name = "download"
   */
  public String downloadFlowXml() {
    return "download";
  }

  /**
   *下载文件
   */
  @ApiOperation(value = "下载文件")
  @RequestMapping(value = "/api/FlowProcess/downloadFlowXml", method = RequestMethod.GET)
  public void downloadFlowXml(HttpServletRequest request, HttpServletResponse response,String processId) throws Exception {
    // 文件地址路径
    FlowProcess  flowProcess = (FlowProcess) flowProcessService.getProcessById(processId);
    
      OutputStream os = response.getOutputStream();
      try {
        response.reset();
        response.setHeader("Content-Disposition",
            "attachment; filename=" + new String(flowProcess.getFlowName().getBytes("utf8"), "iso-8859-1"));
        response.setContentType("application/octet-stream; charset=utf-8");
        os.write(flowProcess.getFlowContent());
        os.flush();
      } finally {
        if (os != null) {
          os.close();
        }
      }
  }
  
  
  /**
   * 获取下载流 对应 annotation 注解里面的 "inputName", "inputStream" 假如 annotation 注解改为 "inputName",
   * "myStream"，则下面的方法则应改为：getMyStream
   * 
   * @return InputStream
   */
  public InputStream getInputStream(String id) throws Exception {
    // 下载路径
    InputStream input = null;
    try {
      if (id != null) {
          FlowProcess flowProcess = (FlowProcess) flowProcessService.getProcessById(id);
        flowProcess.setFlowContentStr(StringUtil.byteToString(flowProcess.getFlowContent()));
        String flowContent = flowProcess.getFlowContentStr();
        input = FileUtil.getStreamFromString(flowContent);
        return input;
      }
    } catch (Exception e) {
      logger.error("getInputStream Exception",e);
    }

    return null;
  }

}


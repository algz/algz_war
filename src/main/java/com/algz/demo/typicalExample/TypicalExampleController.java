package com.algz.demo.typicalExample;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cf611.util.ProTablePage;

/**
 * 1.示例类
 * @RestController
 * @author algz
 *
 */
@RestController
@RequestMapping("/demo/typicalexample")
public class TypicalExampleController {

	/**
	 * 日志类，用于打印到日志文件。
	 * eg:
	 * 2021-12-31 11:47:35.711  INFO 8900 --- [nio-8080-exec-6] com.cf611.modelManager.ModelControl      : 访问的URL地址为(包括参数):/demo/index
	 */
	private static final Logger logger=LoggerFactory.getLogger(TypicalExampleController.class);
	
	/**
	 * 1.POST请求
	 * (与 @PostMapping 效果相同)
	 * @param typicalExample 采用实体接收数据
	 * @return
	 */
	@RequestMapping(path = "/addtypicalexample",method=RequestMethod.POST)
	public String addTypicalExample(TypicalExample typicalExample) {
		logger.info("1.POST请求");
		return null;//service.AddDefinitionDetail(detailParam);
	}
	
	/**
	 * 2.POST请求提交.
	 * (可以省略@RequestBody)
	 *   export async function submitDefinition(params: any) {
		    return request('/algz/typicalexample/submittypicalexample', {
		      method: 'POST',
		      requestType:'form',//application/x-www-form-urlencoded
		      data:params
		    });
		  }
	 * @param params
	 * @return
	 */
	@PostMapping("submittypicalexample")
	public String submitDefinition(TypicalExample params) {
		return null;//service.submitDefinition(params);
	}
	
	/**
	 * 3.POST请求（添加）
	 * 
	   * 添加规则与指标的关联
	   * Post提交 (以表单方式提交)
	   * @param options 
	   * @returns 
		export async function addRegulationIndicator(options?:any){
	      return request('/algz/regulationmanager/addregulationindicator',{
	        method:'POST',
	        data:options
	      })
	    }
	    
	 * (与 @PostMapping 效果相同)
	 * @param map 采用Map类型接收数据（K-V 键值对）,必须添加 @RequestBody ,否则接收不到数据。
	 */
	@RequestMapping(path="addregulationindicator",method=RequestMethod.POST)
	public void addRegulationIndicator(@RequestBody Map<String,String> map ) {
//		service.addRegulationIndicator( m.get("regulationId"), m.get("indicatorId"));
		return ;
	}
	
	/**
	 * 4.POST请求（删除）
	 *   export async function delDefinitionDetail(options?:any){
		    return request('/algz/typicalexample/deltypicalexample',{
		      method:'POST',
		      requestType:'form',//application/x-www-form-urlencoded 此行可省略
		      data:options
		    })
		  }
	 * @param typicalExample
	 * @return
	 */
	@PostMapping("deltypicalexample")
	public String delTypicalExample(@RequestBody TypicalExample typicalExample) {
		return null;//service.delDefinition(definition);
	}
	
	/**
	 * 获取 tablePage
	 * @param pageParam
	 * @param param
	 * @return
	 */
	@RequestMapping("/typicalexamples")
	public ProTablePage<TypicalExample> getTypicalExamples(ProTablePage<TypicalExample> pageParam,TypicalExample param) {
		return null;//service.GetDefinitionDetails(pageParam, definitionDetailParam);
	}
}

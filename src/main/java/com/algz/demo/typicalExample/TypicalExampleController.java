package com.algz.demo.typicalExample;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cf611.util.ProTablePage;

@RestController
@RequestMapping("/typicalexample")
public class TypicalExampleController {

	
	
	/**
	 * 添加需求详,POST请求。
	 * (与 @PostMapping 效果相同)
	 * @param typicalExample
	 * @return
	 */
	@RequestMapping(path = "/addtypicalexample",method=RequestMethod.POST)
	public String addTypicalExample(TypicalExample typicalExample) {
		return null;//service.AddDefinitionDetail(detailParam);
	}
	
	/**
	 * 提交.
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
	 * 删除
	 *   export async function delDefinitionDetail(options?:any){
		    return request('/algz/typicalexample/deltypicalexample',{
		      method:'POST',
		      requestType:'form',//application/x-www-form-urlencoded
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

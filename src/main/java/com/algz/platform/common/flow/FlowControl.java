package com.algz.platform.common.flow;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algz.platform.utility.JsonUtils;

@RestController
@RequestMapping("/common/flow")
public class FlowControl {
	
	@RequestMapping("/uploadflowdata")
	private String uploadFlowData(String cells) {
		Flow.toFlow(cells);
		Map m=JsonUtils.jsonToMap(cells);
		Object obj=JsonUtils.jsonToClass(cells, Flow.class);
		return null;
	}

	@RequestMapping("/downloadflowdata")
	private String downloadFlowData() {
		return null;
	}
}

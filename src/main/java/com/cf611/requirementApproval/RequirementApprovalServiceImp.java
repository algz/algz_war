package com.cf611.requirementApproval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.common.file.pathencode.APathCode;
import com.algz.platform.common.file.pathencode.APathCodeRepository;
import com.algz.platform.utility.ADateUtils;
import com.algz.platform.utility.AFileUtils;
import com.algz.platform.utility.SpringSecurityUtils;
import com.algz.websocket.javax.WebSocketServer;
import com.cf611.approvalCommentManager.ApprovalComment;
import com.cf611.approvalCommentManager.ApprovalCommentService;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.requirementDefinition.RequirementDefinitionService;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirmentRegulation.office.ExcelEntity;
import com.cf611.requirmentRegulation.office.OfficeService;
import com.cf611.requirmentRegulation.view.DefinitionRegulationView;
import com.cf611.requirmentRegulation.view.DefinitionRegulationViewRepository;

@Transactional(readOnly = true)
@Service
public class RequirementApprovalServiceImp implements RequirementApprovalService {

	@Autowired
	private RequirementDefinitionService definitionService;

	@Autowired
	private ApprovalCommentService approvalCommentService;

	@Autowired
	private RequirementDefinitionService requirementDefinitionService;

	@Autowired
	private WebSocketServer webSocketServer;

	@Autowired
	private DefinitionRegulationViewRepository definitionRegulationViewRepository;

	@Autowired
	private APathCodeRepository aPathCodeRepository;

	@Autowired
	private OfficeService officeService;

	/**
	 * 审批通过后，发送需求任务给客户端。
	 */
	@Transactional
	@Override
	public String submitDefinition(ApprovalComment ac) {
		ac.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
		ac.setKind("2");
		definitionService.publishDefinition(ac.getDefinitionId(), ac.getApprovalResult().equals("1") ? "3" : "1", ac);
		if (ac.getApprovalResult().equals("1")) {
			// 通过
			Definition def = definitionService.getDefinition(ac.getDefinitionId());
			//推送消息
			webSocketServer.sendMsgToAllUser("push@" + ac.getDefinitionId());

			createRegulationExcel(def);
		}
		return null;
	}

	/**
	 * 创建判定excel文件
	 * @param definitionId
	 */
	private void createRegulationExcel(Definition def) {
		DefinitionRegulationView view = new DefinitionRegulationView();
		view.setDefinitionId(def.getId());
		List<DefinitionRegulationView> list = definitionRegulationViewRepository.findAll(Example.of(view));
		List<ExcelEntity> entityList = new ArrayList<ExcelEntity>();
		for (int i = 0; i < list.size(); i++) {
			DefinitionRegulationView v = list.get(i);
			ExcelEntity ee = new ExcelEntity();
			ee.setSerialNo((i + 1) + "");
			ee.setKindName(v.getKindName());
			ee.setSemanticsName(v.getSemanticsName());
			ee.setUserIndicatorName(v.getUserIndicatorName());
			ee.setIndicatorName(v.getIndicatorName());
			ee.setRegulationName(v.getRegulationName());
			ee.setComponentName(v.getComponentName());
			ee.setSubModelName(v.getSubModelName());
			ee.setSubModelPath(v.getSubModelPath());
			ee.setNewLine("1");
			entityList.add(ee);
		}

		// 获得持化久对象，不用save
//		def.setReceiver(SpringSecurityUtils.getCurrentUser().getUserid());
//		def.setReceiveDate(ADateUtils.getCurrentDateTime());
//		def.setState("3");
      //definitionRepository.save(def);

		String filestorePath = "/excel/" + def.getName() + ".xlsx";
		officeService.GetExcelFilePath(filestorePath, entityList);

		APathCode pathCode = new APathCode();
		pathCode.setRelationId(view.getDefinitionId());
		pathCode.setRelationKind("CF_DEFINITION_REGULATION_VIEW");
//				pathCode.setRemark();
		pathCode.setCreator(def.getReceiver());
		pathCode.setFilePath(filestorePath);
		pathCode.setFileName(AFileUtils.getFileFullName(filestorePath));
		aPathCodeRepository.save(pathCode);

		def.setRegulationFileId(pathCode.getId());
	}

	@Override
	public Map<String, Object> getDefinitionDetails(String definitonId) {
		Definition definition = requirementDefinitionService.getDefinition(definitonId);
		List<DefinitionDetail> list = requirementDefinitionService.getDefinitionDetailByDefinitionId(definitonId);
		ApprovalComment ac = new ApprovalComment();
		ac.setDefinitionId(definitonId);
		ac.setKind("2");
		List<ApprovalComment> acList = approvalCommentService.getApprovalCommentList(ac);
		StringBuilder str = new StringBuilder();

		if (!definition.getState().equals("2") && acList.size() > 0) {
			for (ApprovalComment act : acList) {
				str.append(act.getCreateDate() + ":" + (act.getApprovalResult().equals("1") ? "通过" : "不通过") + ":"
						+ (StringUtils.isEmpty(act.getApprovalComment()) ? "" : act.getApprovalComment()) + "\n");
			}
		} else {
			str.append(
					acList.size() > 0 && acList.get(0).getApprovalComment() != null ? acList.get(0).getApprovalComment()
							: "");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("approvalComment", str.toString());
		return map;
	}

}

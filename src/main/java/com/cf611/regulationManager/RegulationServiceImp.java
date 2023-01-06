package com.cf611.regulationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.utility.SpringBeanUtils;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.indicatorManager.Indicator;
import com.cf611.indicatorManager.IndicatorRepository;
import com.cf611.requirmentDataBase.semanticsBase.Semantics;
import com.cf611.requirmentDataBase.semanticsBase.SemanticsRepository;
import com.cf611.requirmentDataBase.semanticsBase.semanticsKind.SemanticsKind;
import com.cf611.util.ProTablePage;
import com.cf611.util.TreeNode;

@Service
@Transactional(readOnly = true)
public class RegulationServiceImp implements RegulationService {

	@Autowired
	private RegulationRepository repository;
	
	
	@Autowired
	private SemanticsRepository semanticsRepository;
	
	@Autowired
	private IndicatorRepository indicatorRepository;
	
	/**
	 * 获取规则列表
	 * @param pageParam
	 * @param regulationParam
	 * @return
	 */
	@Override
	public ProTablePage<Regulation> getRegulations(ProTablePage<Regulation> pageParam,Regulation regulationParam){
		try {
			Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
			Page<Regulation>page =  SpringBeanUtils.PageListMapToPageEntity(repository.getRegulations(regulationParam.getModelId(),pageable), pageable, Regulation.class);
			pageParam.setPage(page);
			return pageParam;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@Override
	public String addRegulation(Regulation regulation) {
		repository.save(regulation);
		return "";
	}
	
	@Transactional
	@Override
	public String updateRegulation(Regulation regulation) {
		Regulation reg=repository.findById(regulation.getId()).get();
		reg.setRegulationVal(regulation.getRegulationVal());
		repository.save(reg);
		return null;
	}

	@Transactional
	@Override
	public String delRegulation(Regulation regulationParam) {
		repository.deleteById(regulationParam.getId());
		return null;
	}

	@Override
	public List<TreeNode> getSemanticsIndicators(TreeNode nodeParam) {
		TreeNode root=new TreeNode();
		root.setKey("0");
		root.setChildren(new ArrayList<TreeNode>());
		if(nodeParam.getKey()!=null) {
			Semantics st=new Semantics();
			st.setKindId(nodeParam.getKey().substring(1));
			List<Semantics> kindList=semanticsRepository.findAll(Example.of(st));

			for(Semantics sk : kindList) {
				TreeNode pnode = new TreeNode("p"+sk.getId(), sk.getName());
				pnode.setCheckable(false);
				pnode.setChildren(new ArrayList<TreeNode>());
				root.getChildren().add(pnode);
				Indicator indicatorParam=new Indicator();
				indicatorParam.setSemanticsId(sk.getId());
				List<Indicator> indicatorList=indicatorRepository.findAll(Example.of(indicatorParam));
				for(Indicator it:indicatorList) {
					TreeNode cnode=new TreeNode(it.getId(),it.getName());
					cnode.setIsLeaf(true);
					pnode.getChildren().add(cnode);
					pnode.setCheckable(true);
				}
			}		
		}
		
		return root.getChildren();
	}

	@Transactional
	@Override
	public String saveRegulation(Regulation reg) {
		if(reg.getId().contains("tem")) {
			//添加
			reg.setId(null);
			repository.save(reg);
		}else {
			//修改
			repository.save(reg);
		}
		return null;
	}

	@Transactional
	@Override
	public void addRegulationIndicator(String regulationId, String indicatorId) {
		repository.addRegulationIndicator(regulationId, indicatorId);
		
	}

	@Transactional
	@Override
	public void delRegulationIndicator(String regulationId, String indicatorId) {
		repository.delRegulationIndicator(regulationId, indicatorId);
	}

	@Override
	public List<String> getRegulationIndicator(String regulationId) {
		return repository.getRegulationIndicator(regulationId, null);
	}
}

/**
 * 
 */
package com.algz.flow;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.SpringBeanUtils;
import com.algz.platform.utility.SpringSecurityUtils;
import com.cf611.approvalCommentManager.ApprovalComment;
import com.cf611.approvalCommentManager.ApprovalCommentService;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.definitionDetailManager.DefinitionDetailRepository;
import com.cf611.requirementDefinition.definition.Definition;
import com.cf611.requirementDefinition.definition.DefinitionRepository;
import com.cf611.requirementDefinition.definitionDetailView.DefinitionDetailView;
import com.cf611.requirementDefinition.definitionDetailView.DefinitionDetailViewRepository;
import com.cf611.requirementDefinition.definitionView.DefinitionView;
import com.cf611.requirementDefinition.definitionView.DefinitionViewRepository;
import com.cf611.util.ProTablePage;

/**
 * @author algz
 *
 */
@Service
@Transactional(readOnly = true) 
public class FlowManagerServiceImp implements FlowManagerService {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private FlowRepository repository;
	
	@Autowired
	private FlowViewRepository flowViewRepository;
	
	@Autowired
	private DefinitionDetailRepository definitionDetailRepository;
	
	@Autowired
	private DefinitionDetailViewRepository definitionDetailViewRepository;
	
	
	@Autowired
	private ApprovalCommentService approvalCommentService;
	
	/**
	 * 分页查询
	 */
	@Override
	public ProTablePage<FlowView> getFlowView(ProTablePage<FlowView> pageParam,FlowView definitionParam) {

		
//		排序
//		Sort sort= new Sort(Sort.Direction.ASC, "uid");
		//Pageable pageable = new PageRequest(pageIndex, pageSize,sort);
		//Pageable pageable = PageRequest.of(0, 10,Sort.by("createTime"));
		//参数1，分页需求设置，page为0，是从第一页，1是第二页；参数2，size 设置每页数据的条数;参数3，排序,字符串参数("createDate")是对象(definition)属性名称,大小写。
		Pageable pageable = PageRequest.of(pageParam.getCurrent() -1, pageParam.getPageSize(),Sort.by("createDate","name").descending());
		
		//直接使用匿名内部类实现接口
        Specification<FlowView> specification = new Specification<FlowView>() {
			private static final long serialVersionUID = 1L;

			@Override
            public Predicate toPredicate(Root<FlowView> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                //条件1：查询 tvName 为 海信 的数据，root.get 中的值与 TV 实体中的属性名称对应
//                if (definitionParam.getState() != null && !"".equals(definitionParam.getState())) {
//                    predicateList.add(cb.equal(root.get("state").as(String.class), definitionParam.getState()));
//                }
 
                //条件2：TV 生产日期（dateOfProduction）大于等于 start 的数据，root.get 中的 dateOfProduction 必须对应 TV 中的属性
                //predicateList.add(cb.greaterThanOrEqualTo(root.get("dateOfProduction").as(Date.class), start));
 
                //条件3：TV 生产日期（dateOfProduction）小于等于 end
                //predicateList.add(cb.lessThanOrEqualTo(root.get("dateOfProduction").as(Date.class), end));
 
                //1. where version=?
                if(definitionParam.getVersion()!=null) {
                	predicateList.add(cb.equal(root.get("version"), definitionParam.getVersion()));
                }
                
                //2.效果相当于 where (state in (?,?..) )
               if(definitionParam.getState()!=null) {
                   String[] stateArr=definitionParam.getState().split(",");
    			   //Path<Object> path = root.get("state");
    			   CriteriaBuilder.In<Object> in = cb.in(root.get("state"));//定义查询的字段
    			   for (int i = 0; i <stateArr.length ; i++) {
    			       in.value(stateArr[i]);//存入值
    			   }
    			   //predicateList.add(cb.and(cb.and(in)));//存入条件集合里
    			   predicateList.add(cb.and(in));//存入条件集合里
               }


                
                Predicate[] pre = new Predicate[predicateList.size()];
                pre = predicateList.toArray(pre);
                return query.where(pre).getRestriction();
            }

        };
        Page<FlowView> page=flowViewRepository.findAll(specification, pageable);
		
		pageParam.setPage(page);
		return pageParam;
	}

//	@Override
//	public ProTablePage<DefinitionDetail> getDefinitionDetails(ProTablePage<DefinitionDetail> pageParam,DefinitionDetail definitionDetail) {
//		
//		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
//		Page<DefinitionDetail> page= definitionDetailRepository.findAll(Example.of(definitionDetail),pageable);
//		pageParam.setPage(page);
//		return pageParam;
//	}
//
//	@Transactional
//	@Override
//	public String addDefinition(Definition definition) {
//		try {
//			Definition newDefinition=new Definition();
//			if(definition.getId()==null) {
//				//新增(definitiondetail:先insert,再update。sql语句变多，因为实体用了一对多的关联。)
//				definition.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
//				
//				newDefinition=repository.save(definition);
//				newDefinition.setTopId(newDefinition.getId());
//				//提交多的，则添加。
//				for(DefinitionDetail detail : definition.getDetailList()) {
//						detail.setDefinitionId(newDefinition.getId());
//						detail.setCreator(definition.getCreator());
//				}
//				
//				return "";
//			}else {
//				//更新
//				newDefinition=repository.findById(definition.getId()).get();
//				newDefinition.setName(definition.getName());
//				
//				DefinitionDetail tem=new DefinitionDetail();
//				tem.setDefinitionId(newDefinition.getId());
//				List<DefinitionDetail> originList=definitionDetailRepository.findAll(Example.of(tem));
//				for(DefinitionDetail origin : originList) {
//					if(definition.getDetailList().contains(origin)) {
//						definition.getDetailList().remove(origin);
//						continue;
//					}else {
//						definitionDetailRepository.deleteById(origin.getId());
//					}
//				}
//				//提交多的，则添加。
//				for(DefinitionDetail detail : definition.getDetailList()) {
//					if(detail.getId()==null) {
//						detail.setId(null);
//						detail.setDefinitionId(definition.getId());
//						detail.setCreator(SpringSecurityUtils.getCurrentUser().getUserid());
////						detail.setIndicatorId(null);
//						definitionDetailRepository.save(detail);
//					}
//				}
//				return null;
//			}
//			
//		}catch(Exception ex){
//			return ex.getLocalizedMessage();
//		}
//	}
//
//	/**
//	 * 如果想null属性值不更新,必需先查找出数据对象，然后再赋值给数据对象需要更新的值。
//	 */
//	@Transactional
//	@Override
//	public String publishDefinition(String definitionId,String state,ApprovalComment ac) {
//		if(!StringUtils.isEmpty(definitionId)) {
//			
//			Definition definition=repository.getOne(definitionId);
////			definition.setId(definitionId);
//			definition.setState(state);
//			repository.save(definition); 
////			ApprovalComment ac=new ApprovalComment();
//
//			approvalCommentService.saveApprovalComment(ac);
////			ac.setRelationId(null);
//			
//		}
//
//		return null;
//	}
//
//	
//	/**
//	 * 如果想null属性值不更新,必需先查找出数据对象，然后再赋值给数据对象需要更新的值。
//	 */
//	@Transactional
//	@Override
//	public String upgradeDefinition(String parentId,ApprovalComment ac) {
//		if(!StringUtils.isEmpty(parentId)) {
//			
//			Definition pDefinition=repository.getOne(parentId);
//			Definition definition=new Definition();
//			SpringBeanUtils.copyPropertiesForbidNull(pDefinition, definition);
//			definition.setId(null);
//			
////			definition.setId(definitionId);
//			definition.setState("1");
//			int cv=getMaxVersion(pDefinition.getTopId());
//			definition.setVersion(cv+"");
//			definition.setParentId(parentId);
//			definition.setTopId(pDefinition.getTopId()==null?pDefinition.getId():pDefinition.getTopId());
//			definition.setDetailList(new ArrayList<DefinitionDetail>());
//			repository.save(definition); 
//			for(DefinitionDetail pDetail : pDefinition.getDetailList()) {
//				DefinitionDetail detail=new DefinitionDetail();
//				SpringBeanUtils.copyPropertiesForbidNull(pDetail, detail);
//				detail.setId(null);
//				detail.setDefinitionId(definition.getId());
//				definitionDetailRepository.save(detail);
//			}
//			approvalCommentService.saveApprovalComment(ac);
//		}
//
//		return null;
//	}
//	
//	@Transactional
//	@Override
//	public String delDefinition(Definition definition) {
//		repository.deleteById(definition.getId());
//		return null;
//	}
//
//
//	@Override
//	public List<DefinitionDetailView> findDefinitionDetailViewByDefinitionId(String definitonId) {
//		return definitionDetailViewRepository.findDefinitionDetailViewByDefinitionId(definitonId);
////		List<Map<String,Object>> mapList=definitionDetailViewRepository.findAll(definitonId);
////		try {
////			return SpringBeanUtils.ListMapToListEntity(mapList, DefinitionDetailView.class);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////			return null;
////		}
//	}
//
//	@Transactional
//	@Override
//	public String delDefinitionDetail(String definitionDetailId) {
//		definitionDetailRepository.deleteById(definitionDetailId);
//		return null;
//	}
//
//	/**
//	 * 获取需求定义
//	 * @param definitionId
//	 * @return
//	 */
//	@Override
//	public Definition getDefinition(String definitionId) {
//		return repository.findById(definitionId).get();
//	}
//
//	
//	private Integer getMaxVersion(String topId) {
////		String sql="select topid from cf_definitions def where id='"+definitionId+"'";
////		String topId=(String) entityManager.createNativeQuery(sql).getSingleResult();
////		if(topId==null) {
////			//版本为1的数据，topId=null.
////			topId=definitionId;
////		}
//		String sql="select Nvl(Max(to_number(version)+1),2) version from cf_definitions def where topid='"+topId+"'";
//		BigDecimal max=(BigDecimal) entityManager.createNativeQuery(sql).getSingleResult();
////		if(max==null) {
////			sql="select Max(to_number(version)+1) version from cf_definitions def where topid='"+definitionId+"'";
////			max=(BigDecimal) entityManager.createNativeQuery(sql).getSingleResult();
////		}
//		return max.intValue();
//	}
//
//	@Transactional
//	@Override
//	public String saveDefinition(Definition definition) {
//		repository.save(definition);
//		return null;
//	}
}

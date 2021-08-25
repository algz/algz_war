package com.cf611.userManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.security.authority.roleManager.ARole;
import com.algz.platform.security.authority.roleManager.ARoleRepository;
import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.security.authority.userManager.AUserRepository;
import com.algz.platform.utility.JsonUtils;
import com.algz.platform.utility.SpringBeanUtils;
import com.algz.platform.utility.SpringSecurityUtils;
import com.cf611.definitionDetailManager.DefinitionDetail;
import com.cf611.util.ProTablePage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

	@Autowired
	private AUserRepository dao;

	@Autowired
	private ARoleRepository roleDao;

	@Override
	public UserVo loginAccount(UserVo vo) {
//		AUser user=new AUser();
//		user.setUsername(vo.getUserName());
//		user.setPassword(vo.getPassword());
//		user=dao.findOne(Example.of(user)).orElse(null);
//		if(user!=null) {
			vo.setStatus("ok");
			vo.setCurrentAuthority(new String[]{"ADMIN"});
//		}else {
//			
//		}
		return vo;
	}

	/**
	 * 获取当前登录用户
	 */
	@Override
	public CurrentUserVo getCurrentUser() {
		AUser user = SpringSecurityUtils.getCurrentUser();
		CurrentUserVo vo=new CurrentUserVo();
//		AUser user=dao.getOne(vo.getUserid());
		vo.transform(user);

		vo.setTitle("交互专家——标题");
		vo.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
		vo.setGroup("蚂蚁集团－某某某事业群－某某平台部－某某技术部－UED_group");
		vo.setSignature("signature");
		vo.setUnreadCount(11);
	    //email: 'antdesign@alipay.com海纳百川，有容乃大',
		return vo;
	}
	
	/**
	 * 获取用户列表
	 */
	@Override
	public ProTablePage<AUser> getUsers(ProTablePage<AUser> pageParam,AUser au){
		Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getPageSize());
		Page<Map<String,Object>> objPage=dao.findUsersView(pageable);
		try {
			Page<AUser>page = SpringBeanUtils.PageListMapToPageEntity(objPage, pageable, AUser.class);
			pageParam.setPage(page);
			return pageParam;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取用户
	 */
	@Override
	public AUser getUser(String userid) {
		//不能使用getOne();返回的是代理，不能序列化。
		return dao.findById(userid).get();
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	@Transactional
	@Override
	public String saveUser(AUser user) {
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
		user.setName(user.getName()==null?user.getUsername():user.getName());
		if(user.getUserid()==null) {
			//新增
			user.setPassword(bc.encode(user.getPassword()));
			dao.save(user);
			
			ARole role=new ARole();
			role.setAuthority(user.getAuthoritienName());
			role=roleDao.findOne(Example.of(role)).get();
			dao.addUserRole(user.getUserid(), role.getRoleid());
			
			
		}else {
			//更新
			AUser tem=dao.getOne(user.getUserid());
			tem.setName(user.getName());
			
			if(user.getPassword()!=null&&!user.getPassword().equals("")) {
				String str=bc.encode(user.getPassword());
				tem.setPassword(str);
			}

			dao.save(tem);
			dao.deleteUserRoleForUserid(user.getUserid());
			ARole role=new ARole();
			role.setAuthority(user.getAuthoritienName());
			role=roleDao.findOne(Example.of(role)).get();
			dao.addUserRole(tem.getUserid(), role.getRoleid());
			
		}
		return null;
	}
	
	/**
	 * 删除用户
	 */
	@Transactional
	@Override
	public String delUser(String id) {
		dao.deleteById(id);
		return null;
	}
	
	/**
	 * 获取所有权限信息
	 * @return
	 */
	@Override
	public String getRolesForSelectMap() {
		List<ARole> roleList=roleDao.findAll();
		List<Map<String,String>> mList=new ArrayList<Map<String,String>>();
		roleList.forEach(x->{
			Map<String,String> m=new HashMap<String,String>();
			m.put("label", x.getDescription());
			m.put("value", x.getAuthority());
			mList.add(m);
		});
		return JsonUtils.objectToJson(mList);
//        { label: '全部', value: 'all' },
//        { label: '未解决', value: 'open' },
//        { label: '已解决', value: 'closed' },
//        { label: '解决中', value: 'processing' },
		
	}
}

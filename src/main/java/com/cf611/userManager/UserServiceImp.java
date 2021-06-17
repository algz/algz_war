package com.cf611.userManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.security.authority.userManager.AUser;
import com.algz.platform.security.authority.userManager.AUserRepository;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

	@Autowired
	private AUserRepository dao;


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

	
	@Override
	public CurrentUserVo getCurrentUser(CurrentUserVo vo) {
//		AUser user=dao.getOne(cuv.getUserid());
//		cuv.transform(user);
		vo.setUserid("00000001");
		vo.setName("algz");
		vo.setTitle("交互专家——标题");
		vo.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
		vo.setGroup("蚂蚁集团－某某某事业群－某某平台部－某某技术部－UED_group");
		vo.setSignature("signature");
		vo.setUnreadCount(11);
	    //email: 'antdesign@alipay.com海纳百川，有容乃大',
		return vo;
	}
	
//	public void 
	
}

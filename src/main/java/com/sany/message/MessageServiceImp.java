package com.sany.message;

import java.awt.Desktop;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algz.platform.utility.JsonUtils;
import com.sany.dynamic.DynamicReponsitory;

@Service
@Transactional(readOnly = true) 
public class MessageServiceImp implements MessageService {

	@Autowired
	private MessageReponsitory msgRepository;
	
	@Override
	public void SendMessage(String msg) {
		//{task={taskname=SR450多体动力学仿真计算, editor=0000001, gk=[dlw, ts], cadname=桅杆动力学模型, cadversion=A1.1, cadfile=ftp://127.0.0.1/桅杆201010131000.zip, cadfilepassword=1234}}
		Map m=JsonUtils.jsonToMap(msg);
		Map task=(Map)m.get("task");
		
		//msgRepository.
	}

}

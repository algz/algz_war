package com.algz.platform.common.sql;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerationStrategy;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;



public class AIDGenerator extends UUIDGenerator  {
	
		@Override
		public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
			UUIDGenerator generator = this.buildSessionFactoryUniqueIdentifierGenerator();
			UUIDTypeDescriptor.ValueTransformer valueTransformer=UUIDTypeDescriptor.ToStringTransformer.INSTANCE;
			UUIDGenerationStrategy strategy=StandardRandomStrategy.INSTANCE;
			String s=valueTransformer.transform( strategy.generateUUID( session ) ).toString();
			return s.replace("-", "");
			//generator..valueTransformer.transform( strategy.generateUUID( session ) );
//			SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyyMMddHHmmss");
//	        Date date = new Date();
//	        String str = simpleDateFormat.format(date);
//	        Random random = new Random();
//	        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
//	        return   str+ rannum ;// 当前时间+随机数
	    }
}

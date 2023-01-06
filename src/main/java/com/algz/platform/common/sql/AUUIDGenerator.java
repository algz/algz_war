package com.algz.platform.common.sql;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * 暂没使用。
 * @Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.saving.ecm.resource.entity.KeyUtils",
	parameters = { @Parameter(name = "dataCenterID", value = "123456") ,@Parameter(name = "idLength", value = "10")})
	
 * @author algz
 *
 */
public class AUUIDGenerator extends AbstractUUIDGenerator implements Configurable {

	public String dataCenterID;
	public String idLength;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// Date today = new Date();
		// SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddhhmmss");
		// String time = formatDate.format(today);
		return dataCenterID + "|" + idLength;
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		this.dataCenterID = params.getProperty("dataCenterID");
		this.idLength = params.getProperty("idLength");
	}

}

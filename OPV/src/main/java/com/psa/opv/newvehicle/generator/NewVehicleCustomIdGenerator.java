package com.psa.opv.newvehicle.generator;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

/**
 * @author VARMA This Class generating Custom Id for NewVehicle class customized
 *         generator id,here not generating id based on sequence,generating on
 *         table(newvehicle_sql). Sequence generator reading value form
 *         table(newvehicle_sql) not sequence.
 */
public class NewVehicleCustomIdGenerator extends SequenceStyleGenerator {
	// prefix related part ---New_Veh_
	public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
	public static final String VALUE_PREFIX_DEFAULT = "";
	private String valuePrefix;
	// format related part---- how many zeros required
	public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	public static final String NUMBER_FORMAT_DEFAULT = "%d";
	private String numberFormat;

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		super.configure(LongType.INSTANCE, params, serviceRegistry);
		valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
		numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return valuePrefix + String.format(numberFormat, super.generate(session, object));

	}

}

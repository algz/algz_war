package com.algz.platform.common.sql;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

public class AliasToEntityMapResultTransformer1 extends AliasedTupleSubsetResultTransformer {

	public static final AliasToEntityMapResultTransformer1 INSTANCE = new AliasToEntityMapResultTransformer1();
	
	/**
	 * Disallow instantiation of AliasToEntityMapResultTransformer.
	 */
	private  AliasToEntityMapResultTransformer1() {

	}

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map result = new LinkedHashMap(tuple.length);
		for ( int i=0; i<tuple.length; i++ ) {
			String alias = aliases[i];
			if ( alias!=null ) {
				result.put( alias, tuple[i] );
			}
		}
		return result;
	}

	@Override
	public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
		// TODO Auto-generated method stub
		return false;
	}






	/**
	 * Serialization hook for ensuring singleton uniqueing.
	 *
	 * @return The singleton instance : {@link #INSTANCE}
	 */
	private Object readResolve() {
		return INSTANCE;
	}
	
}

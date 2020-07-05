package com.simplesteph.kafka.Validators;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;

public class StringValidator  implements ConfigDef.Validator {

	@Override
	public void ensureValid(String name, Object value) {
		if(value == null) {
			 throw new ConfigException(name, value, "Batch Size must be a positive integer that's less or equal to 100");
		}

	}
}

package com.bbodeum.config;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class ExtendedOracleDialect extends Oracle10gDialect{
	
	public ExtendedOracleDialect() {
        registerFunction( "second", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "to_number(to_char(?1,'SS'))") );
        registerFunction( "minute", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "to_number(to_char(?1,'MI'))") );
        registerFunction( "hour", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "to_number(to_char(?1,'HH24'))") );
        registerFunction( "dayofweek", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "to_number(to_char(?1,'dy'))") );
    }
}

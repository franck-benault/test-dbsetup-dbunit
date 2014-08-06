package net.franckbenault.dbtest.sample;

import static com.ninja_squad.dbsetup.Operations.*;


import com.ninja_squad.dbsetup.operation.Operation;

public class DBSetupCommonOperations {
	
    public static final Operation DELETE_ALL = 
        deleteAllFrom("USERS");
    
    
    public static final Operation INSERT_USERS_DATA =
                insertInto("USERS")
                    .columns("ID","LOGIN", "PASSWORD", "DEACTIVATION_DATE")
                    .values(1L,"root", "pwd", DateUtil.getTomorrow())
                    .values(2L,"guest", "pwd",  DateUtil.getTomorrow())
                    .values(3L,"guest", "pwd", DateUtil.getYesterday())
                    .build();

}

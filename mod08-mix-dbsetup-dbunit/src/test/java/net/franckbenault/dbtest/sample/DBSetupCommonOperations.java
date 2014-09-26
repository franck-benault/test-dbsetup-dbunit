package net.franckbenault.dbtest.sample;

import static com.ninja_squad.dbsetup.Operations.*;

import com.ninja_squad.dbsetup.operation.Operation;

public class DBSetupCommonOperations {
	
    public static final Operation DELETE_ALL = 
            deleteAllFrom("USERS");
        
        
        public static final Operation INSERT_USERS_DATA =
                    insertInto("USERS")
                        .columns("ID","LOGIN", "PASSWORD")
                        .values(4L,"root2", "pwd")
                        .values(5L,"guest2", "pwd")
                        .values(6L,"guest3", "pwd")
                        .build();

}

package net.franckbenault.dbtest.sample;

import static com.ninja_squad.dbsetup.Operations.*;

import com.ninja_squad.dbsetup.operation.Operation;

public class CommonOperations {
	
    public static final Operation DELETE_ALL = 
        deleteAllFrom("USERS");
    
    public static final Operation INSERT_REFERENCE_DATA =
                insertInto("USERS")
                    .columns("ID", "LOGIN", "PASSWORD")
                    .values(1L, "root", "pwd")
                    .values(2L, "guest", "pwd").build();

}

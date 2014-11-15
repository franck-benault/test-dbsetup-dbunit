package net.franckbenault.dbtest.bug31;

import static com.ninja_squad.dbsetup.Operations.*;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

public class DBSetupCommonOperations {
	
    public static final Operation DELETE_ALL = 
            deleteAllFrom("buildings");
        
        
        public static final Operation INSERT_BUILDING_DATA =
        		   Operations.insertInto("buildings")
        	          .columns("id", "bldgNr", "name")
        	          .values(1L, "1", "Dormitory No. 1")
        	          .build();

}

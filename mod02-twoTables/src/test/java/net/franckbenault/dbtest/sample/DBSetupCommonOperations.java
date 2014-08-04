package net.franckbenault.dbtest.sample;

import static com.ninja_squad.dbsetup.Operations.*;

import com.ninja_squad.dbsetup.operation.Operation;

public class DBSetupCommonOperations {
	
    public static final Operation DELETE_ALL = 
        deleteAllFrom("ADDRESS","PERSONS");
    
    public static final Operation INSERT_PERSONS_DATA =
                insertInto("PERSONS")
                    .columns("ID", "FIRST_NAME", "LAST_NAME")
                    .values(1L, "Sahra", "Smith")
                    .values(2L, "Wim", "Smith")
                    .values(3L, "Sahra", "Dupond")
                    .values(4L, "William", "Smith")
                    .build();
    
    public static final Operation INSERT_ADDRESS_DATA =
            insertInto("ADDRESS")
                .columns("ID", "TOWN", "STREET", "ID_PERSON")
                .values(1L, "LYON", "rue Saint Vincent",1L)
                .values(2L, "LYON", "rue Saint Martin",1L)
                .build();

}

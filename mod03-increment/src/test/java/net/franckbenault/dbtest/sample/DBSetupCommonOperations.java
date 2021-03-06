package net.franckbenault.dbtest.sample;

import static com.ninja_squad.dbsetup.Operations.*;

import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;

public class DBSetupCommonOperations {
	
    public static final Operation DELETE_ALL = 
        deleteAllFrom("USERS");
    
    public static final Operation INSERT_USERS_DATA =
                insertInto("USERS")
                	.withGeneratedValue("ID", ValueGenerators.sequence().startingAt(100L).incrementingBy(10))
                    .columns("LOGIN", "PASSWORD")
                    .values("root", "pwd")
                    .values("guest", "pwd").build();

    public static final Operation INSERT_USERS_DATA_REPEATING =
    insertInto("USERS")
    .withGeneratedValue("ID", ValueGenerators.sequence().startingAt(1L))
    .withGeneratedValue("LOGIN", ValueGenerators.stringSequence("user-").startingAt(1L))
    .withGeneratedValue("PASSWORD", ValueGenerators.stringSequence("pwd-").startingAt(1L))
    .columns("DESCRIPTION")
    .repeatingValues("fake description").times(10)
    .build();
    
}

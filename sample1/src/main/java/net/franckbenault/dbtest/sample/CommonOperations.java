package net.franckbenault.dbtest.sample;

import static com.ninja_squad.dbsetup.Operations.*;

import com.ninja_squad.dbsetup.operation.Operation;

public class CommonOperations {
    public static final Operation DELETE_ALL = 
        deleteAllFrom("PRODUCT", "VENDOR", "COUNTRY", "USER");

}

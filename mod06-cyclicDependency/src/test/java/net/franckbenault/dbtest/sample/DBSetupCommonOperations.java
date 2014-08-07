package net.franckbenault.dbtest.sample;

import static com.ninja_squad.dbsetup.Operations.*;


import com.ninja_squad.dbsetup.operation.Operation;

public class DBSetupCommonOperations {
	
    public static final Operation DELETE_ALL = 
        deleteAllFrom("VENDOR","PRODUCT");
    
    

    
    public static final Operation insertVendorsAndProducts = 
            sequenceOf(
                insertInto("VENDOR")
                    .columns("ID", "VCODE", "NAME")
                    .values(1L, "AMA", "AMAZON")
                    .build(),
                insertInto("PRODUCT")
                    .columns("ID", "NAME", "VENDOR_ID")
                    .values(1L, "Kindle", 1L)
                    .build(),
                sql("update VENDOR set FEATURED_PRODUCT_ID = 1 where ID = 1"));

}

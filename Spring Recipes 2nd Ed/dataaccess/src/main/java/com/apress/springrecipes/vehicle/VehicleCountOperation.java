package com.apress.springrecipes.vehicle;

import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;


public class VehicleCountOperation extends SqlFunction {
    public VehicleCountOperation(DataSource dataSource) {
        setDataSource(dataSource);
        setSql("SELECT COUNT(*) FROM VEHICLE");
        compile();
    }

    public int perform() {
        return run();
    }
}

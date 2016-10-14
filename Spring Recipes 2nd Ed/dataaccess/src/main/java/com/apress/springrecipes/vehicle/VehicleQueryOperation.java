package com.apress.springrecipes.vehicle;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;


public class VehicleQueryOperation extends MappingSqlQuery {
    public VehicleQueryOperation(DataSource dataSource) {
        setDataSource(dataSource);
        setSql("SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?");
        declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }

    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNo(rs.getString("VEHICLE_NO"));
        vehicle.setColor(rs.getString("COLOR"));
        vehicle.setWheel(rs.getInt("WHEEL"));
        vehicle.setSeat(rs.getInt("SEAT"));

        return vehicle;
    }

    public Vehicle perform(String vehicleNo) {
        return (Vehicle) findObject(new Object[] { vehicleNo });
    }
}

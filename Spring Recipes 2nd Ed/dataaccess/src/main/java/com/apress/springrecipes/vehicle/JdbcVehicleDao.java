package com.apress.springrecipes.vehicle;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JdbcVehicleDao extends SimpleJdbcDaoSupport implements VehicleDao {
    public void insert(Vehicle vehicle) {
        String sql = "INSERT INTO VEHICLE (VEHICLE_NO, COLOR, WHEEL, SEAT) " + "VALUES (:vehicleNo, :color, :wheel, :seat)";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(vehicle);
        getSimpleJdbcTemplate().update(sql, parameterSource);
    }

    public void insertBatch(List<Vehicle> vehicles) {
        String sql = "INSERT INTO VEHICLE (VEHICLE_NO, COLOR, WHEEL, SEAT) " + "VALUES (:vehicleNo, :color, :wheel, :seat)";
        List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

        for (Vehicle vehicle : vehicles) {
            parameters.add(new BeanPropertySqlParameterSource(vehicle));
        }

        getSimpleJdbcTemplate().batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
    }
                                                                                    
    public Vehicle findByVehicleNo(String vehicleNo) {
        String sql = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?";

        BeanPropertyRowMapper<Vehicle> vehicleRowMapper = BeanPropertyRowMapper.newInstance(Vehicle.class);

        Vehicle vehicle = getSimpleJdbcTemplate().queryForObject(sql, vehicleRowMapper, vehicleNo);

        return vehicle;
    }

    public void update(Vehicle vehicle) {
        String sql = "UPDATE VEHICLE SET COLOR = :color, WHEEL = :wheel, SEAT=:seat WHERE VEHICLE_NO=:vehicleNo";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(vehicle);
        getSimpleJdbcTemplate().update(sql, parameterSource);
    }

    public void delete(Vehicle vehicle) {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("vehicleNo", vehicle.getVehicleNo());

        String sql = "DELETE FROM VEHICLE WHERE VEHICLE_NO = :vehicleNo";
        getSimpleJdbcTemplate().update(sql, args);
    }

    public List<Vehicle> findAll() {
        String sql = "SELECT * FROM VEHICLE";
        RowMapper<Vehicle> rm = ParameterizedBeanPropertyRowMapper.newInstance(Vehicle.class);
        List<Vehicle> vehicles = getSimpleJdbcTemplate().query(sql, rm);

        return vehicles;
    }

    public String getColor(String vehicleNo) {
        String sql = "SELECT COLOR FROM VEHICLE WHERE VEHICLE_NO = ?";

        String color = getSimpleJdbcTemplate().queryForObject(sql, String.class, vehicleNo);

        return color;
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM VEHICLE";

        int count = getJdbcTemplate().queryForInt(sql);

        return count;
    }
}

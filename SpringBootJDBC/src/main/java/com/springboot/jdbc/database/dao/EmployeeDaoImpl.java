package com.springboot.jdbc.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.jdbc.database.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insertEmployee(Employee emp) {
		String sql = "INSERT INTO employee " + "(empId, empName) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] { emp.getEmpId(), emp.getEmpName() });
	}

	@Override
	public void insertEmployees(List<Employee> employees) {
		String sql = "INSERT INTO employee " + "(empId, empName) VALUES (?, ?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setString(1, employee.getEmpId());
				ps.setString(2, employee.getEmpName());
			}

			public int getBatchSize() {
				return employees.size();
			}
		});

	}

	@Override
	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		List<Employee> result = new ArrayList<Employee>();
		for(Map<String, Object> row:rows){
			Employee emp = new Employee();
			emp.setEmpId((String)row.get("empId"));
			emp.setEmpName((String)row.get("empName"));
			result.add(emp);
		}
		
		return result;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Employee getEmployeeById(String empId) {
		String sql = "SELECT * FROM employee WHERE empId = ?";
		return (Employee)jdbcTemplate.queryForObject(sql, new Object[]{empId}, new RowMapper<Employee>(){
			@Override
			public Employee mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Employee emp = new Employee();
				emp.setEmpId(rs.getString("empId"));
				emp.setEmpName(rs.getString("empName"));
				return emp;
			}
		});
	}

}

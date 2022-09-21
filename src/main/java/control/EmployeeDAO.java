package control;

import control.db.DBConnection;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAO {
    public Employee employee;
    public static void addEmp(Employee employee){
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO employee(EMP_ID,EMP_NAME,EMP_NO,HIRE_DATE,IMAGE,JOB,SALARY,DEPT_ID,MNG_ID) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, employee.getEmpId());
            ps.setString(2, employee.getEmpName());
            ps.setString(3, employee.getEmpNo());
            ps.setDate(4, new java.sql.Date(employee.getHireDate().getTime()));
            ps.setBytes(5, employee.getImage());
            ps.setString(6, employee.getJob());
            ps.setFloat(7, employee.getSalary());
            ps.setInt(8, employee.getDeptId());
            ps.setLong(9, employee.getMngId().longValue());
            ps.executeUpdate();
            conn.close();

        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void updateEmp(Employee employee){
        try{
            Connection conn= DBConnection.getConnection();
            String query = "UPDATE EMPLOYEE set \"+\n" +
                    "\t\t\t\t\"EMP_NAME = ?,\"+\n" +
                    "\t\t\t\t\"EMP_NO = ?,\"+\n" +
                    "\t\t\t\t\"HIRE_DATE = ?,\"+\n" +
                    "\t\t\t\t\"IMAGE= ?,\"+\n" +
                    "\t\t\t\t\"JOB= ?,\"+\n" +
                    "\t\t\t\t\"SALARY = ?,\"+\n" +
                    "\t\t\t\t\"DEPT_ID = ?,\"+\n" +
                    "\t\t\t\t\"MNG_ID= ? \"+\n" +
                    "\t\t\t\t\"Where EMP_ID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, employee.getEmpName());
            ps.setString(2, employee.getEmpNo());
            ps.setDate(3, new java.sql.Date(employee.getHireDate().getTime()));
            ps.setBytes(4, employee.getImage());
            ps.setString(5, employee.getJob());
            ps.setFloat(6, employee.getSalary());
            ps.setInt(7, employee.getDeptId());
            ps.setLong(8, employee.getMngId().longValue());
            ps.setInt(9, employee.getEmpId());
            ps.executeUpdate();
            conn.close();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployee(String employee){
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM employee WHERE EMP_NAME = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, employee);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package org.escalandojava.computerapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.escalandojava.computerapp.beans.Company;
import org.escalandojava.computerapp.conexionbd.MySqlConection;

public class CompanyDAO {
	
	
	
	public List getCompanies(){
		
		List companies =  new ArrayList();
		Connection con =  new MySqlConection().getConection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			
			pst = con.prepareStatement("select * from company");
			rs = pst.executeQuery();
				
			while(rs.next()){
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				companies.add(company);
				System.out.println(company);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return companies;
	}
	
	
	
public Company getCompanyByName(String nombre){
		
	    Company company = null;
		Connection con =  new MySqlConection().getConection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			
			pst = con.prepareStatement("select * from company where name = ?");
			pst.setString(1, nombre);
			rs = pst.executeQuery();
				
			while(rs.next()){
				company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return company;
	}




public void addCompany(Integer id, String nombre){
	
	Connection con =  new MySqlConection().getConection();
	PreparedStatement pst = null;
	
	try {
		
		pst = con.prepareStatement("insert into company values (?,?)");
		pst.setInt(1, id);
		pst.setString(2, nombre);
		pst.executeUpdate();
			
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
		try {
			if(pst != null) pst.close();
			if(con != null) con.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
}


}

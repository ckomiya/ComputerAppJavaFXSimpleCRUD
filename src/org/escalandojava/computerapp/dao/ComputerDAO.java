package org.escalandojava.computerapp.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.escalandojava.computerapp.beans.Company;
import org.escalandojava.computerapp.beans.Computer;
import org.escalandojava.computerapp.conexionbd.MySqlConection;

public class ComputerDAO {

	
	public static void main(String[] args){
		
		new ComputerDAO().getComputers();
	}	
	
	public List getComputers(){
		
		List computers =  new ArrayList();
		Connection con =  new MySqlConection().getConection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			
			pst = con.prepareStatement(
					"select pc.*, com.name nombreCompania from computer pc, company com "
					+ "where pc.company_id = com.id order by pc.id");
			rs = pst.executeQuery();
				
			while(rs.next()){
				Computer computer = new Computer();
				
				computer.setId(rs.getInt("id"));
				computer.setNombre(rs.getString("name"));
				computer.setFechaInicioProduccion(rs.getDate("introduced"));
				computer.setFechaFinProduccion(rs.getDate("discontinued"));
				computer.setPrecio(rs.getBigDecimal("price"));
				
				Integer companyId = (Integer) rs.getObject("company_id");
				computer.setCompanyId(companyId );
				
				computer.setNombreCompania(rs.getString("nombreCompania"));
				
				
				
				computers.add(computer);
				
				//System.out.println(computer);
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
		
		return computers;
	}

	
	public boolean saveComputer(Computer computer) {
		
		Connection con =  new MySqlConection().getConection();
		PreparedStatement pst = null;
		
		Integer valorRetorno = null;
		
		try {
			
			Calendar cal = Calendar.getInstance();
			pst = con.prepareStatement("insert into computer values (?,?,?,?,?,?)");
			pst.setInt(1, computer.getId());
			pst.setString(2, computer.getNombre());
			pst.setDate(3, computer.getFechaInicioBD());
			pst.setDate(4, computer.getFechaFinBD());
			pst.setInt(5, computer.getCompanyId());
			pst.setBigDecimal(6, computer.getPrecio());
		
			valorRetorno = pst.executeUpdate();
				
		
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
		
		return (valorRetorno != 0);
	}

	
	/**
	 * Obtiene las computadoras por nombre
	 * @param nombre
	 * @return
	 */
	
	public List getComputadorasPorNombre(String nombre) {
		
		List computers =  new ArrayList();
		Connection con =  new MySqlConection().getConection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			
			pst = con.prepareStatement(
					"select pc.*, com.name nombreCompania from computer pc, company com "
					+ " where pc.company_id = com.id and (pc.name like ? or com.name like ?)"
					+ " order by pc.id");
			
			pst.setString(1, "%" + nombre + "%");
			pst.setString(2, "%" + nombre + "%");
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				Computer computer = new Computer();
				
				computer.setId(rs.getInt("id"));
				computer.setNombre(rs.getString("name"));
				computer.setFechaInicioProduccion(rs.getDate("introduced"));
				computer.setFechaFinProduccion(rs.getDate("discontinued"));
				computer.setPrecio(rs.getBigDecimal("price"));
				
				Integer companyId = (Integer) rs.getObject("company_id");
				computer.setCompanyId(companyId );
				
				computer.setNombreCompania(rs.getString("nombreCompania"));
				
				
				
				computers.add(computer);
				
				//System.out.println(computer);
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
		
		return computers;
	}

	public List getComputadorasPorFechas(Date fecha1, Date fecha2) {
		

		List computers =  new ArrayList();
		Connection con =  new MySqlConection().getConection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			
			pst = con.prepareStatement(
					"select pc.*, com.name nombreCompania from computer pc, company com "
					+ " where pc.company_id = com.id and"
					+ " pc.introduced between ? and ?");
			
			pst.setDate(1, new java.sql.Date(fecha1.getTime()));
			pst.setDate(2,  new java.sql.Date(fecha2.getTime()));
			
			rs = pst.executeQuery();
				
			while(rs.next()){
				Computer computer = new Computer();
				
				computer.setId(rs.getInt("id"));
				computer.setNombre(rs.getString("name"));
				computer.setFechaInicioProduccion(rs.getDate("introduced"));
				computer.setFechaFinProduccion(rs.getDate("discontinued"));
				computer.setPrecio(rs.getBigDecimal("price"));
				
				Integer companyId = (Integer) rs.getObject("company_id");
				computer.setCompanyId(companyId );
				
				computer.setNombreCompania(rs.getString("nombreCompania"));
				
				
				
				computers.add(computer);
				
				//System.out.println(computer);
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
		
		return computers;
	}

	public Integer getIdNuevo() {
		
		Integer id = null ;
		Connection con =  new MySqlConection().getConection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select (max(id) + 1) myId ");
			sql.append(" from computer ");
			
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
				
			while(rs.next()){
				id = rs.getInt("myId");
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
		
		return id;
	}

	public void updateComputer(Computer computer) {
		
		Connection con =  new MySqlConection().getConection();
		PreparedStatement pst = null;
		
		//Integer valorRetorno = null;
		
		try {
			
			//Calendar cal = Calendar.getInstance();
			StringBuilder sb = new StringBuilder();
			sb.append(" update computer ");
			sb.append(" set name = ?, ");
			sb.append(" introduced = ?, ");
			sb.append(" discontinued = ?, ");
			sb.append(" company_id = ?, ");
			sb.append(" price = ? ");
			sb.append(" where id = ? ");
			
			pst = con.prepareStatement(sb.toString());
				
			pst.setString(1, computer.getNombre());
			pst.setDate(2, computer.getFechaInicioBD());
			pst.setDate(3, computer.getFechaFinBD());
			pst.setInt(4, computer.getCompanyId());
			pst.setBigDecimal(5, computer.getPrecio());
			pst.setInt(6, computer.getId());
		
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

	


package org.escalandojava.computerapp.beans;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Computer {

	private Integer id;
	private String nombre;
	private Date fechaInicioProduccion;
	private Date fechaFinProduccion;
	private BigDecimal precio;
	private Integer companyId;
	private String nombreCompania;
	
	SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
	NumberFormat nfSoles = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-PE"));
	
	
	public Computer(){
		
	}
	

	public Computer(Integer id, String nombre, Date fechaInicioProduccion,
			Date fechaFinProduccion, BigDecimal precio, Integer companyId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicioProduccion = fechaInicioProduccion;
		this.fechaFinProduccion = fechaFinProduccion;
		this.precio = precio;
		this.companyId = companyId;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaInicioProduccion() {
		return fechaInicioProduccion;
	}
	public void setFechaInicioProduccion(Date fechaInicioProduccion) {
		this.fechaInicioProduccion = fechaInicioProduccion;
	}
	public Date getFechaFinProduccion() {
		return fechaFinProduccion;
	}
	public void setFechaFinProduccion(Date fechaFinProduccion) {
		this.fechaFinProduccion = fechaFinProduccion;
	}

	
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	public String getNombreCompania(){
		return this.nombreCompania;
	}
	
	public void setNombreCompania(String nombreCompania){
		this.nombreCompania = nombreCompania;
	}
	
	
    ///  ---  Manejo de Formatos
	
	public String getFechaInicioProduccionFormat() {
		return fechaInicioProduccion == null ? null : sdf.format(fechaInicioProduccion);
	}
	
	public String getFechaFinProduccionFormat() {
		return fechaFinProduccion == null ? null : sdf.format(fechaFinProduccion);
	}
	
	public String getPrecioEnSoles(){
		return precio == null ? null : nfSoles.format(precio.doubleValue());
	}
	
	
	@Override
	public String toString() {
		return "Computer [id=" + id + ", nombre=" + nombre
				+ ", fechaInicioProduccion=" + fechaInicioProduccion
				+ ", fechaFinProduccion=" + fechaFinProduccion + ", precio="
				+ precio + ", companyId=" + companyId + "]";
	}
	
	
	// fechas BD
	
	public java.sql.Date getFechaInicioBD(){
		return fechaInicioProduccion == null ? null :
			new java.sql.Date(fechaInicioProduccion.getTime());
	}
	
	public java.sql.Date getFechaFinBD(){
		
		return fechaFinProduccion == null ? null :
			new java.sql.Date(fechaFinProduccion.getTime());
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	
	
	
}

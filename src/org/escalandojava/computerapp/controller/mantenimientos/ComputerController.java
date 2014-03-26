package org.escalandojava.computerapp.controller.mantenimientos;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.escalandojava.computerapp.beans.Company;
import org.escalandojava.computerapp.beans.Computer;
import org.escalandojava.computerapp.dao.CompanyDAO;
import org.escalandojava.computerapp.dao.ComputerDAO;
import org.escalandojava.computerapp.util.Validaciones;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ComputerController extends Application {

	
	TableView<Computer> computerTableView = new TableView<>();
	Stage escenario;
	ComboBox<Company> cboCompany;
	TextField txtId = new TextField();
	TextField txtNombre = new TextField();
	TextField txtFechaInicio = new TextField();
	TextField txtFechaFin = new TextField();
	TextField txtPrecio = new TextField();
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
			
		escenario = primaryStage;
		escenario.setTitle("Mantenimiento de computadoras ");
		Group root = new Group();
		Scene scene = new Scene(root, 1200, 500, Color.WHITE);
	
		SplitPane splitPane = new SplitPane();
		splitPane.setPrefSize(scene.widthProperty().doubleValue(), scene.widthProperty().doubleValue());
		splitPane.setOrientation(Orientation.HORIZONTAL);
		splitPane.setDividerPosition(0, 0.60);

		root.getChildren().add(splitPane);
	
		BorderPane leftGridPane =  getListComputerPane();	
		GridPane rightGridPane = getComputerFormPane();
	
		splitPane.getItems().addAll(leftGridPane, rightGridPane);
	
		escenario.setScene(scene);
		escenario.show();
	}
	
	private BorderPane getListComputerPane(){
		
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(30));
	
		computerTableView.setPrefHeight(400);
		computerTableView.setMaxWidth(870);
		loadTable();
			
		TableColumn<Computer, String> idCol = new TableColumn("Id");
		idCol.setCellValueFactory(new PropertyValueFactory("id"));
		idCol.setPrefWidth(50);
		
		TableColumn<Computer, String> nameCol = new TableColumn("Nombre");
		nameCol.setCellValueFactory(new PropertyValueFactory("nombre"));
		nameCol.setPrefWidth(300);
		
		TableColumn<Computer, Date> fechaInicioCol = new TableColumn("Inicio de Produccion");
		fechaInicioCol.setCellValueFactory(new PropertyValueFactory("fechaInicioProduccionFormat"));
		fechaInicioCol.setPrefWidth(100);
		
		TableColumn<Computer, Date> fechaFinCol = new TableColumn("Fin de Produccion");
		fechaFinCol.setCellValueFactory(new PropertyValueFactory("fechaFinProduccionFormat"));
		fechaFinCol.setPrefWidth(100);
		
		TableColumn<Computer, Double> precioCol = new TableColumn("Precio");
		precioCol.setCellValueFactory(new PropertyValueFactory("precioEnSoles"));
		precioCol.setPrefWidth(100);
		
		TableColumn<Computer, Integer> companyIdCol = new TableColumn("Compañía");
		companyIdCol.setCellValueFactory(new PropertyValueFactory("nombreCompania"));
		companyIdCol.setPrefWidth(200);
				
		computerTableView.getColumns().setAll(idCol, nameCol, fechaInicioCol, fechaFinCol, precioCol, companyIdCol);		
		bp.setTop(computerTableView);
	
		computerTableView.
		   setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				Computer computer = computerTableView.getSelectionModel().
						               getSelectedItem();
				
				txtId.setText(computer.getId().toString());
				txtNombre.setText(computer.getNombre());
				txtFechaInicio.setText(computer.getFechaInicioProduccionFormat());
				txtFechaFin.setText(computer.getFechaFinProduccionFormat());
				
				if(computer.getPrecio() == null){
					txtPrecio.setText("");
				}else{
					txtPrecio.setText(computer.getPrecio().toString());
				}
				
				
				Company company = new Company();
				company.setId(computer.getCompanyId());
				//company.setName(computer.getNombreCompania());
				
				cboCompany.getSelectionModel().select(company);
				
				
			}
			
		});
		
		
		return bp;
	}
	
	
	private GridPane getComputerFormPane(){
		
		GridPane gp = new GridPane();
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
		gp.setPadding(new Insets(5));
		gp.setHgap(5);
		gp.setVgap(5);

		Label lblId = new Label("Id");
		Label lblNombre = new Label("Nombre");
		Label lblFechaInicio = new Label("Fecha Inicio");
		Label lblFechaFin = new Label("Fecha Fin");
		Label lblPrecio = new Label("Precio");
		Label lblCompanyId = new Label("Compañía");
		
		
		
		
		txtId.setDisable(true);
		
		List<Company> companies = new CompanyDAO().getCompanies();
		
		ObservableList<Company> obsCompanies = 
				FXCollections.observableArrayList(companies);
		
		cboCompany = new ComboBox<Company>(obsCompanies);
		
		cboCompany.setConverter(new StringConverter<Company>() {

			@Override
			public Company fromString(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString(Company company) {
				// TODO Auto-generated method stub
				return company.getName();
			}
		});
		
		txtId.setMaxWidth(50);

		
		HBox botones = new HBox(10);
		
		Button btnNuevo = new Button("Nuevo");
		Button btnEliminar = new Button("Eliminar");	
		Button btnGrabar = new Button("Grabar");
		
		botones.getChildren().add(btnNuevo);
		botones.getChildren().add(btnEliminar);
		botones.getChildren().add(btnGrabar);
		
		GridPane.setHalignment(botones, HPos.RIGHT);

		gp.add(lblId, 0, 1);
		gp.add(lblNombre, 0, 2);
		gp.add(lblFechaInicio, 0, 3);
		gp.add(lblFechaFin, 0, 4);
		gp.add(lblPrecio, 0, 5);
		gp.add(lblCompanyId, 0, 6);

		gp.add(txtId, 1, 1);
		gp.add(txtNombre, 1, 2);
		gp.add(txtFechaInicio, 1, 3);
		gp.add(txtFechaFin, 1, 4);
		gp.add(txtPrecio, 1, 5);
		gp.add(cboCompany, 1, 6);
		gp.add(botones, 1, 7);
		
		// Boton Nuevo
		btnNuevo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				txtNombre.setText("");
				txtFechaInicio.setText("");
				txtFechaFin.setText("");
				txtPrecio.setText("");
				cboCompany.getSelectionModel().selectFirst();
				
				Integer id = new ComputerDAO().getIdNuevo();
				
				txtId.setText(id.toString());
				
			}
			
		});
		
		
		// Botono Grabar
		btnGrabar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					
					if(!Validaciones.esDatoNumerico(txtId.getText().trim())){
						JOptionPane.showMessageDialog(null, "El id debe ser un dato numérico");
						return;
					}
					
					if(!Validaciones.esCadenaVacia(txtFechaInicio.getText()) && !Validaciones.esDatoFormatoFecha(txtFechaInicio.getText(), "dd/MM/yyyy") ){
						JOptionPane.showMessageDialog(null, "La fecha inicio debe tener el formato dd/MM/yyyy");
						return;
					}
					
					if(!Validaciones.esCadenaVacia(txtFechaFin.getText())  && !Validaciones.esDatoFormatoFecha(txtFechaFin.getText(), "dd/MM/yyyy")  ){
						JOptionPane.showMessageDialog(null, "La fecha fin debe tener el formato dd/MM/yyyy");
						return;
					}
					
					if(!Validaciones.esCadenaVacia(txtPrecio.getText()) && !Validaciones.esDatoDecimal(txtPrecio.getText())){
						JOptionPane.showMessageDialog(null, "El precio debe ser un dato entero o decimal");
						return;
					}
					
						
					Integer id = new Integer(txtId.getText());
					String nombre =  txtNombre.getText();
					Date fechaInicio = Validaciones.esCadenaVacia(txtFechaInicio.getText()) ? null : sdf.parse(txtFechaInicio.getText());
					Date fechaFin =  Validaciones.esCadenaVacia(txtFechaFin.getText()) ? null : sdf.parse(txtFechaFin.getText());
					BigDecimal precio = Validaciones.esCadenaVacia(txtPrecio.getText()) ? null : new BigDecimal(txtPrecio.getText());
					Integer companyId = cboCompany.getSelectionModel()
											.getSelectedItem().getId();
				
					
				Computer computer = new Computer(id, nombre, 
						fechaInicio, fechaFin, precio, companyId);
				
				
				boolean existeEnGrid = computerTableView.getItems().
							contains(computer);
				
				if(existeEnGrid){
					// Actualiza el registro
					new ComputerDAO().updateComputer(computer);
					JOptionPane.showMessageDialog(null, "Actualización exitosa!");

				}else{
					// Inserta el registro
					new ComputerDAO().saveComputer(computer);
					JOptionPane.showMessageDialog(null, "Inserción exitosa!");
				}
				
				
				
				loadTable();
				
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				
				 			}
		});
		
		return gp;
		
	}
	
	
	private void loadTable(){
	    
		ObservableList<Computer> computerList = FXCollections.observableArrayList();	
		
		List computers = new ComputerDAO().getComputers();
		
		computerTableView.setItems(null);
		computerTableView.layout();
		
		computerList.addAll(computers);
		
		computerTableView.setItems(computerList);
		
	}
}

package pe.edu.upeu.sysventas.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.components.ColumnInfo;
import pe.edu.upeu.sysventas.components.TableViewHelper;
import pe.edu.upeu.sysventas.components.Toast;
import pe.edu.upeu.sysventas.components.ToltipCustom;
import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.enums.TipoFuncion;
import pe.edu.upeu.sysventas.model.Producto;
import pe.edu.upeu.sysventas.service.IPeliculaService;
import pe.edu.upeu.sysventas.service.ISalaService;
import pe.edu.upeu.sysventas.service.IProductoService;
import pe.edu.upeu.sysventas.service.IProductoCineService;

import java.util.*;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ProductoController {
    private final ISalaService ms;
    private final IPeliculaService cs;
    private final IProductoCineService ums;
    private final IProductoService ps;

    @FXML
    ComboBox<ComboBoxOption> cbxTipoFuncion;
    @FXML
    ComboBox<ComboBoxOption> cbxPelicula, cbxSala, cbxProductoCine;
    @FXML TextField txtNombreUsuario, txtPUnit,
            txtPUnitOld, txtUtilidad, txtStock, txtStockOld, txtFiltroDato;

    @FXML
    private TableView<Producto> tableView;
    ObservableList<Producto> litarProducto;

    Producto formulario;
    Long idProductoCE = 0L;

    @FXML Label lbnMsg;
    @FXML private AnchorPane miContenedor;
    Stage stage;
    private Validator validator;
    private final ToltipCustom ttc=new ToltipCustom();

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            stage = (Stage) miContenedor.getScene().getWindow();
            System.out.println("El título del stage es: " + stage.getTitle());
        });
        System.out.println("Holassss");

        cbxTipoFuncion.getItems().addAll(ps.listarTipoProducto());

        cbxPelicula.getItems().addAll(cs.lisCategoria());
        cbxSala.getItems().addAll(ms.listarCombobox());
        cbxProductoCine.getItems().addAll(ums.listarCombobox());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        TableViewHelper<Producto> tableViewHelper = new TableViewHelper<>();
        LinkedHashMap<String, ColumnInfo> columns = new LinkedHashMap<>();
        columns.put("ID Prod.", new ColumnInfo("idProducto", 60.0));
        columns.put("ID Producto.", new ColumnInfo("tiipoProducto", 150.0));
        columns.put("Nombre .", new ColumnInfo("nombre", 200.0));
        columns.put("P. Unitario", new ColumnInfo("pu", 150.0));
        columns.put("Utilidad", new ColumnInfo("utilidad", 100.0));
        columns.put("pelicula", new ColumnInfo("idPelicula.nombre", 200.0));
        columns.put("sala", new ColumnInfo("idSala.nombre", 200.0));

        Consumer<Producto> updateAction = p -> {
            editForm(p);
            idProductoCE=p.getIdProducto();
        };
        Consumer<Producto> deleteActtion = p -> {
            System.out.println(p.getIdProducto());
            ps.delete(p.getIdProducto());
            Stage stage=(Stage)miContenedor.getScene().getWindow();
            double w = stage.getWidth() / 1.5, h = stage.getHeight() / 2;
            Toast.showToast(stage, "Se eliminó correctamente!!", 2000, w, h);
            listar();
        };

        tableViewHelper.addColumnsInOrderWithSize(tableView, columns, updateAction, deleteActtion);
        tableView.setTableMenuButtonVisible(true);
        listar();
    }
    public void listar(){
        try {
            tableView.getItems().clear();
            litarProducto= FXCollections.observableArrayList(ps.findAll());
            tableView.getItems().addAll(litarProducto);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Llego"+stage.getTitle());
    }
    @FXML
    public void validarFormulario() {
        formulario = new Producto();
        formulario.setNombre(txtNombreUsuario.getText());
        formulario.setPu(parseDoubleSafe(txtPUnit.getText()));
        formulario.setPuold(parseDoubleSafe(txtPUnitOld.getText()));
        formulario.setUtilidad(parseDoubleSafe(txtUtilidad.getText()));
        formulario.setStock(parseDoubleSafe(txtStock.getText()));
        formulario.setStockold(parseDoubleSafe(txtStockOld.getText()));

        String idxTP = cbxTipoFuncion.getSelectionModel().getSelectedItem() == null ? ""
                : cbxTipoFuncion.getSelectionModel().getSelectedItem().getKey();
        formulario.setTiipoProducto(idxTP.equals("") ? null : TipoFuncion.valueOf(idxTP));

        String idxM = cbxSala.getSelectionModel().getSelectedItem() == null ? "0"
                : cbxSala.getSelectionModel().getSelectedItem().getKey();
        formulario.setIdSala(idxM.equals("0") ? null : ms.findByid(Long.parseLong(idxM)));

        String idxC = cbxPelicula.getSelectionModel().getSelectedItem() == null ? "0"
                : cbxPelicula.getSelectionModel().getSelectedItem().getKey();
        formulario.setIdPelicula(idxC.equals("0") ? null : cs.findByid(Long.parseLong(idxC)));

        String idxUM = cbxProductoCine.getSelectionModel().getSelectedItem() == null ? "0"
                : cbxProductoCine.getSelectionModel().getSelectedItem().getKey();
        formulario.setIdProductoCine(idxUM.equals("0") ? null : ums.findByid(Long.parseLong(idxUM)));


        Set<ConstraintViolation<Producto>> violaciones = validator.validate(formulario);
        List<ConstraintViolation<Producto>> violacionesOrdenadas = violaciones.stream()
                .sorted(Comparator.comparing(v -> v.getPropertyPath().toString())).toList();

        if (violacionesOrdenadas.isEmpty()) {
            procesarFormulario();

        } else {
            mostrarErroresValidacion(violacionesOrdenadas);
        }

    }

    private void mostrarErroresValidacion(List<ConstraintViolation<Producto>> violaciones) {
        limpiarError();
        Map<String, Control> campos = new LinkedHashMap<>();
        campos.put("nombre", txtNombreUsuario);
        campos.put("tipoFuncion", cbxTipoFuncion);
        campos.put("pu", txtPUnit);
        campos.put("puold", txtPUnitOld);
        campos.put("unidad", txtUtilidad);
        campos.put("stock", txtStock);
        campos.put("stockold", txtStockOld);
        campos.put("idpelicula", cbxPelicula);
        campos.put("idsala", cbxSala);
        campos.put("idProductoCine", cbxProductoCine);

        LinkedHashMap<String, String> erroresOrdenados = new LinkedHashMap<>();
        final Control[] primerCtrl = {null};
        for (String campo : campos.keySet()) {
            violaciones.stream()
                    .filter(v -> v.getPropertyPath().toString().equals(campo))
                    .findFirst().ifPresent(v -> {

                        erroresOrdenados.put(campo, v.getMessage());

                        Control c = campos.get(campo);
                        if (c != null && !c.getStyleClass().contains("text-field-error")){
                            //c.getStyleClass().add("text-field-error");
                            if (c != null) ttc.marcarError(c, v.getMessage().trim());
                        }
                        if (primerCtrl[0] == null) primerCtrl[0] = c;
                    });
        }
        if (!erroresOrdenados.isEmpty()) {
            lbnMsg.setText(erroresOrdenados.entrySet().iterator().next().getValue());
            lbnMsg.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            if (primerCtrl[0] != null) Platform.runLater(primerCtrl[0]::requestFocus);
        }
    }
    private void procesarFormulario() {
        lbnMsg.setText("Formulario válido");
        lbnMsg.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
        Stage stage=(Stage)miContenedor.getScene().getWindow();
        limpiarError();
        double w = stage.getWidth() / 1.5, h = stage.getHeight() / 2;
        if (idProductoCE > 0L) {
            formulario.setIdProducto(idProductoCE);
            ps.update(idProductoCE, formulario);
            Toast.showToast(stage, "Se actualizó correctamente!!", 2000, w, h);
        } else {
            ps.save(formulario);
            Toast.showToast(stage, "Se guardó correctamente!!", 2000, w, h);
        }
        clearForm(); listar();
    }
    private double parseDoubleSafe(String value) {
        if (value == null || value.trim().isEmpty()) return 0.0;
        try { return Double.parseDouble(value.trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }

    public void editForm(Producto producto) {
        txtNombreUsuario.setText(producto.getNombre());

        txtPUnit.setText(producto.getPu().toString());
        txtPUnitOld.setText(producto.getPuold().toString());
        txtUtilidad.setText(producto.getUtilidad().toString());
        txtStock.setText(producto.getStock().toString());
        txtStockOld.setText(producto.getStockold().toString());

        cbxTipoFuncion.getSelectionModel().select(
                cbxTipoFuncion.getItems().stream()
                        .filter(m -> m.getKey() == producto.getTiipoProducto().name())
                        .findFirst().orElse(null));

        cbxPelicula.getSelectionModel().select(
                cbxPelicula.getItems().stream()
                        .filter(m -> Long.parseLong(m.getKey()) == producto.getIdSala().getIdSala())
                        .findFirst().orElse(null));
        cbxSala.getSelectionModel().select(
                cbxSala.getItems().stream()
                        .filter(c -> Long.parseLong(c.getKey()) == producto.getIdPelicula().getIdPeliculas())
                        .findFirst().orElse(null));
        cbxProductoCine.getSelectionModel().select(
                cbxProductoCine.getItems().stream()
                        .filter(u -> Long.parseLong(u.getKey()) == producto.getIdProductoCine().getIdProductoCine())
                        .findFirst().orElse(null));
        idProductoCE = producto.getIdProducto();
        limpiarError();
    }
    public void limpiarError() {
        List.of(txtNombreUsuario,
                        cbxTipoFuncion,
                        txtPUnit, txtPUnitOld, txtUtilidad,
                        txtStock, txtStockOld, cbxPelicula, cbxSala, cbxProductoCine)
                .forEach(c -> {c.getStyleClass().remove("text-field-error");
                    ttc.limpiarCampo(c);
                });
    }

    public void clearForm() {
        txtNombreUsuario.clear();
        cbxTipoFuncion.getSelectionModel().clearSelection();
        txtPUnit.clear(); txtPUnitOld.clear();
        txtUtilidad.clear(); txtStock.clear(); txtStockOld.clear();
        cbxPelicula.getSelectionModel().clearSelection();
        cbxSala.getSelectionModel().clearSelection();
        cbxProductoCine.getSelectionModel().clearSelection();
        idProductoCE = 0L; limpiarError();
    }

}

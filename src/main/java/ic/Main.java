package ic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter a number");
        double a = sc.nextDouble();
        sc.close();
        TemperatureConverter converter = new TemperatureConverter();
        System.out.println("fahrenheit to celsius");
        System.out.println(converter.fahrenheitToCelsius(a));
        System.out.println("kelvin to celsius");
        System.out.println(converter.kelvinToCelsius(a));
        System.out.println("celsius to fahrenheit");
        System.out.println(converter.celsiusToFahrenheit(a));
        System.out.println("is extreme temperature");
        System.out.println(converter.isExtremeTemperature(a));
    }*/

    private final TemperatureDAO TemperatureDAO = new TemperatureDAO();

    private TextField originalDegreeField;
    private TextField convertedDegreeField;
    private ComboBox<TemperatureType> originalTypeComboBox;
    private ComboBox<TemperatureType> convertedTypeComboBox;
    private Label resultLabel;
    private TableView<Temperature> tableView;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Temperature Converter");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(15));

        originalDegreeField = new TextField();
        convertedDegreeField = new TextField();
        originalTypeComboBox = new ComboBox<>();
        convertedTypeComboBox = new ComboBox<>();
        loadTemperatures();

        Button calcButton = new Button("Calculate & Save");
        resultLabel = new Label();

        form.add(new Label("Temperature Unit:"), 0, 0);
        form.add(originalTypeComboBox, 1, 0);
        form.add(new Label("Temperature degree:"), 0, 1);
        form.add(originalDegreeField, 1, 1);
        form.add(new Label("Conversion Unit:"), 0, 2);
        form.add(convertedTypeComboBox, 1, 2);
        form.add(calcButton, 1, 3);
        form.add(resultLabel, 1, 4);

        tableView = buildTableView();
        loadRecords();

        calcButton.setOnAction(e -> handleCalculateAndSave());

        VBox root = new VBox(15, form, new Label("Saved Records:"), tableView);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.TOP_LEFT);

        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    private void loadTemperatures() {
        try {
            List<TemperatureType> types = TemperatureTypeDAO.getAllTypes();
            originalTypeComboBox.getItems().addAll(types);
            convertedTypeComboBox.getItems().addAll(types);
            if (!types.isEmpty()) {
                originalTypeComboBox.getSelectionModel().selectFirst();
                convertedTypeComboBox.getSelectionModel().selectFirst();
            }
        } catch (SQLException e) {
            showError("Failed to load degree types: " + e.getMessage());
        }
    }

    private void handleCalculateAndSave() {
        try {
            double originalDegree = Double.parseDouble(originalDegreeField.getText());
            double convertedDegree = 0;
            String originalSelectedType = originalTypeComboBox.getValue().getTypeName().toString();
            String convertedSelectedType = convertedTypeComboBox.getValue().getTypeName().toString();

            if (originalSelectedType == null || convertedSelectedType == null) {
                showError("Please select degree types.");
                return;
            }

            List<TemperatureType> types = originalTypeComboBox.getItems();
            String C = types.get(0).getTypeName().toString();
            String F = types.get(1).getTypeName().toString();
            String K = types.get(2).getTypeName().toString();
            
            if (originalSelectedType == C && convertedSelectedType == F) {
                convertedDegree = TemperatureConverter.celsiusToFahrenheit(originalDegree);
            } else if (originalSelectedType == F && convertedSelectedType == C) {
                convertedDegree = TemperatureConverter.fahrenheitToCelsius(originalDegree);
            } else if (originalSelectedType == K && convertedSelectedType == C) {
                convertedDegree = TemperatureConverter.kelvinToCelsius(originalDegree);
            } else {
                System.out.println(originalSelectedType + " == " + C + " " + (originalSelectedType == C));
                System.out.println(originalSelectedType.getClass().getName() + " " + C.getClass().getName());
                showError("bad combo");
                return;
            }

            //double time = TemperatureConverter.timeCal(speed, distance);
            resultLabel.setText(String.format("Temperature: %.2f", convertedDegree));

            Boolean extreme = false;
            if (originalSelectedType != C && convertedSelectedType == C) {
                    extreme = TemperatureConverter.isExtremeTemperature(convertedDegree);
            } else if (originalSelectedType == C && convertedSelectedType != C) {
                extreme = TemperatureConverter.isExtremeTemperature(originalDegree);
            }
            

            Temperature temp = new Temperature(originalSelectedType, originalDegree, extreme, convertedSelectedType, convertedDegree);
            TemperatureDAO.save(temp);

            loadRecords();
            originalDegreeField.clear();
            convertedDegreeField.clear();

        } catch (NumberFormatException ex) {
            showError("Temperature degree must be numeric.");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        } catch (SQLException ex) {
            showError("Database error: " + ex.getMessage());
        }
    }

    private TableView<Temperature> buildTableView() {
        TableView<Temperature> table = new TableView<>();

        TableColumn<Temperature, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()));

        TableColumn<Temperature, String> originalTypeCol = new TableColumn<>("Original Type");
        originalTypeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getOriginalType()));

        TableColumn<Temperature, Number> originalDegreeCol = new TableColumn<>("Original Degree");
        originalDegreeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getOriginalDegree()));

        TableColumn<Temperature, String> convertedTypeCol = new TableColumn<>("Converted Type");
        convertedTypeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getConvertedType()));

        TableColumn<Temperature, Number> convertedDegreeCol = new TableColumn<>("Converted Degree");
        convertedDegreeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getConvertedDegree()));

        TableColumn<Temperature, Boolean> extremeCol = new TableColumn<>("Extreme");
        extremeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().getExtreme()));

        table.getColumns().addAll(idCol, originalTypeCol, originalDegreeCol, convertedTypeCol, convertedDegreeCol, extremeCol);
        return table;
    }

    private void loadRecords() {
        try {
            List<Temperature> records = TemperatureDAO.getHistory();
            tableView.getItems().setAll(records);
        } catch (SQLException e) {
            showError("Failed to load records: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        try {
            while (!DB.ensureDBExists()) {
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        launch(args);
    }
}

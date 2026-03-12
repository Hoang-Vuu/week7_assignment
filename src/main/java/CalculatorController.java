import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML private TextField number1Field;
    @FXML private TextField number2Field;
    @FXML private Label resultLabel;

    @FXML
    private void onCalculateClick() {
        try {
            double num1 = Double.parseDouble(number1Field.getText());
            double num2 = Double.parseDouble(number2Field.getText());

            double sum = num1 + num2;
            double product = num1 * num2;

            // Subreact (Subtract)
            double difference = num1 - num2;

            // Division (handle divide-by-zero)
            Double division = (num2 == 0) ? null : (num1 / num2);

            String divisionText = (division == null) ? "undefined (divide by zero)" : String.valueOf(division);

            resultLabel.setText(
                    "Sum: " + sum +
                            ", Product: " + product +
                            ", Difference: " + difference +
                            ", Division: " + divisionText
            );

            // Save to DB (store NULL when divide-by-zero)
            ResultService.saveResult(num1, num2, sum, product, difference, division);

        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers!");
        }
    }
}
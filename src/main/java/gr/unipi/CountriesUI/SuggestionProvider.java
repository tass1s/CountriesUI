package gr.unipi.CountriesUI;

import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.List;
import java.util.stream.Collectors;

public class SuggestionProvider {
	public static void createAutoCompletePopup(TextField textField, List<String> suggestions) {
		Popup popup = new Popup();
		ListView<String> listView = new ListView<>();
		listView.setItems(FXCollections.observableList(suggestions));

		listView.setCellFactory(TextFieldListCell.forListView(new StringConverter<String>() {
			@Override
			public String toString(String object) {
				return object;
			}

			@Override
			public String fromString(String string) {
				return string;
			}
		}));

		listView.setOnMouseClicked(event -> {
			textField.setText(listView.getSelectionModel().getSelectedItem());
			popup.hide();
		});

		listView.setOnKeyReleased(event -> {
			if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
				String input = textField.getText();
				listView.setItems(FXCollections.observableList(suggestions.stream()
						.filter(s -> s.toLowerCase().startsWith(input.toLowerCase())).collect(Collectors.toList())));
				popup.show(textField.getScene().getWindow());
			}
		});
		
		

		popup.getContent().add(listView);
		popup.setAutoHide(true);

		Callback<ListView<String>, ListCell<String>> forListView = TextFieldListCell.forListView();
		listView.setCellFactory(forListView);
		listView.setPrefWidth(textField.getWidth());

		popup.show(textField.getScene().getWindow(), textField.localToScreen(textField.getBoundsInLocal()).getMinX(),
				textField.localToScreen(textField.getBoundsInLocal()).getMaxY());
		
		
	}
	
	
}
module gr.unipi.CountriesUI {
	requires javafx.controls;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.databind;
	requires org.apache.httpcomponents.httpcore;
	requires org.apache.httpcomponents.httpclient;
	requires CountriesData;
	requires javafx.graphics;

	// Open package to Jackson Databind
	opens gr.unipi.CountriesUI to com.fasterxml.jackson.databind;

	exports gr.unipi.CountriesUI to javafx.graphics;
}

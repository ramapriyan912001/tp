package seedu.awe.ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import static java.util.Objects.requireNonNull;

public class LogoDisplay extends UiPart<Region> {

    private static final String FXML = "LogoDisplay.fxml";

    @FXML
    private ImageView logoDisplay;

    public LogoDisplay(String url) {
        super(FXML);
        Image image = new Image(url);
        logoDisplay.setImage(image);
    }

}

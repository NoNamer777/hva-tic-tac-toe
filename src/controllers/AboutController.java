package controllers;

import javafx.scene.control.TextArea;

/**
 * Logica van het "over"-scherm
 *
 * @author HvA HBO-ICT
 */
public class AboutController {

    private TextArea message;

    public void initialize() {
        message.setText("This application is made by:" +
                "\n Miguel Korn, 500806177, IS103 &" +
                "\n Oscar Wellner, 500806660, IS103");
    }

    public void setMessage(TextArea message) {
        this.message = message;
    }
}

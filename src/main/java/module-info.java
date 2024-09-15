module me.group.cceproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens me.group.cceproject to javafx.fxml;
    opens me.group.cceproject.controllers to javafx.fxml;

    exports me.group.cceproject;
    exports me.group.cceproject.controllers;
}
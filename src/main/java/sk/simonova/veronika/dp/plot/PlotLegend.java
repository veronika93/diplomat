package sk.simonova.veronika.dp.plot;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class PlotLegend {

    public static VBox buildLegend(List<Item> items) {
        Node[] nodes = new Node[items.size()];
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            CheckBox checkBox = new CheckBox(item.getTitle());
            checkBox.setOnMouseClicked(event -> item.getOnClick().onClick());
            checkBox.setSelected(true);
            checkBox.getStyleClass().add(item.getCssClass());
            nodes[i] = checkBox;
        }
        return new VBox(3f, nodes);
    }

    public static class Item {
        private String cssClass;
        private String title;
        private OnClick onClick;

        public Item(String cssClass, String title, OnClick onClick) {
            this.cssClass = cssClass;
            this.title = title;
            this.onClick = onClick;
        }

        public String getCssClass() {
            return cssClass;
        }

        public String getTitle() {
            return title;
        }

        public OnClick getOnClick() {
            return onClick;
        }
    }

    interface OnClick {
        void onClick();
    }
}

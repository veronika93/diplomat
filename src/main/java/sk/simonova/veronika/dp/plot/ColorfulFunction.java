package sk.simonova.veronika.dp.plot;

import javafx.scene.paint.Paint;

import java.util.function.Function;

public class ColorfulFunction {
    private Paint paint;
    private Function<Double, Double> function;
    private boolean reverseAxis;
    private String name;

    public ColorfulFunction(Function<Double, Double> function, Paint paint) {
        this(function, paint, false, "");
    }

    public ColorfulFunction(Function<Double, Double> function, Paint paint, boolean reverseAxis) {
        this(function, paint, reverseAxis, "");
    }

    public ColorfulFunction(Function<Double, Double> function, Paint paint, boolean reverseAxis, String name) {
        this.paint = paint;
        this.function = function;
        this.reverseAxis = reverseAxis;
        this.name = name;
    }

    public ColorfulFunction(Function<Double, Double> function, Paint paint, String name) {
        this(function, paint, false, name);
    }

    public String getName() {
        return name;
    }

    public Paint getPaint() {
        return paint;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    public boolean isReverseAxis() {
        return reverseAxis;
    }
}

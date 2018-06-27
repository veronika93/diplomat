package sk.simonova.veronika.dp.plot;

import javafx.scene.paint.Paint;

import java.util.function.Function;

public class ColorfulFunction {
    private Paint paint;
    private Function<Double, Double> function;
    private boolean reverseAxis;

    public ColorfulFunction(Function<Double, Double> function, Paint paint) {
        this.paint = paint;
        this.function = function;
    }

    public ColorfulFunction(Function<Double, Double> function, Paint paint, boolean reverseAxis) {
        this.paint = paint;
        this.function = function;
        this.reverseAxis = reverseAxis;
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

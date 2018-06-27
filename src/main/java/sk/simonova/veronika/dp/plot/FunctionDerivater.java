package sk.simonova.veronika.dp.plot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionDerivater {

    public static Result derivate(FunctionParser.Result what) {

        if (what.getMocnina() == 1) {
            return new Result(what.getNasobok(), 0);
        } else {
            return new Result(what.getNasobok() * what.getMocnina(), what.getMocnina() - 1);
        }
    }

    public static class Result {
        private double nasobok;
        private double mocnina;

        public Result(double nasobok, double mocnina) {
            this.nasobok = nasobok;
            this.mocnina = mocnina;
        }

        public double getNasobok() {
            return nasobok;
        }

        public double getMocnina() {
            return mocnina;
        }
    }

    public static void main(String[] args) {
        Result r = FunctionDerivater.derivate(new FunctionParser.Result(1, 3));
        System.out.println(r.getNasobok() + "k^" + r.getMocnina());

        r = FunctionDerivater.derivate(new FunctionParser.Result(1, 1));
        System.out.println(r.getNasobok() + "k^" + r.getMocnina());

        System.out.println(Math.pow(-12, 0));
    }
}

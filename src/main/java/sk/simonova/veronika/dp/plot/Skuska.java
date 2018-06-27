package sk.simonova.veronika.dp.plot;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;

import java.util.function.Function;

public class Skuska {
    public static void run (double alfa,double delta,double r, String vztah,String kapital, String cistenie,int znamienko){


        FunctionParser.Result vysledokVztah;
        FunctionParser.Result vysledokKapital;
        FunctionParser.Result vysledokCistenie;
        try {
            vysledokVztah =  FunctionParser.parse(vztah);
            vysledokKapital = FunctionParser.parse(kapital);
            vysledokCistenie = FunctionParser.parse(cistenie);
        }catch (IllegalArgumentException e){
            //todo show error
            return;
        }
        vysledokCistenie = new FunctionParser.Result(vysledokCistenie.getNasobok()*Math.pow(vysledokVztah.getNasobok(),vysledokCistenie.getMocnina()),vysledokCistenie.getMocnina()*vysledokVztah.getMocnina());
        FunctionDerivater.Result pk = FunctionDerivater.derivate(vysledokKapital);
        FunctionDerivater.Result pa = FunctionDerivater.derivate(vysledokCistenie);

        System.out.println(vztah);
        System.out.println(pk.getNasobok()+"    "+pk.getMocnina() );
        System.out.println(pa.getNasobok()+"    "+pa.getMocnina());

        UnivariateFunction function = x -> r + delta - (alfa*Math.pow(x,alfa-1))-(znamienko*(((pk.getNasobok()*(Math.pow(x,pk.getMocnina())))/(pa.getNasobok()*(Math.pow(x,pa.getMocnina()))))));

        System.out.println(function.value(4));


        UnivariateSolver solver = new BracketingNthOrderBrentSolver(1.0e-12, 1.0e-8, 100);
        double c =solver.solve(100, function, -10.0, 10.0, 0);
    }

    public static void main(String[] args) {
        Double alfa = 0.5;
        Double delta =0.5;
        Double r = 0.1;
        int znamienko = -1;
        String kapital= "k^3";
        String cistenie ="2*a";
        String vztah = "k^2";

        run(alfa,delta,r,vztah,kapital,cistenie,znamienko);
    }
}

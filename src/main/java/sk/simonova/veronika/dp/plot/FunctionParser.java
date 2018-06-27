package sk.simonova.veronika.dp.plot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionParser {

    public static Result parse(String what){
        String dacoKratK = "(\\d+.?\\d*) *\\* *[ka]";
        String dacoKratKNaDaco = "(\\d+.?\\d*) *\\* *[ka] *\\^ *(\\d+\\.?\\d*)";
        String kNaDaco = "[ka] *\\^ *(\\d+\\.?\\d*)";

        Pattern pattern = Pattern.compile(dacoKratK);
        Matcher matcher = pattern.matcher(what);
        if(matcher.matches()) {
            return new Result(Double.parseDouble(matcher.group(1)), 1);
        }

        pattern = Pattern.compile(dacoKratKNaDaco);
        matcher = pattern.matcher(what);
        if(matcher.matches()){
            return new Result(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)));
        }

        pattern = Pattern.compile(kNaDaco);
        matcher = pattern.matcher(what);
        if(matcher.matches()){
            return new Result(1, Double.parseDouble(matcher.group(1)));
        }
        if(what.trim().equalsIgnoreCase("k")){
            return new Result(1, 1);
        }

        throw new IllegalArgumentException();
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
}

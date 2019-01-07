package pe.gob.osce.rnp.seg.utils;

public class ThatsWhyWePlay {

    private static final String hashSchema = "RoNsPce";

    public static void main(String[] args){
        String rucHash = Parseador.getEncodeHash32Id(hashSchema, Long.valueOf("21602293085"));
        System.out.println(rucHash);
    }
}

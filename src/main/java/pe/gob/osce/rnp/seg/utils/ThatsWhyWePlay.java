package pe.gob.osce.rnp.seg.utils;

import pe.gob.osce.rnp.seg.model.jpa.dto.ProExtNoDom;
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;

import java.util.ArrayList;
import java.util.List;

public class ThatsWhyWePlay {

    private static final String hashSchema = "RoNsPce";

    public static void main(String[] args){
        String rucHash = Parseador.getEncodeHash32Id(hashSchema, Long.valueOf("21602293085"));
        System.out.println(rucHash);
        List<ProExtNoDom> optLst = new ArrayList<>();
        optLst.add(new ProExtNoDom("10484292308", "AAAAAAAAAAAA"));
        optLst.add(new ProExtNoDom("20602293085", "BBBBBBBBBBBB"));
        optLst.add(new ProExtNoDom("20603540558", "CCCCCCCCCCCC"));
    }
}

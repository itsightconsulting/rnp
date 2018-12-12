package pe.gob.osce.rnp.seg.svc;

import pe.gob.osce.rnp.seg.generic.BaseService;
import pe.gob.osce.rnp.seg.model.jpa.Card;

public interface CardService extends BaseService<Card> {

    Integer getSimpleSumaSp(int n1, int n2);
}
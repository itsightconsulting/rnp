package pe.gob.osce.rnp.seg.utils;

public class  Tuple<X, Y, Z> {
    public final X item1;
    public final Y item2;
    public final Z item3;

    public Tuple(X item1, Y item2, Z item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
}
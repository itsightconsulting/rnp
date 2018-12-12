package pe.gob.osce.rnp.seg.generic;


public abstract class BaseServiceImpl<T> {

    protected T repository;

    public BaseServiceImpl(T repository) {
        // TODO Auto-generated constructor stub
        this.repository = repository;
    }

}
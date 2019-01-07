package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osce.rnp.seg.dao.ParametroRepository;
import pe.gob.osce.rnp.seg.generic.BaseServiceImpl;
import pe.gob.osce.rnp.seg.model.jpa.Parametro;
import pe.gob.osce.rnp.seg.svc.ParametroService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParametroServiceImpl extends BaseServiceImpl<ParametroRepository> implements ParametroService {

    public ParametroServiceImpl(ParametroRepository repository) {
        super(repository);
    }

    @Override
    public Parametro save(Parametro entity) {
        return repository.save(entity);
    }

    @Override
    public Parametro update(Parametro entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Parametro findOne(int id) {
        Optional<Parametro> opt = repository.findById(id);
        return opt.isPresent() ? opt.get() : null;
    }

    @Override
    public Parametro findOneWithFT(int id) {
        return null;
    }

    @Override
    public Parametro findByParametro(String parametro) {
        Optional<Parametro> opt = repository.findByParametro(parametro);
        return opt.isPresent() ? opt.get() : null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(new Integer(id));
    }

    @Override
    public List<Parametro> findAll() {
        return repository.findAll();
    }
}

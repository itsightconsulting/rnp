package pe.gob.osce.rnp.seg.svc.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osce.rnp.seg.dao.CardRepository;
import pe.gob.osce.rnp.seg.generic.BaseServiceImpl;
import pe.gob.osce.rnp.seg.model.jpa.Card;
import pe.gob.osce.rnp.seg.svc.CardService;

@Service
@Transactional
public class CardServiceImpl extends BaseServiceImpl<CardRepository> implements CardService {

    public CardServiceImpl(CardRepository repository) {
        super(repository);
    }

    @Override
    public Card save(Card entity) {
        return repository.save(entity);
    }

    @Override
    public Card update(Card entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Card findOne(int id) {
        return repository.findById(id);
    }

    @Override
    public Card findOneWithFT(int id) {
        return null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(new Integer(id));
    }

    @Override
    public List<Card> findAll() {
        return repository.findAll();
    }

    @Override
    public Integer getSimpleSumaSp(int n1, int n2) {
        return repository.getSumaDemo(n1, n2);
    }
}

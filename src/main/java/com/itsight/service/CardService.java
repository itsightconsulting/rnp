package com.itsight.service;

import com.itsight.domain.Card;
import com.itsight.generic.BaseService;

import java.util.List;

public interface CardService extends BaseService<Card> {

    Integer getSimpleSumaSp(int n1, int n2);
}
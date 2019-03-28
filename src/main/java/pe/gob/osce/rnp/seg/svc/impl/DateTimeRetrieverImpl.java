package pe.gob.osce.rnp.seg.svc.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;

import pe.gob.osce.rnp.seg.svc.DateTimeRetriever;

@Service
public class DateTimeRetrieverImpl implements DateTimeRetriever, Serializable {

    @Override
    public Date currentTime() {
        return new Timestamp(System.currentTimeMillis());
    }
}

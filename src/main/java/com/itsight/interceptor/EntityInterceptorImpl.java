package com.itsight.interceptor;

import com.itsight.domain.base.AuditingEntity;
import com.itsight.service.DateTimeRetriever;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Component
public class EntityInterceptorImpl extends EmptyInterceptor implements EntityInterceptor {
    private static final long serialVersionUID = 8160823652337870429L;
    @Autowired
    DateTimeRetriever dateTimeRetriever;


    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof AuditingEntity) {

            for (int i = 0; i < propertyNames.length; i++) {
                String propertyName = propertyNames[i];

                if (propertyName.equals("createdBy")) {
                    Optional<Authentication> optSc =  Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
                    if(optSc.isPresent()){
                        state[i] = SecurityContextHolder.getContext().getAuthentication().getName();
                    }else{
                        state[i] = "InitialSeeder";
                    }
                } else if (propertyName.equals("creationDate")) {
                    state[i] = currentTime();
                } else if (propertyName.equals("flagActive")) {
                    state[i] = true;
                }
            }
        }
        return true; //super.onSave(entity, id, state, propertyNames, types);
    }

    private Date currentTime() {
        if (dateTimeRetriever == null) return new Timestamp(System.currentTimeMillis());
        Date date = dateTimeRetriever.currentTime();
        return date;
    }


    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof AuditingEntity) {
            for (int i = 0; i < propertyNames.length; i++) {
                String propertyName = propertyNames[i];

                if (propertyName.equals("modifiedBy")) {
                    currentState[i] = SecurityContextHolder.getContext().getAuthentication().getName();
                } else if (propertyName.equals("modificationDate")) {
                    currentState[i] = currentTime();
                }
            }
        }
        return true;
    }
}

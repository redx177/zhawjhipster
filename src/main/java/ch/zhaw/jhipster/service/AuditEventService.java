package ch.zhaw.jhipster.service;

import ch.zhaw.jhipster.config.audit.AuditEventConverter;
import ch.zhaw.jhipster.domain.PersistentAuditEvent;
import ch.zhaw.jhipster.repository.PersistenceAuditEventRepository;
import org.joda.time.LocalDateTime;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Service for managing audit events.
 * <p/>
 * <p>
 * This is the default implementation to support SpringBoot Actuator AuditEventRepository
 * </p>
 */
@Service
@Transactional
public class AuditEventService {

    @Inject
    private PersistenceAuditEventRepository persistenceAuditEventRepository;

    @Inject
    private AuditEventConverter auditEventConverter;

    public List<AuditEvent> findAll() {
        return auditEventConverter.convertToAuditEvent(persistenceAuditEventRepository.findAll());
    }

    public List<AuditEvent> findByDates(LocalDateTime fromDate, LocalDateTime toDate) {
        List<PersistentAuditEvent> persistentAuditEvents =
            persistenceAuditEventRepository.findAllByAuditEventDateBetween(fromDate, toDate);

        return auditEventConverter.convertToAuditEvent(persistentAuditEvents);
    }
}

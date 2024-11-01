package kh.edu.cstad.business.config.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    protected Boolean isDeleted = false;

    @CreatedBy
    protected T createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected LocalDateTime createdDate;

    @LastModifiedBy
    protected T lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

}

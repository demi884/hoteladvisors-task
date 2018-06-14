package org.wildfly.sample.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.wildfly.sample.Utils;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @Getter
    @Setter
    @Builder.Default
    private String id = Utils.randomId();

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date updated;
}

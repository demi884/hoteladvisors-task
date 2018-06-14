package org.wildfly.sample.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@NamedQueries({
        @NamedQuery(name = Address.Query.FIND_ALL, query = "SELECT a FROM Address a ORDER BY a.city, a.street"),
})
public class Address extends BaseEntity {
    public static class Query {
        public static final String FIND_ALL = "Address.findAll";
    }

    @Getter
    @Setter
    private String contactId;

    @Getter
    @Setter
    private Integer postIndex;

    @Getter
    @Setter
    @NotNull
    @Size(min = 1, max = 64)
    private String city;

    @Getter
    @Setter
    @NotNull
    @Size(min = 1, max = 64)
    private String street;

    @Getter
    @Setter
    @NotNull
    @Size(min = 1, max = 8)
    private String house;

    @Getter
    @Setter
    private String apartment;


}

package org.wildfly.sample.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@NamedQueries(@NamedQuery(name = Contact.Query.FIND_ALL, query = "SELECT c FROM Contact c ORDER BY c.lastName"))
public class Contact extends BaseEntity {
    public static class Query {
        public static final String FIND_ALL = "Contact.findAll";
    }

    @Getter
    @Setter
    @NotNull
    @Size(min = 1, max = 32)
    private String firstName;

    @Getter
    @Setter
    private String middleName;

    @Getter
    @Setter
    @NotNull
    @Size(min = 1, max = 32)
    private String lastName;

    @Getter
    @Setter
    private String phoneNumber;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "contactId")
    @Getter
    @Builder.Default
    private List<Address> addressList = new ArrayList<>();
}

package org.omnirio.app.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "account")
public class AccountEntity {
    private String accountId;
    private String accountType;
    private Date openDate;
    private String CustomerId;
    private String customerName;
    private String branch;
    private String minorIndicator;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountEntity)) {
            return false;
        }
        return accountId != null && accountId.equals(((AccountEntity) o).accountId);
    }
}

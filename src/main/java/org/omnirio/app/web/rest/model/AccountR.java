package org.omnirio.app.web.rest.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountR {
    private String accountId;
    private String accountType;
    private String openDate;
    private String CustomerId;
    private String customerName;
    private String branch;
    private String minorIndicator;

}

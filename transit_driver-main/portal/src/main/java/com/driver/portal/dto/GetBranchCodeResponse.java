package com.driver.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBranchCodeResponse {
     
    private int errCode;
    private String errMsg;
    private String branchName;
    private String branchCode;
    private String custEmail;
    private String custFax;
    private String custNationality;
    private String custPhone;
    private String custType;
    private String custId;
    private String customerName;
    private String nationalityID;
    private String mailingAddress;
    private String permanentAddress;
    private String dateCustOpen;
    private String dateOfBirth;
    private String flgCustType;
    private String gender;
    private String maritalStatus;;
    private String accountNo;
    private String avlBal;
    private String currBal;
}

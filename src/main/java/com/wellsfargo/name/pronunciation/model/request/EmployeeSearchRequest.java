package com.wellsfargo.name.pronunciation.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeSearchRequest {
    String searchCriteria;
}

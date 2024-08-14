package com.web.bookservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PostSearchCondition {

    private String select;
    private String query;
    private boolean my;
    private String orderBy;

}

package com.student.server.data.request;

import com.student.server.data.constant.PaginationConstant;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageableRequest {

    private int page = PaginationConstant.DEFAULT_CURRENT_PAGE;

    private int size = PaginationConstant.DEFAULT_SIZE;

    private String orderBy = PaginationConstant.DEFAULT_ORDER_BY;

    private String sortBy = PaginationConstant.DEFAULT_SORT_BY;

}

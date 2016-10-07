package me.dong.model.vo;

import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;

/**
 * 페이지와 사이즈를 지정하여 요청할 경우 사용
 */
@Setter
public class DataListRequestVO extends RequestVO{

    public static final int DEFAULT_PAGE_ROW = 20;

    private int page;

    private int pageSize;

    public int getPage(){
        return page < 1 ? 1: page;
    }

    public int getPageSize(){
        return pageSize <= 0 ? DEFAULT_PAGE_ROW : pageSize;
    }

    public Pageable getPageable(){
        return new QPageRequest(getPage() - 1, getPageSize());
    }
}

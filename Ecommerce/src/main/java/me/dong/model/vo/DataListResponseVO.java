package me.dong.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 응답코드/메시지 + 여러개의 데이터(엔티티, VO)가 있는 경우
 */
@Getter
@Setter
@ToString
public class DataListResponseVO<T> extends ResponseVO {

    private List<T> list;
    private int pageSize;
    private Integer page;
    private long fullListSize;

    public DataListResponseVO(String resultCode) {
        super(resultCode);
    }

    public DataListResponseVO(List<T> list){
        this(ResultCodes.OK);
        this.list = list;
    }

    public DataListResponseVO(Page<T> page){
        this(ResultCodes.OK);
        this.list = page.getContent();
        this.pageSize = page.getSize();
        this.page = page.getNumber() + 1;
        this.fullListSize = page.getTotalElements();
    }
}

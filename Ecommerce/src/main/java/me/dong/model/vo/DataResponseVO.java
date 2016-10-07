package me.dong.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 응답코드/메시지 + 1개의 데이터(엔티티, VO)가 있는 경우
 */
@Setter
@Getter
@ToString
public class DataResponseVO<T> extends ResponseVO {

    private T item;

    private int index;

    private long fullListSize;

    public DataResponseVO(String resultCode) {
        super(resultCode);
    }

    public DataResponseVO(T item){
        this(ResultCodes.OK);
        this.item = item;
    }
}

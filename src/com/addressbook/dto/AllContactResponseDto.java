package com.addressbook.dto;

import java.util.List;

/**
 * Created by birsan on 5/10/2016.
 */
public class AllContactResponseDto {
    private List<ContactDto> data;

    public List<ContactDto> getData() {
        return data;
    }

    public void setData(List<ContactDto> data) {
        this.data = data;
    }
}

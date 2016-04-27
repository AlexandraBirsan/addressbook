package com.addressbook.model;

import com.addressbook.dto.AbstractContactDTO;
import org.primefaces.model.StreamedContent;

/**
 * Created by birsan on 4/20/2016.
 */
public class ContactDto extends AbstractContactDTO {
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

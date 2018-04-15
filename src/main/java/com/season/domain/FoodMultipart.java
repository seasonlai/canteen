package com.season.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by season on 2018/4/15.
 */
public class FoodMultipart extends  Food{
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

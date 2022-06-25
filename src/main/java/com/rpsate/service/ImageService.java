package com.rpsate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rpsate.pojo.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    public Integer add(Image image, MultipartFile file) throws IOException;

    public Integer delete(Long id) throws IOException;

    public Integer deletes(List<Long> ids) throws IOException;

    public Integer update(Image image);

    public Integer update(Image image, MultipartFile file) throws IOException;

    public IPage getAll(Integer page, Integer size);

    public IPage search(Integer page, Integer size, String key);
}

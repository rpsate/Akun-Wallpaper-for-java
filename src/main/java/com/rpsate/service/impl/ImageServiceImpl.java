package com.rpsate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rpsate.dao.ImageDao;
import com.rpsate.pojo.Image;
import com.rpsate.response.RespCodeMsg;
import com.rpsate.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private ImageDao imageDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(Image image, MultipartFile file) throws IOException {
        Integer res = imageDao.insert(image);
        String filePath = uploadPath + File.separator + image.getPath();
        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        if (res > 0)
            return RespCodeMsg.ADD_IMAGE_SUCCESS;
        return RespCodeMsg.ADD_IMAGE_FAIL;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(Long id) throws IOException {
        //先查出文件信息
        Image image = imageDao.selectById(id);
        if (image == null)
            return RespCodeMsg.SELECT_IMAGE_FAIL;
        Integer res = imageDao.deleteById(id);
        String filePath = uploadPath + File.separator + image.getPath();

        //删除文件
        File dest = new File(filePath);
        Files.deleteIfExists(dest.toPath());

        if (res > 0)
            return RespCodeMsg.DELETE_IMAGE_SUCCESS;
        return RespCodeMsg.DELETE_IMAGE_FAIL;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deletes(List<Long> ids) throws IOException {
        //先查出文件信息
        List<Image> images = imageDao.selectBatchIds(ids);
        if (images == null)
            return RespCodeMsg.IMAGE_ID_INVALID;
        Integer res = imageDao.deleteBatchIds(ids);

        //删除文件
        for (Image image: images) {
            String filePath = uploadPath + File.separator + image.getPath();
            File dest = new File(filePath);
            Files.deleteIfExists(dest.toPath());
        }

        return res;
    }

    @Override
    public Integer update(Image image) {
        Integer res = imageDao.updateById(image);
        if (res > 0)
            return RespCodeMsg.UPDATE_IMAGE_SUCCESS;
        return RespCodeMsg.UPDATE_IMAGE_FAIL;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(Image image, MultipartFile file) throws IOException {
        //先查出原来文件信息
        Image oldImage = imageDao.selectById(image.getId());
        if (oldImage == null)
            return RespCodeMsg.SELECT_IMAGE_FAIL;
        String oldFilePath = uploadPath + File.separator + oldImage.getPath();

        //修改文件
        Integer res = imageDao.updateById(image);

        //删除老文件
        File oldDest = new File(oldFilePath);
        Files.deleteIfExists(oldDest.toPath());

        //保存新文件
        String newFilePath = uploadPath + File.separator + image.getPath();
        File newDest = new File(newFilePath);
        Files.copy(file.getInputStream(), newDest.toPath());

        if (res > 0)
            return RespCodeMsg.UPDATE_IMAGE_SUCCESS;
        return RespCodeMsg.UPDATE_IMAGE_FAIL;
    }

    @Override
    public IPage getAll(Integer page, Integer size) {
        IPage selectPage = new Page(page, size);
        imageDao.selectPage(selectPage, null);
        removeNotExistImage(selectPage.getRecords());
        return selectPage;
    }

    @Override
    public IPage search(Integer page, Integer size, String key) {
        StringBuilder keyBuilder = new StringBuilder();
        Integer length = key.length();
        for (int i = 0; i < length - 1; i++) {
            keyBuilder.append(key.charAt(i)).append("%");
        }
        keyBuilder.append(key.charAt(length - 1));
        IPage selectPage = new Page(page, size);
        imageDao.search(selectPage, keyBuilder.toString());
        removeNotExistImage(selectPage.getRecords());
        return selectPage;
    }

    private void removeNotExistImage(List<Image> images) {
        for (Image image: images) {
            String filePath = uploadPath + File.separator + image.getPath();
            File file = new File(filePath);
            if (!file.exists()) {
                image.setPath(null);
            }
        }
    }
}

package com.rpsate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rpsate.pojo.Image;
import com.rpsate.response.RespCodeMsg;
import com.rpsate.response.RespData;
import com.rpsate.service.ImageService;
import com.rpsate.utils.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Value("${file.upload.path}")
    private String uploadPath;

    @Value("#{'${file.upload.extension}'.split(',')}")
    private List<String> uploadExtension;

    @Autowired
    private ImageService imageService;

    @PostMapping
    public RespData add(@RequestPart(required = false) MultipartFile file, @RequestParam(required = false) String name) throws IOException {
        if (name == null)
            return new RespData(RespCodeMsg.IMAGE_NAME_NOT_NULL, null);
        if (file == null)
            return new RespData(RespCodeMsg.IMAGE_FILE_NOT_NULL, null);
        if (!FileProcessor.createPath(uploadPath))
            return new RespData(RespCodeMsg.PATH_CREATE_FAIL, null);
        String filename = file.getOriginalFilename();
        String extension = FileProcessor.getExtension(filename);
        if (!uploadExtension.contains(extension))
            return new RespData(RespCodeMsg.FILE_INVALID, null);
        String path = FileProcessor.getSaveName(filename);
        //为了文件上传和数据库读写一致，将文件保存放到Service中处理(开启事务)
        Image image = new Image(name, path);
        Integer code = imageService.add(image, file);
        return new RespData(code, null);
    }

    @DeleteMapping("/{id}")
    public RespData delete(@PathVariable Long id) throws IOException {
        Integer code = imageService.delete(id);
        return new RespData(code, null);
    }


    @DeleteMapping
    public RespData deletes(@RequestBody List<Long> ids) throws IOException {
        if (ids == null || ids.size() == 0)
            return new RespData(RespCodeMsg.ID_NOT_NULL, null);
        Integer res = imageService.deletes(ids);
        if (res > 0)
            return new RespData(RespCodeMsg.DELETE_IMAGES_SUCCESS, null, String.valueOf(res));
        return new RespData(RespCodeMsg.DELETE_IMAGES_FAIL, null);
    }

    @PutMapping
    public RespData update(@RequestPart(required = false) MultipartFile file,@RequestParam(required = false) Long id, @RequestParam(required = false) String name) throws IOException {
        if (id == null)
            return new RespData(RespCodeMsg.ID_NOT_NULL, null);
        if (name == null && file == null)
            return new RespData(RespCodeMsg.PARAMETER_ERR, null);
        if (file == null) {
            Image image = new Image(id, name, null);
            Integer code = imageService.update(image);
            return new RespData(code, null);
        }
        String filename = file.getOriginalFilename();
        String extension = FileProcessor.getExtension(filename);
        if (!uploadExtension.contains(extension))
            return new RespData(RespCodeMsg.FILE_INVALID, null);
        String path = FileProcessor.getSaveName(filename);
        //为了文件上传和数据库读写一致，将文件保存放到Service中处理(开启事务)
        Image image = new Image(id, name, path);
        Integer code = imageService.update(image, file);
        return new RespData(code, null);
    }


    @GetMapping("/{page}/{size}")
    public RespData getAll(@PathVariable Integer page,@PathVariable Integer size, @RequestBody(required = false) String key) {
        IPage res = imageService.getAll(page, size);
        if (res == null)
            return new RespData(RespCodeMsg.FAIL, null);
        return new RespData(RespCodeMsg.SUCCESS, res);
    }

    @GetMapping("/{page}/{size}/{key}")
    public RespData search(@PathVariable Integer page,@PathVariable Integer size, @PathVariable String key) {
        IPage res = imageService.search(page, size, key);
        if (res == null)
            return new RespData(RespCodeMsg.FAIL, null);
        return new RespData(RespCodeMsg.SUCCESS, res);
    }
}

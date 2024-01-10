package com.hjxlog.controller.admin;

import com.hjxlog.protocol.Result;
import com.hjxlog.util.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author: Huang JX
 * @date: 2024/1/6
 */
@RestController
@RequestMapping("/admin/file")
public class AdminFileController {

    @Resource
    private FileUtils fileUtils;

    @PostMapping("/uploadImage")
    public Result handleFileUpload(@RequestParam("file") MultipartFile file) {
        String uri = fileUtils.uploadImage(file);
        return Result.success(uri);
    }

}

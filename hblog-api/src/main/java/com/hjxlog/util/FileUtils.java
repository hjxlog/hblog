package com.hjxlog.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.hjxlog.constant.SystemConstants;
import com.hjxlog.exception.SystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: Huang JX
 * @date: 2024/1/6
 */
@Component
public class FileUtils {

    @Value("${aliyun.oss.endpoint}")
    private String ossEndpoint;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    @Value("${aliyun.oss.custom-domain}")
    private String customDomain;

    public String uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new SystemException("上传文件为空");
        }
        String curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String originalFilename = file.getOriginalFilename();
        String imageName = SystemConstants.ALIYUN_OSS_FOLDER + curTime + "_" + originalFilename;
        OSS ossClient = null;
        try {
            // 从环境变量中获取访问凭证
            // 请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
            EnvironmentVariableCredentialsProvider credentialsProvider;
            credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
            ossClient = new OSSClientBuilder().build(ossEndpoint, credentialsProvider);
            ossClient.putObject(bucketName, imageName, new ByteArrayInputStream(file.getBytes()));
        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return customDomain + "/" + imageName;
    }

}

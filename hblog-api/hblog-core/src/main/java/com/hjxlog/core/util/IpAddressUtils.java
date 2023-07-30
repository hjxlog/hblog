package com.hjxlog.core.util;

import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: Huang JX
 * @date: 2023/7/20
 */
@Component
public class IpAddressUtils {

    private static Searcher searcher;

    @PostConstruct
    private void initSearcher() throws IOException {
        InputStream inputStream = new ClassPathResource("ip2region.xdb").getInputStream();
        byte[] cBuff = FileCopyUtils.copyToByteArray(inputStream);
        searcher = Searcher.newWithBuffer(cBuff);
    }

    public static String getAddress(String ip) {
        String region = "invalid ip address";
        try {
            region = searcher.search(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }

}

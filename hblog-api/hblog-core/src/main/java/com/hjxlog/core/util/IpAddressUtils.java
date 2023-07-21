package com.hjxlog.core.util;

import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @author: Huang JX
 * @date: 2023/7/20
 */
@Component
public class IpAddressUtils {

    private static Searcher searcher;

    @PostConstruct
    private void initSearcher() throws IOException {
        File file = ResourceUtils.getFile("classpath:ip2region.xdb");
        byte[] cBuff = Searcher.loadContentFromFile(file.getAbsolutePath());
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

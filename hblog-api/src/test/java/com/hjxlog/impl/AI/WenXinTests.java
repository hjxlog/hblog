package com.hjxlog.impl.AI;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.model.chat.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Huang JX
 * @date: 2024/8/13
 */
@SpringBootTest
public class WenXinTests {

    @Value("${AI.Qianfan.accessKey}")
    private String accessKey;

    @Value("${AI.Qianfan.secretKey}")
    private String secretKey;

    @Test
    void flowType() {//流式
        // 使用安全认证AK/SK鉴权参数，安全认证Access Key替换your_iam_ak，Secret Key替换your_iam_sk
        Qianfan qianfan = new Qianfan(accessKey, secretKey);
        qianfan.chatCompletion()
                .model("ERNIE-Lite-8K")
                .addMessage("user", "AI的发展历史")
                // 启用流式返回
                .executeStream()
                .forEachRemaining(chunk -> {
                    String result = chunk.getResult();
                    System.out.print(result);
                });
    }

    @Test
    void multiWheel() {//多轮
        // 使用安全认证AK/SK鉴权参数，安全认证Access Key替换your_iam_ak，Secret Key替换your_iam_sk
        Qianfan qianfan = new Qianfan(accessKey, secretKey);
        // 多轮对话
        ChatResponse resp = qianfan.chatCompletion()
                .model("ERNIE-Lite-8K")
                .addMessage("user", "AI的发展历史")
                .execute();
        System.out.println(resp.getResult());
    }

}

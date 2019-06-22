package com.hejz.wireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/22 16:32
 */
public class MockServer {
    public static void main(String[] args) throws Exception {
        configureFor(8083);
        //把之前配置都清空
        removeAllMappings();
        //重新加配置
        mock("/order/1", "01");
        mock("/order/2", "02");


    }

    private static void mock(String url, String file) throws Exception {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        Object[] array = FileUtils.readLines(resource.getFile(), "utf-8").toArray();
        String content = StringUtils.join(array, "\n");
        stubFor(get(urlPathEqualTo(url))
                .willReturn(aResponse().withBody(content).withStatus(200)));
    }
}

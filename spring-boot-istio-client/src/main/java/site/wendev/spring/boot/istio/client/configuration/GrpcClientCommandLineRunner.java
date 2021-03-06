package site.wendev.spring.boot.istio.client.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 加入gRPC Client的启动、停止等逻辑到Spring Boot生命周期中
 *
 * @author 江文
 * @date 2020/4/12 3:36 下午
 */
@Component
@Slf4j
public class GrpcClientCommandLineRunner implements CommandLineRunner {
    @Autowired
    GrpcClientConfiguration configuration;

    @Override
    public void run(String... args) {
        // 开启gRPC客户端
        configuration.start();

        // 添加客户端关闭的逻辑
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                configuration.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}

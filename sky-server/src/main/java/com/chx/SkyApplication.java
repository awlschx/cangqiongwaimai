package com.chx;

import com.sun.jdi.PathSearchingVirtualMachine;
import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

@SpringBootApplication
@EnableTransactionManagement//开启注解方式的事务管理
@Slf4j
public class SkyApplication {
    public static void main(String[] args){
        SpringApplication.run(SkyApplication.class);
        log.info("项目启动成功...");
    }
}

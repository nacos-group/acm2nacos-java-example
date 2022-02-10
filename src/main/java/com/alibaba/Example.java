/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

public class Example {
    
    // 配置中心的endpoint。
    // 在ACM迁移MSE Nacos的过程中，需要将这个{serverAddr}替换为MSE Nacos的内网/外网地址。（可以在MSE Nacos集群的详情页的"基础信息"栏目中找到）
    static final String mseServerAddr = "{serverAddr}";
    static final String dataId = "{dataId}";
    static final String group = "{group}";
    static final Properties propertiesForMse;
    static final String keyId = "alias/acs/mse";
    // 购买的mse所在的region，比如cn-hangzhou
    static final String regionId = "{region}";
    
    // 账号的AK/SK
    static final String akForFather = "{ak}";
    static final String skForFather = "{sk}";
    static {
        propertiesForMse = new Properties();
        // 给本地Properties授予基础信息
        // 给访问MSE的授予基础信息
        propertiesForMse.put("serverAddr", mseServerAddr);
        propertiesForMse.put("keyId", keyId);
        propertiesForMse.put("regionId", regionId);
        // 在ACM迁移MSE Nacos的过程中，需要在MSE Nacos集群的详情页的"命名空间"栏目中新建namespace，并且将namespace id复制到{namespace}处。
        // 您也可以选择使用默认的namespace，如果使用默认的"public"的namcespace,请不要填写这个值，直接留空即可。
        propertiesForMse.put("namespace", "{namespace}");
        // 给访问MSE的Properties授予AK/SK
        propertiesForMse.put("accessKey", akForFather);
        propertiesForMse.put("secretKey", skForFather);
    }
    
    public static void main(String[] args) throws NacosException, InterruptedException {
        testLogic();
    }
    
    public static void testLogic() throws NacosException, InterruptedException {
        ConfigService configService = NacosFactory.createConfigService(propertiesForMse);
        String content = "kuae-test-content1111";
        boolean result = configService.publishConfig(dataId, group, content);
        System.out.println("*****" + result);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }
            
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println(configInfo);
            }
        });
        Thread.sleep(999999999L);
    }
    
}

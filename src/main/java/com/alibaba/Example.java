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
    
    // �������ĵ�endpoint��
    // ��ACMǨ��MSE Nacos�Ĺ����У���Ҫ�����{serverAddr}�滻ΪMSE Nacos������/������ַ����������MSE Nacos��Ⱥ������ҳ��"������Ϣ"��Ŀ���ҵ���
    static final String mseServerAddr = "{serverAddr}";
    static final String dataId = "{dataId}";
    static final String group = "{group}";
    static final Properties propertiesForMse;
    static final String keyId = "alias/acs/mse";
    // �����mse���ڵ�region������cn-hangzhou
    static final String regionId = "{region}";
    
    // �˺ŵ�AK/SK
    static final String akForFather = "{ak}";
    static final String skForFather = "{sk}";
    static {
        propertiesForMse = new Properties();
        // ������Properties���������Ϣ
        // ������MSE�����������Ϣ
        propertiesForMse.put("serverAddr", mseServerAddr);
        propertiesForMse.put("keyId", keyId);
        propertiesForMse.put("regionId", regionId);
        // ��ACMǨ��MSE Nacos�Ĺ����У���Ҫ��MSE Nacos��Ⱥ������ҳ��"�����ռ�"��Ŀ���½�namespace�����ҽ�namespace id���Ƶ�{namespace}����
        // ��Ҳ����ѡ��ʹ��Ĭ�ϵ�namespace�����ʹ��Ĭ�ϵ�"public"��namcespace,�벻Ҫ��д���ֵ��ֱ�����ռ��ɡ�
        propertiesForMse.put("namespace", "{namespace}");
        // ������MSE��Properties����AK/SK
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

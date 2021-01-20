/*
 * Copyright 2018-2020 Pnoker. All Rights Reserved.
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

package com.dc3.center.data.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc3.api.center.data.feign.DeviceEventClient;
import com.dc3.center.data.service.DeviceEventService;
import com.dc3.common.bean.R;
import com.dc3.common.bean.driver.DeviceEvent;
import com.dc3.common.bean.driver.DeviceEventDto;
import com.dc3.common.constant.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author pnoker
 */
@Slf4j
@RestController
@RequestMapping(Common.Service.DC3_DATA_DEVICE_EVENT_URL_PREFIX)
public class DeviceEventApi implements DeviceEventClient {

    @Resource
    private DeviceEventService deviceEventService;

    @Override
    public R<String> deviceStatus(Long deviceId) {
        try {
            String status = deviceEventService.deviceStatus(deviceId);
            return R.ok(status, "ok");
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Page<DeviceEvent>> list(DeviceEventDto deviceEventDto) {
        try {
            Page<DeviceEvent> page = deviceEventService.list(deviceEventDto);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

}
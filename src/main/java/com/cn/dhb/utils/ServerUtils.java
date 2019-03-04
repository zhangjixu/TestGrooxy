package com.cn.dhb.utils;


import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: zhangjixu
 * @CreateDate: 2018/6/15
 * @Description:
 * @Version: 1.0.0
 */
public class ServerUtils {

    /**
     * 获取本地 ip
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getLocalHostIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress().toString();
    }

    /**
     * 获取请求的端口号
     *
     * @param request
     * @return
     */
    public static int getRequestPort(HttpServletRequest request) {
        return request.getServerPort();
    }


}

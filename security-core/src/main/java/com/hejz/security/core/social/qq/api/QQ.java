package com.hejz.security.core.social.qq.api;

import java.io.IOException;

/**
 * QQ用户信息
 */
public interface QQ {
    QQUserInfo getUserInfo() throws IOException;
}

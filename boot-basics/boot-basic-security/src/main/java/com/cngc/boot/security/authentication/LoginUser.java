package com.cngc.boot.security.authentication;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 通过security框架获取的principal对象.
 *
 * @author maxD
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class LoginUser {
    private String account;
    private String appCode;
    private String userName;

    private LoginUser() {

    }

    public static Builder withAccount(String account) {
        return new Builder(account);
    }

    /**
     * LoginUser对象构造器.
     */
    public static final class Builder {
        private String account;
        private String appCode;
        private String userName;

        public Builder(String account) {
            this.account = account;
        }

        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public LoginUser build() {
            return new LoginUser(this.account, this.appCode, this.userName);
        }
    }
}

package com.sxl.mvpmoduledemo.model;

import com.sxl.mvpmoduledemo.contract.LoginContract;
import com.sxl.mvpmoduledemo.presenter.LoginPresenter;

/**
 * author: smile .
 * date: On 2019/4/17
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public void login(String name, String psw, LoginPresenter loginPresenter) {
        /**
         * 对数据做一个简单的判断 并通知展示器
         */
        if (name.isEmpty() || psw.isEmpty()){
            loginPresenter.onFail("账号密码不能为空");
        }else if (name.equals("root") && psw.equals("123456")){
            loginPresenter.onSuccess();
        }else {
            loginPresenter.onFail("账号密码错误");
        }
    }
}

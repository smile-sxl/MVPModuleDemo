package com.sxl.mvpmoduledemo.contract;

import com.sxl.mvpmoduledemo.presenter.LoginPresenter;

/**
 * author: smile .
 * date: On 2019/4/17
 */
public interface LoginContract {

    // 模型 处理数据
    interface Model {
        void login(String name, String psw, LoginPresenter loginPresenter);
    }

    // 视图  显示界面
    interface View {
        // 登录失败
        void onFail(String msg);
        // 登录成功
        void onSuccess();
        // 获取账号
        String getName();
        // 获取密码
        String getPassWord();
    }

    // 展示器 或者说 主持人  进行逻辑处理
    interface Presenter {
        // 登陆成功
        void onSuccess();
        // 登陆失败
        void onFail(String msg);
    }
}

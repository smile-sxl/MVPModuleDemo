package com.sxl.mvpmoduledemo.presenter;

import com.sxl.mvpmoduledemo.contract.LoginContract;
import com.sxl.mvpmoduledemo.model.LoginModel;

/**
 * author: smile .
 * date: On 2019/4/17
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private LoginModel loginModel;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        loginModel=new LoginModel();
    }

    @Override
    public void onSuccess() {
        // 通知视图
        view.onSuccess();
    }

    @Override
    public void onFail(String msg) {
        // 通知视图
        view.onFail(msg);
    }

    /**
     * 登录方法
     */
    public void login(){
        //将view中的参数获取出来。
        String name = view.getName();
        String psw = view.getPassWord();

        // 调用一下业务处理model层的登陆方法
        loginModel.login(name,psw,this);
    }

}

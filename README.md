## 前言
  MVC，MVP，MVVM这三个我们都或多或少的听说过或者了解过，但说实话，我在上家公司做Android的时候只对代码根据功能进行过模块化的拆分，具体在项目中或在代码中很少有实现，下面我们先简单的介绍下这三种模式。

## MVC、MVP和MVVM 

### 一、MVC、MVP和MVVM 都分别是什么？

#### MVC：模型（Model）、视图（View）和控制器（Controller）

- 模型（Model）：专门用来处理业务逻辑，如数据库相关的操作、文件的访问和数据结构等。
- 视图（View）： 专注页面布局和数据显示，如界面的显示
- 控制器（Controller）：连接模型和视图，如把视图的请求发送给模型

![mvc](https://github.com/smile-sxl/MVPModuleDemo/image/mvc.png)

MVC实现了视图和模型的分离，避免了视图和模型糅合在一起，当视图改变的时候只要业务逻辑没变不需要改变模型；但是它有一个缺点缺点是因为MVC中的控制器并不能直接更新视图，所以MVC并不能实现视图和模型的完全分离，视图依然依赖模型的数据（数据结构）来显示，也就是说视图依赖模型。

#### MVP:  模型（Model）、视图（View）和展示器（Presenter）

- 模型（Model）：专门用来处理业务逻辑，如数据库相关的操作、文件的访问和数据结构等。
- 视图（View）：专注页面布局和数据显示，如界面的显示
- 展示器（Presenter）：连接模型和视图，处理视图的请求并根据模型更新视图，将模型和视图进行分离了，所有的交互都发生在Persenter 内部。

![mvc](https://github.com/smile-sxl/MVPModuleDemo/image/mvp.png)

MVP用展示器代替了控制器，而展示器是可以直接更新视图，所以MVP中展示器可以处理视图的请求并递送到模型又可以根据模型的变化更新视图，实现了视图和模型的完全分离。

#### MVVM：模型（Model）、视图（View）和视图模型（ViewModel）

- 模型（Model）：专门用来处理业务逻辑，如数据库相关的操作、文件的访问和数据结构等。
- 视图（View）：专注页面布局和数据显示，如界面的显示
- 视图模型（ViewModel）：连接模型和视图，也是将模型和视图进行了分离，但视图模型和视图是双向绑定的，当视图发生变化的时候视图模型也会发生改变，当视图模型变化的时候视图也随之变化。

![mvc](https://github.com/smile-sxl/MVPModuleDemo/image/mvvm.png)

MVVM用视图模型代替了MVP中的展示器，视图模型和视图实现了双向绑定，当视图发生变化的时候视图模型也会发生改变，当视图模型变化的时候视图也随之变化。

### 二、个人建议在Android中采用MVP

 **MVP相比于MVC的优势：** 

- 分离了视图逻辑和业务逻辑，降低了耦合。
- Activity只处理生命周期的任务，代码变得更加**简洁**。
- 视图逻辑和业务逻辑分别抽象到了View和Presenter的**接口**中去，提高代码的可阅读性。
- Presenter被抽象成接口，可以有多种具体的实现，所以方便进行**单元测试**。
- 把业务逻辑抽到Presenter中去，避免后台线程引用着Activity导致Activity的资源无法被系统回收从而引起**内存泄露**和OOM。

大家也可以看一看任玉刚大大的

[MVC、MVP、MVVM，我到底该怎么选？](https://blog.csdn.net/singwhatiwanna/article/details/80904132)这篇文章，可以根据项目中不同的情况下可以采用的不同的模式。



## Android快速生成MVP 模式代码

**Demo 小例子：通过MVP模式实现登录效果，效果看下图：**

![](https://github.com/smile-sxl/MVPModuleDemo/image/login.gif)

#### 一、安装MVPHelper插件

MVPHelper是一款适用于 `Intellij IDEA` 和 `Android Studio` 的插件, 可用于为 MVP 架构生成接口以及实现类。在 **Android Studio**  中`Setting` 窗口的 `Plugins `选项搜索 `MVPHelper` 安装即可，具体可看下图。

![mvc](https://github.com/smile-sxl/MVPModuleDemo/image/mvphelper.jpg)

#### 二、使用MVPHelper 快速创建MVP 

首先在包下创建一个 presenter 空文件夹，然后创建一个LoginPresenter 的类，在类中使用MVPHelper 插件生成接口以及实现类。具体看下图：

![mvchelper](https://github.com/smile-sxl/MVPModuleDemo/image/mvphelper.gif)

创建成功后会生成如下目录结构：

![](https://github.com/smile-sxl/MVPModuleDemo/image/jiegou.jpg)

#### 三、根据生成的MVP目录结构去拆分登录这个过程

##### 1、首先是界面xml布局：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <EditText
        android:id="@+id/edtTxt_login_account"
        android:layout_marginTop="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:hint="请输入账号"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <EditText
        android:id="@+id/edtTxt_login_password"
        android:layout_marginTop="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:hint="请输入密码"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/btn_login_login"
        android:layout_marginTop="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:text="登陆"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</LinearLayout>
```

##### 2、对MVP模型拆分逻辑，实现LoginContract 接口

```java
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
```

##### 3、对接口进行实现

- Model 模型的实现

```java
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
```

- View 视图的实现

```java
public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    private EditText edtTxtLoginAccount;
    private EditText edtTxtLoginPassword;
    private Button btnLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtTxtLoginAccount = findViewById(R.id.edtTxt_login_account);
        edtTxtLoginPassword = findViewById(R.id.edtTxt_login_password);
        btnLogin = findViewById(R.id.btn_login_login);
        btnLogin.setOnClickListener(this);
        loginPresenter=new LoginPresenter(this);
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public String getName() {
        return edtTxtLoginAccount.getText().toString();
    }

    @Override
    public String getPassWord() {
        return edtTxtLoginPassword.getText().toString();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_login_login){
          	// 通知展示器
            loginPresenter.login();
        }
    }
}
```

- Presenter 展示器的实现

```java
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
```

### 总结：

以上就是对登录过程简单的一次MVP 模式的实现，其实MVP模式困难的地方就是要明确好各个位置各自负责的工作，具体的实现对号入座就可以了。


#### 01_Debug简介和意义

+ 什么是程序DeBug？
  + Debug，是程序开发人员必会的一项调试程序的技能。

  + 企业中程序开发和程序调试的比例为1:1.5，可以说如果你不会调试程序，你就没有办法从事编程工作。

    

+ Debug能帮助我们做什么？
  1. 追踪代码的运行流程。
  2. 程序运行异常定位。
  3. 线上问题追踪。

  

+ Debug对于程序学习者的意义

  1. 通过调试能够更好的查看程序的执行流程。
  2. 复杂的程序逻辑，通过老师的口述讲解，很难理解清楚，这个时候借助调试能够很好的帮助同学们理解程序。
  3. 定位问题，提高自我解决问题的能力。

  


#### 02_IDEA中的Debug步骤

+ 设置断点（F9）

+ 调试程序（8个按钮）

  ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6e6a44619ef74568820ddcedb8eacd7b~tplv-k3u1fbpfcp-zoom-1.image)

  | 按钮                                                         | 说明                                                         |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/46f3d9ef52474fb8bc8408b9d7e29e7e~tplv-k3u1fbpfcp-zoom-1.image)                            | (Alt + F10)：如果你的光标在其它行或其它页面，点击这个按钮可跳转到当前代码执行的行 |
  | ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/41b1b76e281c4f8aaf1e3e31b1d50b74~tplv-k3u1fbpfcp-zoom-1.image)                            | (F8)：步过，一行一行地往下走，如果这一行上有方法不会进入方法。 |
  | ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b0d3d68832944eadb5ad2fd29562c72e~tplv-k3u1fbpfcp-zoom-1.image) | (F7)：步入。如果当前行有方法，可以进入方法内部，一般用于进入自定义方法内，不会进入官方类库的方法。 |
  | ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/845b70957ca7423d842d16f00a96d816~tplv-k3u1fbpfcp-zoom-1.image)                            | (Alt + Shift + F7)：强制步入，能进入任何方法，查看底层源码的时候可以用这个进入官方类库的方法。 |
  | ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0948f7219a5f4df682f12743d51f9065~tplv-k3u1fbpfcp-zoom-1.image)                            | (Shift + F8)：步出，从步入的方法内退出到方法调用处，此时方法已执行完毕，只是还没有完成赋值。 |
  | ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5161c9c3cfd044a89b352fec8d26c44a~tplv-k3u1fbpfcp-zoom-1.image)                            | 回退断点。回退到当前方法的调用处。                                             |
  | ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b51c96e427164406ad276ff8c20612c2~tplv-k3u1fbpfcp-zoom-1.image)                            | (Alt + F9)：运行到光标处，你可以将光标定位到你需要查看的那一行，然后使用这个功能，代码会运行至光标行，而不需要打断点。 |
  | ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c89d8b5ae1b748748612adb764b5066b~tplv-k3u1fbpfcp-zoom-1.image)                            | (Alt + F8)：计算表达式。                                     |

+ 观察变量

  + 查看变量有三种方式：
    + 程序区查看变量
    + Debugger的Variables中查看变量
    + 鼠标悬停到变量名上会弹出当前变量的值

  ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/786c2166e12d41debc775afbc4f84d16~tplv-k3u1fbpfcp-zoom-1.image)

+ 查看输出

  

#### 03_跳转到当前代码执行的行

- 跳转到当前代码执行的行（Alt + F10）![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5d4e738942e14d749c8340b0748b0e5f~tplv-k3u1fbpfcp-zoom-1.image)

- 作用

  - 使程序窗口切换到当前正在运行的程序处。

    

#### 04_步过调试的使用

+ 步过调试
  + 步过调试按钮（F8）![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/095ced070cd04ff291159d7abbfb6439~tplv-k3u1fbpfcp-zoom-1.image)
+ 作用
  + 步过，一行一行地往下走，如果这一行上有方法不会进入方法。
  + 常用于调试过程中不想进入调用的方法体的情况。



#### 05_步入调试的使用

+ 步入调试

  + 步过调试按钮（F7）![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dc9f5216e6b14e699051773481d890b9~tplv-k3u1fbpfcp-zoom-1.image)

+ 作用

  + 步入，一行一行地往下走，如果这一行上有方法，则进入方法内部。

  + 一般用于进入自定义方法内，不会进入官方类库的方法。

    

#### 06_强制步入调试的使用

- 强制步入调试

  - 强制步入调试按钮（Alt + Shift + F7）![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b24c8620c9a44f318fe04d45ead1c475~tplv-k3u1fbpfcp-zoom-1.image)  

- 作用

  - 进入官方类库方法

  - 帮助我们学习和查看JDK源码

    

#### 07_步出调试的使用

- 步出调试
  - 步出调试按钮（Shift + F8）![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/190739df50314e5e8f0c172350519363~tplv-k3u1fbpfcp-zoom-1.image)  

- 作用
  - 从方法内退出到方法调用处。
  - 调试的时候，有时候会跳入到自己不想查看的方法体，这个时候使用步出。



#### 08_回退断点调试的使用

- 回退断点

  - 回退断点按钮  ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a30e786abfe0419f975b2b21128fb81d~tplv-k3u1fbpfcp-zoom-1.image)   

- 作用

  - 回退到当前方法的调用处。

  - 当想重新查看该方法体的执行过程时，不用重新启动Debug，可以使用回退断点方式。

    

#### 09_运行到光标处

- 运行到光标处
  - 运行光标处按钮 （F9）![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/50af61dd776444b39e7beee36c42d907~tplv-k3u1fbpfcp-zoom-1.image)  
  - 作用
    - 使程序运行到光标处，而无需设置断点。



#### 10_计算表达式

- 计算表达式

  - 计算表达式按钮（Alt + F8）![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/fd975ca2521c45b78017c427d75f0b85~tplv-k3u1fbpfcp-zoom-1.image)  

    ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3f6e3c905f404b44b2318d2c65864c97~tplv-k3u1fbpfcp-zoom-1.image)

- 作用

  - 设置变量，在计算表达式的框里，可以改变变量的值，这样有时候就能很方便我们去调试各种值的情况了。 

    

#### 11_条件断点

- 条件断点

  - 右键单击断点处，可以设置进入断点的条件

    ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/766e70e7f2b940209c5c4e9459810719~tplv-k3u1fbpfcp-zoom-1.image)

- 作用

  - 通过设置断点条件，在满足条件时，才停在断点处，否则直接运行。

    

#### 12_多线程调试

+ 步骤
  + 多线程调试，需要调整断点挂起级别为Thread

    ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/be2033196277476ab7126c2b611ca0e4~tplv-k3u1fbpfcp-zoom-1.image)

  + Frame中选择线程进行调试
  
#### 13_出处
本篇来自B站up风清扬的[视频](https://www.bilibili.com/video/BV1LJ41187hu?p=6&share_medium=iphone&share_plat=ios&share_source=WEIXIN&share_tag=s_i&timestamp=1619797311&unique_k=XnKRMN)，结合视频学习更高效。
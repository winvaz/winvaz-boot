package com.icore.winvaz.javase.basic.designpattern.proxypattern;

/**
 * 用户只关心接口功能，而不在乎谁提供了功能。上图中接口是 Subject。
 * 接口真正实现者是上图的 RealSubject，但是它不与用户直接接触，而是通过代理。
 * 代理就是上图中的 Proxy，由于它实现了 Subject 接口，所以它能够直接与用户接触。
 * 用户调用 Proxy 的时候，Proxy 内部调用了 RealSubject。所以，Proxy 是中介者，它可以增强 RealSubject 操作。
 * 代理又可以分为静态代理和动态代理两种。我们先来看下静态代理。
 * 现在可以看到，代理模式可以在不修改被代理对象的基础上，通过扩展代理类，
 * 进行一些功能的附加与增强。值得注意的是，代理类和被代理类应该共同实现一个接口，
 * 或者是共同继承某个类。这个就是是静态代理的内容，为什么叫做静态呢？因为它的类型是事先预定好的，比如上面代码中的 MovieStaticProxy 这个类。
 * 优点
 * 代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用
 * 代理对象可以扩展目标对象的功能
 * 代理模式能将客户端与目标对象分离，在一定程度上降低了系统的耦合度。
 * 缺点
 * 代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护。
 * <p>
 * 代理分为静态代理和动态代理两种。
 * 静态代理，代理类需要自己编写代码写成。
 * 动态代理有jdk和cglib，代理类通过 Proxy.newInstance()或者ASM 生成。
 * 静态代理和动态代理的区别是在于要不要开发者自己定义 Proxy 类。
 * 动态代理通过 Proxy 动态生成 proxy class，但是它也指定了一个 InvocationHandler 或者 MethodInterceptor的实现类。
 * 代理模式本质上的目的是为了增强现有代码的功能。
 *
 * @Deciption 静态代理
 * @Author wdq
 * @Create 2020/6/16 22:05
 * @Version 1.0.0
 */
public class StaticProxyPattern {
    public static void main(String[] args) {
        Movie captainAmericaMovie = new CaptainAmericaMovie();
        MovieStaticProxy movieStaticProxy = new MovieStaticProxy(captainAmericaMovie);
        movieStaticProxy.play();
    }
}

/**
 * 电影是电影公司委托给影院进行播放的，但是影院可以在播放电影的时候，产生一些自己的经济收益，比如提供按摩椅，娃娃机（这个每次去电影院都会尝试下，基本上是夹不起来，有木有大神可以传授下诀窍），
 * 卖爆米花、饮料（贵的要死，反正吃不起）等。我们平常去电影院看电影的时候，在电影开始的阶段是不是经常会放广告呢？
 * 然后在影片开始结束时播放一些广告。 下面我们通过代码来模拟下电影院这一系列的赚钱操作。
 * 首先得有一个接口，通用的接口是代理模式实现的基础。这个接口我们命名为 Movie，代表电影播放的能力。
 */
// 定义一个电影(Movie)接口
interface Movie {
    // 播放的功能
    void play();
}

// 接下来我们要创建一个真正的实现这个 Movie 接口的类，
// 和一个实现该接口的代理类。 真正的类《美国队长》电影：
class CaptainAmericaMovie implements Movie {

    @Override
    public void play() {
        System.out.println("普通影厅正在播放的电影是《美国队长》");
    }
}

// 创建代理类
class MovieStaticProxy implements Movie {

    private Movie movie;

    public MovieStaticProxy(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void play() {
        // 播放电影之前
        playStart();
        movie.play();
        // 播放电影之后
        playEnd();
    }

    private void playEnd() {
        System.out.println("电影结束了，继续播放广告");
    }

    private void playStart() {
        System.out.println("电影开始前正在播放广告");
    }
}
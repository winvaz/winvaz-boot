package com.icore.winvaz.javase.basic.thread;

import lombok.Data;

/**
 * 线程通讯
 *
 * @Author wdq
 * @Create 2019-02-22 13:16
 */
public class ThreadCommunication {

    private static int i = 0;

    public static void main(String[] args) {
        Resource resource = new Resource();
        /*
        Input input = new Input(resource);
        Output output = new Output(resource);

        Thread thread1 = new Thread(input);
        Thread thread2 = new Thread(output);

        thread1.start();
        thread2.start();

        */
        new Thread(new Input(resource)).start();
        new Thread(new Output(resource)).start();
        // Runable接口1.8后变为函数式接口，所以可以使用1.8的函数式编程
        /*new Thread(() ->
                new Input(resource)
        ).start();
        new Thread(() ->
                new Output(resource)
        ).start();*/
    }
}

// 定义资源对象
@Data
class Resource {
    private String name;
    private String sex;
    // 定义，flag = true 赋值完成，flag = false 输出完成
    private Boolean flag = false;
}

// 定义输入线程，输入数据
class Input implements Runnable {
    Resource resource;

    public Input(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            synchronized (resource) {
                // 输入线程，判断flag的值，如果是真，等待
                if (resource.getFlag()) {
                    try {
                        resource.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (x % 2 == 0) {
                    resource.setName("张三");
                    resource.setSex("男");
                } else {
                    resource.setName("lisi");
                    resource.setSex("nv");
                }
                x++;
                // 把标记改为true
                resource.setFlag(true);
                // 唤醒输出线程
                resource.notify();
            }
        }
    }
}

// 定义输出线程，获取数据
class Output implements Runnable {
    Resource resource;

    public Output(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (resource) {
                // 如果flag为false,等待
                if (!resource.getFlag()) {
                    try {
                        resource.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(resource.getName() + "..." + resource.getSex());
                // 把标记改为false
                resource.setFlag(false);
                // 唤醒输入线程
                resource.notify();
            }
        }
    }
}
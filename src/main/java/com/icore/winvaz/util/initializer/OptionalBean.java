package com.icore.winvaz.util.initializer;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Deciption 链式调用 bean 中 value 的方法
 * @Author wdq
 * @Create 2020/10/13 09:48
 * @Version 1.0.0
 */
public final class OptionalBean<T> {

    private static final OptionalBean<?> EMPTY = new OptionalBean<>();

    private final T value;

    private OptionalBean() {
        this.value = null;
    }

    /**
     * 空值会抛出空指针
     * @author wdq
     * @create 2020/10/13 09:50
     * @param value
     */
    private OptionalBean(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * 包装一个不为空的bean
     * @author wdq
     * @create 2020/10/13 09:51
     * @param value
     * @Return com.icore.winvaz.util.initializer.OptionalBean<T>
     * @exception
     */
    public static <T> OptionalBean<T> of(T value) {
        return new OptionalBean<>(value);
    }

    /**
     * 包装一个可能为空的bean
     * @author wdq
     * @create 2020/10/13 09:54
     * @param value
     * @Return com.icore.winvaz.util.initializer.OptionalBean<T>
     * @exception
     */
    public static <T> OptionalBean<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * 获取具体值
     * @author wdq
     * @create 2020/10/13 10:21
     * @param
     * @Return T
     * @exception
     */
    public T get() {
        return Objects.isNull(value) ? null : value;
    }

    /**
     * 获取一个可能为空的对象
     * @author wdq
     * @create 2020/10/13 10:24
     * @param function
     * @Return com.icore.winvaz.util.initializer.OptionalBean<R>
     * @exception
     */
    public <R> OptionalBean<R> getBean(Function<? super T, ? extends R> function) {
        return Objects.isNull(value) ? OptionalBean.empty() : OptionalBean.ofNullable(function.apply(value));
    }

    /**
     * 如果目标值为空，获取一个默认值
     * @author wdq
     * @create 2020/10/13 11:43
     * @param other
     * @Return T
     * @exception
     */
    public T orElse(T other) {
        return value != null ? value : other;
    }

    /**
     * 如果目标值为空，通过lambda表达式获取一个值
     * @author wdq
     * @create 2020/10/13 11:44
     * @param other
     * @Return T
     * @exception
     */
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    /**
     * 如果目标值为空，抛出一个异常
     * @author wdq
     * @create 2020/10/13 11:52
     * @param exceptionSupplier
     * @Return T
     * @exception
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X{
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public boolean isPresent() {
        return value != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalBean<?> that = (OptionalBean<?>) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * 空值常量
     * @author wdq
     * @create 2020/10/13 09:53
     * @param
     * @Return com.icore.winvaz.util.initializer.OptionalBean<T>
     * @exception
     */
    public static <T> OptionalBean<T> empty() {

        OptionalBean<T> none = (OptionalBean<T>) EMPTY;

        return none;
    }
}

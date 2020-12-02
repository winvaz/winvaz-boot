package com.icore.winvaz.dao;

import com.icore.winvaz.entity.Model;

import java.util.List;

public interface BaseMapper<T extends Model<ID>, ID> {

    /**
     * 保存单个对象
     *
     * @param model 单个对象，自增长编号自动返回到该对象。
     */
    void saveOne(T model);

    /**
     * 保存多个对象，使用该方法需要确保对象集合中至少包含一个对象。
     *
     * @param models 多个对象，所有的自增长编号自动返回到每个对象。
     */
    void save(List<T> models);

    /**
     * 保存或者更新单个对象
     *
     * @param model 单个对象，如果该对象存在则更新。
     */
    default void saveOrUpdate(T model) {
        ID id = model.getId();

        if (id instanceof Long) {
            if (((Long) id).equals(-1L)) {
                saveOne(model);
            } else {
                update(model);
            }
        }

        if (id instanceof Integer) {
            if (((Integer) id).equals(-1)) {
                saveOne(model);
            } else {
                update(model);
            }
        }
    }

    /**
     * 保存或者更新（包含所有属性，如空值属性）单个对象
     *
     * @param model 单个对象，如果该对象存在则更新。
     */
    default void saveOrUpdateNullable(T model) {
        ID id = model.getId();

        if (id instanceof Long) {
            if (((Long) id).equals(-1L)) {
                saveOne(model);
            } else {
                updateNullable(model);
            }
        }

        if (id instanceof Integer) {
            if (((Integer) id).equals(-1)) {
                saveOne(model);
            } else {
                updateNullable(model);
            }
        }
    }

    /**
     * 删除
     *
     * @param id 对象编号
     */
    void deleteOne(ID id);

    /**
     * 删除多个对象
     *
     * @param condition 条件
     */
    void delete(Condition<T> condition);

    /**
     * 查询单个对象
     *
     * @param id 对象编号
     *
     * @return 单个对象
     */
    T findOne(ID id);

    /**
     * 查询多个对象
     *
     * @param condition 条件
     *
     * @return 对象集合
     */
    List<T> find(Condition<T> condition);

    /**
     * 查询符合条件的第一个对象
     *
     * @param condition 条件
     *
     * @return 符合条件的第一个对象
     */
    default T findFirst(Condition<T> condition) {
        List<T> result = find(condition.page(1).size(1));
        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    /**
     * 查询对象数量
     *
     * @param condition 条件
     *
     * @return 符合条件的对象的数量
     */
    long count(Condition<T> condition);

    /**
     * 分页
     *
     * @param condition 条件
     *
     * @return 分页数据
     */
    default PageData<T> sliceup(Condition<T> condition) {
        List<T> data = find(condition);
        long total = count(condition);

        return new PageData<>(data, condition.getPage(), condition.getSize(), total);

    }

    /**
     * 条件判断是否存在
     *
     * @param condition 条件
     *
     * @return 是否存在
     */
    default boolean exists(Condition<T> condition) {
        List<T> data = find(condition);
        return data != null && data.size() > 0;
    }

    /**
     * 更新单个对象，将会排除空值属性
     *
     * @param model 单个对象
     */
    void update(T model);

    /**
     * 更新单个对象，包含所有属性，如空值属性
     *
     * @param model 单个对象
     */
    void updateNullable(T model);

}

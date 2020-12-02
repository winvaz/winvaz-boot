package com.icore.winvaz.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deciption 用于数据访问中条件查询
 * @Author wdq
 * @Create 2019/12/18 16:07
 */
public final class Condition<T> {

    // 默认的访问级别的方法提供给基础框架调用

    private Class<T> type;

    private int page;
    private int size;

    private String orderBy;
    private Sort sort;

    private List<Criteria> criterions = new ArrayList<>();

    public Condition(Class<T> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    int getSkip() {
        return (page - 1) * size;
    }

    String getOrderBy() {
        return orderBy;
    }

    String getSort() {
        return sort.name();
    }

    /**
     * 设置页码
     *
     * @param page 页码
     *
     * @return 当前对象
     */
    public Condition<T> page(int page) {
        this.page = page;
        return this;
    }

    /**
     * 设置页容量
     *
     * @param size 页容量
     *
     * @return 当前对象
     */
    public Condition<T> size(int size) {
        this.size = size;
        return this;
    }

    /**
     * 设置升序的属性
     *
     * @param property 属性
     *
     * @return 当前对象
     */
    public Condition<T> asc(String property) {
        this.orderBy = column(property);
        this.sort = Sort.ASC;
        return this;
    }

    /**
     * 设置降序的属性
     *
     * @param property 属性
     *
     * @return 当前对象
     */
    public Condition<T> desc(String property) {
        this.orderBy = column(property);
        this.sort = Sort.DESC;
        return this;
    }

    boolean isSlice() {
        return this.page != 0 && this.size != 0;
    }

    List<Criteria> getCriterions() {
        return criterions;
    }

    /**
     * 返回条件规则集合，此方法不能重复调用。
     *
     * @return 条件规则集合
     */
    public Criteria where() {

        if (!criterions.isEmpty()) {
            throw new IllegalStateException("Duplicate call this method");
        }

        Criteria criteria = new Criteria();

        this.criterions.add(criteria);
        return criteria;
    }

    /**
     * 清除
     *
     * @return 当前对象
     */
    public Condition<T> clear() {
        this.criterions.clear();

        this.page = 0;
        this.size = 0;

        this.orderBy = null;

        return this;
    }

    /**
     * 返回属性对应的列名
     *
     * @param property 属性名称
     *
     * @return 列名
     */
    private String column(String property) {
        return ResultMappings.getColumn(type, property);
    }

    /**
     * 条件规则集合
     *
     * @author gaigeshen
     * @since 04/26 2017
     */
    public class Criteria {

        private List<Criterion> criteria = new ArrayList<>();

        public List<Criterion> getCriteria() {
            return this.criteria;
        }

        /**
         * 接条件或
         *
         * @return 返回新的条件规则集合
         */
        public Criteria or() {
            Criteria criteria = new Criteria();

            criterions.add(criteria);
            return criteria;
        }

        /**
         * 结束条件组合
         */
        public Condition<T> end() {
            return Condition.this;
        }

        /**
         * 返回是否包含任何条件规则
         *
         * @return 是否包含任何条件规则
         */
        public boolean isValid() {
            return this.criteria.size() > 0;
        }

        /**
         * 属性为空
         *
         * @param property 属性名称
         *
         * @return 当前对象
         */
        public Criteria isNull(String property) {
            this.criterion(column(property), " is null");
            return this;
        }

        /**
         * 属性不为空
         *
         * @param property 属性名称
         *
         * @return 当前对象
         */
        public Criteria isNotNull(String property) {
            this.criterion(column(property), " is not null");
            return this;
        }

        /**
         * 属性等于指定的值
         *
         * @param property 属性名称
         * @param value    指定的值
         *
         * @return 当前对象
         */
        public Criteria equalTo(String property, Object value) {
            this.criterion(column(property), " = ", value);
            return this;
        }

        /**
         * 属性不等于指定的值
         *
         * @param property 属性名称
         * @param value    指定的值
         *
         * @return 当前对象
         */
        public Criteria notEqualTo(String property, Object value) {
            this.criterion(column(property), " != ", value);
            return this;
        }

        /**
         * 属性在指定的值之间
         *
         * @param property 属性名称
         * @param value1   最小的值
         * @param value2   最大的值
         *
         * @return 当前对象
         */
        public Criteria between(String property, Object value1, Object value2) {
            this.criterion(column(property), " between ", value1, value2);
            return this;
        }

        /**
         * 属性大于指定的值
         *
         * @param property 属性名称
         * @param value    指定的值
         *
         * @return 当前对象
         */
        public Criteria greaterThan(String property, Object value) {
            this.criterion(column(property), " > ", value);
            return this;
        }

        /**
         * 属性小于指定的值
         *
         * @param property 属性名称
         * @param value    指定的值
         *
         * @return 当前对象
         */
        public Criteria lessThan(String property, Object value) {
            this.criterion(column(property), " < ", value);
            return this;
        }

        private void criterion(String column, String operator) {
            this.criteria.add(new Criterion(column, operator));
        }

        private void criterion(String column, String operator, Object value) {
            this.criteria.add(new Criterion(column, operator, value));
        }

        private void criterion(String column, String operator, Object value1, Object value2) {
            this.criteria.add(new Criterion(column, operator, value1, value2));
        }
    }

    /**
     * 条件规则
     *
     * @author gaigeshen
     * @since 04/26 2017
     */
    class Criterion {

        private String column;
        private String operator;

        private Object value;
        private Object secondValue;

        private boolean noValue;
        private boolean betweenValue;
        private boolean listValue;

        Criterion(String column, String operator) {
            this.column = column;
            this.operator = operator;
            this.noValue = true;
        }

        Criterion(String column, String operator, Object value) {
            this.column = column;
            this.operator = operator;
            this.value = value;

            if (value instanceof List<?>) {
                this.listValue = true;
            }
        }

        Criterion(String column, String operator, Object value, Object secondValue) {
            this.column = column;
            this.operator = operator;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }

        public String getColumn() {
            return column;
        }

        public String getOperator() {
            return operator;
        }

        public String getCondition() {
            return getColumn() + getOperator();
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

    }

    /**
     * 排序方式
     *
     * @author gaigeshen
     * @since 04/26 2017
     */
    enum Sort {

        ASC, DESC;

    }

}

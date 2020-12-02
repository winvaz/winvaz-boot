package com.icore.winvaz.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icore.winvaz.dao.ResultMappings;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.mapping.ResultMapping;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author wdq
 * @Create 2019/12/18 14:15
 */
public abstract class BaseModel implements Model<Long>, Serializable {

    private Long id = -1L;

    /**
     * 创建人
     * @author wdq
     * @create 2020/8/21 17:55
     * @param null
     * @Return
     * @exception
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人")
    private String createUser;

    /**
     * 更新人
     * @author wdq
     * @create 2020/8/21 17:55
     * @param null
     * @Return
     * @exception
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新人")
    private String updateUser;

    /**
     * 创建时间
     * @author wdq
     * @create 2020/8/21 17:57
     * @param null
     * @Return
     * @exception
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     * @author wdq
     * @create 2020/8/21 17:57
     * @param null
     * @Return
     * @exception
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;



    @JsonIgnore
    /**
     * All properties mapping but id
     * @author wdq
     * @create 2020/8/21 17:54
     * @param null
     * @Return
     * @exception
     */
    private List<ResultMappingValue> mappingValues = new ArrayList<>();

    public BaseModel() {

        List<ResultMapping> mappings = ResultMappings.getMappings(getClass());

        for (ResultMapping mapping : mappings) {

            if (!mapping.getProperty().equals("id")) {
                this.mappingValues.add(new ResultMappingValue(mapping));
            }
        }
    }

    @JsonIgnore
    public List<ResultMappingValue> getValues() {

        try {
            for (ResultMappingValue mapping : mappingValues) {

                ResultMapping rm = mapping.getMapping();

                String property = rm.getProperty();

                Field field = FieldUtils.getField(getClass(), property, true);

                mapping.value(field.get(this));
            }

            return Collections.unmodifiableList(mappingValues);

        } catch (IllegalAccessException e) {
            throw new IllegalStateException(
                    "Cannot get field value of type " + getClass().getName());
        }

    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        BaseModel that = (BaseModel) obj;

        return this.id.equals(that.id);
    }

    class ResultMappingValue {

        private ResultMapping mapping;
        private Object value;

        public ResultMappingValue(ResultMapping mapping) {
            this.mapping = mapping;
        }

        public String property() {
            return mapping.getProperty();
        }

        public String column() {
            return mapping.getColumn();
        }

        public Object getValue() {
            return value;
        }

        public void value(Object value) {
            this.value = value;
        }

        public ResultMapping getMapping() {
            return mapping;
        }
    }
}
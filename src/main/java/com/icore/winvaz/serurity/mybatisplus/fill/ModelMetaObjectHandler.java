package com.icore.winvaz.serurity.mybatisplus.fill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.icore.winvaz.util.common.LogUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/21 18:05
 * @Version 1.0.0
 */
public class ModelMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建人
     */
    private String CREATE_USER = "createUser";

    /**
     * 更新人
     */
    private String UPDATE_USER = "updateUser";

    /**
     * 创建时间
     */
    private String CREATE_TIME = "createTime";

    /**
     * 更新时间
     */
    private String UPDATE_TIME = "updateTime";


    @Override
    public void insertFill(MetaObject metaObject) {
        LogUtils.info("start insert fill ....");
        final Object createTime = getFieldValByName(CREATE_TIME, metaObject);
        final Object updateTime = getFieldValByName(UPDATE_TIME, metaObject);
        if (Objects.isNull(createTime)) {
            this.strictUpdateFill(metaObject, CREATE_TIME, () -> LocalDateTime.now(), LocalDateTime.class);
        }
        if (Objects.isNull(updateTime)) {
            this.strictUpdateFill(metaObject, UPDATE_TIME, () -> LocalDateTime.now(), LocalDateTime.class);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LogUtils.info("start update fill .....");
        final Object updateTime = getFieldValByName(UPDATE_TIME, metaObject);
        if (Objects.isNull(updateTime) ) {
            this.strictUpdateFill(metaObject, UPDATE_TIME, () -> LocalDateTime.now(), LocalDateTime.class);
        }
    }
}

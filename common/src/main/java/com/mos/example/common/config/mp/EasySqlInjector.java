package com.mos.example.common.config.mp;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.apache.ibatis.session.Configuration;

import java.util.List;

/**
 * 自定义SQL注入器<br/>
 * 扩展MybatisPlus默认的SQL注入功能，添加批量插入方法
 *
 * @author mos
 */
public class EasySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Configuration configuration, Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(configuration, mapperClass, tableInfo);
        // 添加批量插入方法
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}

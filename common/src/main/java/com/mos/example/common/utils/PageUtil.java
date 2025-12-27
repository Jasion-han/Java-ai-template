package com.mos.example.common.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;

/**
 * 分页工具类
 *
 * @author mos
 */
public class PageUtil {

    /**
     * 为MybatisPlus Page对象追加排序条件
     *
     * @param page  要修改的Page对象
     * @param order 排序字符串（例：id asc,num desc）
     * @return 修改后的Page对象（同一实例）
     */
    public static <T> Page<T> addOrder(Page<T> page, String order) {
        if (page == null) {
            throw new IllegalArgumentException("Page不能为null");
        }

        if (StringUtils.isNotBlank(order)) {
            Arrays.stream(order.split(","))
                    .map(s -> s.split(" "))
                    .filter(arr -> arr.length == 2)
                    .forEach(arr -> {
                        OrderItem orderItem = new OrderItem();
                        // 这里使用mp的StringUtils
                        String column = StringUtils.camelToUnderline(arr[0]);
                        boolean asc = "asc".equalsIgnoreCase(arr[1]);
                        orderItem.setColumn(column);
                        orderItem.setAsc(asc);
                        page.addOrder(orderItem);
                    });
        }
        return page;
    }
}

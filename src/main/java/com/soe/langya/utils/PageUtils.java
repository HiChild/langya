package com.soe.langya.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtils {
    /**
     * 手动分页
     * @param currentPage 当前页
     * @param pageSize
     * @param list
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> getPageInfo(int currentPage, int pageSize, List<T> list) {
        int total = list.size();
        if (total == 0) {
            return new PageInfo<T>(new Page<>());
        }
        //currentPage = 1时为首页，也就是第一页
        //故 需要截取 [(currentPage-1) * pageSize , currentPage * pageSize]的字段
        if (pageSize <= 0) {
            pageSize = 5;
        }
        if (currentPage<=0){
            currentPage =1;
        }
        if (pageSize>total){
            pageSize = total;
        }
        int maxPageNum = (int) Math.ceil((total*1.0)/pageSize);

        if (currentPage> maxPageNum) {
            currentPage = maxPageNum;
        }

        if (total > pageSize) {

            int toIndex = currentPage * pageSize;
            if (toIndex > total) {
                toIndex = total;
            }

            list = list.subList((currentPage-1) * pageSize , toIndex);
        }
        Page<T> page = new Page<>(currentPage, pageSize);
        System.out.println("PageSize"+page.getPageSize());
        page.addAll(list);
        page.setPages((total + pageSize - 1) / pageSize);
        page.setTotal(total);
        PageInfo<T> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }
}

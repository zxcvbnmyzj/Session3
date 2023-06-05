package com.yang.session.Mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HistoryMapper {

    @MapKey("id")
    List<Map<String,Object>> getHistoryList();

    Integer addHistory(Map<String,Object> condition);
}

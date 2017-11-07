package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.pojo.Notice;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by myname on 2017/9/26.
 */
public interface NoticeMapper extends Mapper<Notice> {

    //查看所有公告
    @Select("select * from notice")
    List<Notice> findAll();
}

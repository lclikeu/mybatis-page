import cn.itcast.mybatis.mapper.NoticeMapper;
import cn.itcast.mybatis.pojo.Notice;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by myname on 2017/9/26.
 */
public class NoticeMapperTest {

    private NoticeMapper noticeMapper;

    //获取sqlSESSION
    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {

        //加载mybatis-config.xml文件产生输入流
        InputStream resourceAsStream =
                Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取代理对象
        noticeMapper = sqlSession.getMapper(NoticeMapper.class);

    }

    @Test
    public void findAll(){
        List<Notice> all = noticeMapper.findAll();
        System.out.println(all);
    }


    /**
     * 分页查询
     */
    @Test
    public void findAllPage(){
        //设置分页
        PageHelper.startPage(1,3);

        //查询
        List<Notice> all = noticeMapper.findAll();

        //创建PageInfo封装数据
        PageInfo<Notice> pageInfo = new PageInfo<>(all);
        for (Notice notice : pageInfo.getList()) {
            System.out.println(notice);
        }
        System.out.println("总共 " + pageInfo.getTotal() + " 条数据；当前第 " + pageInfo.getPageNum()
                + " 页；总共 " + pageInfo.getPages() + " 页；。");
    }
}

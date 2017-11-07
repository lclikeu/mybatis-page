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
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by myname on 2017/9/26.
 */
public class MapperTest {

    //定义数据访问接口代理对象
    private NoticeMapper noticeMapper;

    //获取sqlssion
    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        //加载mybatis核心配置文件
        InputStream resourceAsStream =
                Resources.getResourceAsStream("mybatis-config.xml");

        //获取代理对象
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(resourceAsStream);

        sqlSession = sqlSessionFactory.openSession(true);

        noticeMapper = sqlSession.getMapper(NoticeMapper.class);

        //创建MapperHelper
        MapperHelper helper = new MapperHelper();

       // 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册
        helper.registerMapper(NoticeMapper.class);

        //执行配置信息
        helper.processConfiguration(this.sqlSession.getConfiguration());

    }

    /**
     * 根据ID查询
     */
    @Test
    public void findOne() {
        Notice notice = noticeMapper.selectByPrimaryKey(1L);
        System.out.println(notice);
    }

    /** 查询所有 */
    @Test
    public void findAll() {
        List<Notice> notices = noticeMapper.selectAll();
        System.out.println(notices);
    }

    /**
     * 分页查询
     */
    @Test
    public void findAllPage(){
        //开启分页
        PageHelper.startPage(1,3);

        //查询
        List<Notice> notices = noticeMapper.selectAll();

        //创建pageInfo
        PageInfo<Notice> pageInfo = new PageInfo<>(notices);
        for (Notice notice : pageInfo.getList()) {
            System.out.println(notice);
        }
        System.out.println("总共 " + pageInfo.getTotal() + " 条数据；当前第 " + pageInfo.getPageNum()
                + " 页；总共 " + pageInfo.getPages() + " 页；。");
    }

    /** 添加 */
    @Test
    public void save() {
        Notice n = new Notice();
        n.setTitle("李小华");
        n.setContent("李中华");
        noticeMapper.insertSelective(n);
        System.out.println(n.getId());
    }

    /** 修改 */
    @Test
    public void update() {
        Notice n = new Notice();
        n.setId(56L);
        n.setTitle("李大华");
        noticeMapper.updateByPrimaryKeySelective(n);
        System.out.println(n.getId());
    }

    /** 删除 */
    @Test
    public void delete() {
        noticeMapper.deleteByPrimaryKey(56L);
    }
}

package com.gmall.seckill;

import com.gmall.seckill.po.GoodsBo;
import com.gmall.seckill.dao.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillApplicationTests {

	@Resource
	private DataSource dataSource;

	@Resource
	private GoodsMapper goodsMapper;

	@Test
	public void contextLoads() throws SQLException {
		//org.apache.tomcat.jdbc.pool.DataSource
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}

	@Test
	public void test01(){
		List<GoodsBo> goodsBos = goodsMapper.selectAllGoods();
		for (GoodsBo goodsBo : goodsBos){
			log.info(goodsBo+"");
		}
	}
}

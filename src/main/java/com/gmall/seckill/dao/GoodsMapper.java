package com.gmall.seckill.dao;

import com.gmall.seckill.po.GoodsBo;
import com.gmall.seckill.po.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsBo> selectAllGoods ();

    GoodsBo getseckillGoodsBoByGoodsId(long goodsId);

    int updateStock(long goodsId);
}
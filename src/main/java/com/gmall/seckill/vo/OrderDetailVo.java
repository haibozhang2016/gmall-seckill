package com.gmall.seckill.vo;

import com.gmall.seckill.po.GoodsBo;
import com.gmall.seckill.po.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVo {

    private GoodsBo goods;

    private OrderInfo order;
}

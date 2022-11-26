package com.ruoyi.gsx.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gsx.mapper.GsxGoodMapper;
import com.ruoyi.gsx.domain.GsxGood;
import com.ruoyi.gsx.service.IGsxGoodService;

/**
 * GSX商品Service业务层处理
 * 
 * @author chehongchao
 * @date 2022-11-26
 */
@Service
public class GsxGoodServiceImpl implements IGsxGoodService 
{
    @Autowired
    private GsxGoodMapper gsxGoodMapper;

    /**
     * 查询GSX商品
     * 
     * @param goodId GSX商品主键
     * @return GSX商品
     */
    @Override
    public GsxGood selectGsxGoodByGoodId(Long goodId)
    {
        return gsxGoodMapper.selectGsxGoodByGoodId(goodId);
    }

    /**
     * 查询GSX商品列表
     * 
     * @param gsxGood GSX商品
     * @return GSX商品
     */
    @Override
    public List<GsxGood> selectGsxGoodList(GsxGood gsxGood)
    {
        return gsxGoodMapper.selectGsxGoodList(gsxGood);
    }

    /**
     * 新增GSX商品
     * 
     * @param gsxGood GSX商品
     * @return 结果
     */
    @Override
    public int insertGsxGood(GsxGood gsxGood)
    {
        gsxGood.setCreateTime(DateUtils.getNowDate());
        return gsxGoodMapper.insertGsxGood(gsxGood);
    }

    /**
     * 修改GSX商品
     * 
     * @param gsxGood GSX商品
     * @return 结果
     */
    @Override
    public int updateGsxGood(GsxGood gsxGood)
    {
        gsxGood.setUpdateTime(DateUtils.getNowDate());
        return gsxGoodMapper.updateGsxGood(gsxGood);
    }

    /**
     * 批量删除GSX商品
     * 
     * @param goodIds 需要删除的GSX商品主键
     * @return 结果
     */
    @Override
    public int deleteGsxGoodByGoodIds(Long[] goodIds)
    {
        return gsxGoodMapper.deleteGsxGoodByGoodIds(goodIds);
    }

    /**
     * 删除GSX商品信息
     * 
     * @param goodId GSX商品主键
     * @return 结果
     */
    @Override
    public int deleteGsxGoodByGoodId(Long goodId)
    {
        return gsxGoodMapper.deleteGsxGoodByGoodId(goodId);
    }
}

package com.ruoyi.gsx.service;

import java.util.List;
import com.ruoyi.gsx.domain.GsxGood;

/**
 * GSX商品Service接口
 * 
 * @author chehongchao
 * @date 2022-11-26
 */
public interface IGsxGoodService 
{
    /**
     * 查询GSX商品
     * 
     * @param goodId GSX商品主键
     * @return GSX商品
     */
    public GsxGood selectGsxGoodByGoodId(Long goodId);

    /**
     * 查询GSX商品列表
     * 
     * @param gsxGood GSX商品
     * @return GSX商品集合
     */
    public List<GsxGood> selectGsxGoodList(GsxGood gsxGood);

    /**
     * 新增GSX商品
     * 
     * @param gsxGood GSX商品
     * @return 结果
     */
    public int insertGsxGood(GsxGood gsxGood);

    /**
     * 修改GSX商品
     * 
     * @param gsxGood GSX商品
     * @return 结果
     */
    public int updateGsxGood(GsxGood gsxGood);

    /**
     * 批量删除GSX商品
     * 
     * @param goodIds 需要删除的GSX商品主键集合
     * @return 结果
     */
    public int deleteGsxGoodByGoodIds(Long[] goodIds);

    /**
     * 删除GSX商品信息
     * 
     * @param goodId GSX商品主键
     * @return 结果
     */
    public int deleteGsxGoodByGoodId(Long goodId);
}

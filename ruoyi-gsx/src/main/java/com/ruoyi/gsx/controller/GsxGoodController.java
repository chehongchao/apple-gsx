package com.ruoyi.gsx.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.gsx.domain.GsxGood;
import com.ruoyi.gsx.service.IGsxGoodService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * GSX商品Controller
 *
 * @author chehongchao
 * @date 2022-11-26
 */
@RestController
@RequestMapping("/gsx/good")
public class GsxGoodController extends BaseController
{
    @Autowired
    private IGsxGoodService gsxGoodService;

    /**
     * 查询GSX商品列表
     */
    @PreAuthorize("@ss.hasPermi('gsx:good:list')")
    @GetMapping("/list")
    public TableDataInfo list(GsxGood gsxGood)
    {
        startPage();
        List<GsxGood> list = gsxGoodService.selectGsxGoodList(gsxGood);
        return getDataTable(list);
    }

    /**
     * 导出GSX商品列表
     */
    @PreAuthorize("@ss.hasPermi('gsx:good:export')")
    @Log(title = "GSX商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GsxGood gsxGood)
    {
        List<GsxGood> list = gsxGoodService.selectGsxGoodList(gsxGood);
        ExcelUtil<GsxGood> util = new ExcelUtil<GsxGood>(GsxGood.class);
        util.exportExcel(response, list, "GSX商品数据");
    }

    /**
     * 获取GSX商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('gsx:good:query')")
    @GetMapping(value = "/{goodId}")
    public AjaxResult getInfo(@PathVariable("goodId") Long goodId)
    {
        return success(gsxGoodService.selectGsxGoodByGoodId(goodId));
    }

    /**
     * 新增GSX商品
     */
    @PreAuthorize("@ss.hasPermi('gsx:good:add')")
    @Log(title = "GSX商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody GsxGood gsxGood)
    {
        return toAjax(gsxGoodService.insertGsxGood(gsxGood));
    }

    /**
     * 修改GSX商品
     */
    @PreAuthorize("@ss.hasPermi('gsx:good:edit')")
    @Log(title = "GSX商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody GsxGood gsxGood)
    {
        return toAjax(gsxGoodService.updateGsxGood(gsxGood));
    }

    /**
     * 删除GSX商品
     */
    @PreAuthorize("@ss.hasPermi('gsx:good:remove')")
    @Log(title = "GSX商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodIds}")
    public AjaxResult remove(@PathVariable Long[] goodIds)
    {
        return toAjax(gsxGoodService.deleteGsxGoodByGoodIds(goodIds));
    }
}

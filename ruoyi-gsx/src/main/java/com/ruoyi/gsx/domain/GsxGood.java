package com.ruoyi.gsx.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * GSX商品对象 gsx_good
 *
 * @author chehongchao
 * @date 2022-11-26
 */
@Validated
public class GsxGood extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long goodId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodName;

    /** 商品分类 */
    @Excel(name = "商品分类")
    private String goodClass;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodImage;

    /** 商品详情 */
    @Excel(name = "商品详情")
    private String goodDetail;

    /** 商品库存 */
    @Excel(name = "商品库存")
    private Long goodStock;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal goodPrice;

    /** 商品成本价 */
    @Excel(name = "商品成本价")
    private BigDecimal goodCostPrice;

    /** 商品划线价 */
    @Excel(name = "商品划线价")
    private BigDecimal goodMarkPrice;

    /** 商品库存预警值 */
    @Excel(name = "商品库存预警值")
    private String lowStock;

    /** api类型 */
    @Excel(name = "api类型")
    private String apiType;

    /** api地址 */
    @Excel(name = "api地址")
    private String apiUrl;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long orderNum;

    /** 商品删除状态（0正常 1删除） */
    @Excel(name = "商品删除状态", readConverterExp = "0=正常,1=删除")
    private Integer isDeleted;

    /** 商品上架状态（0已上架 1未上架） */
    @Excel(name = "商品上架状态", readConverterExp = "0=已上架,1=未上架")
    private Integer isPublish;

    public void setGoodId(Long goodId)
    {
        this.goodId = goodId;
    }

    public Long getGoodId()
    {
        return goodId;
    }
    public void setGoodName(String goodName)
    {
        this.goodName = goodName;
    }

    public String getGoodName()
    {
        return goodName;
    }
    public void setGoodClass(String goodClass)
    {
        this.goodClass = goodClass;
    }

    public String getGoodClass()
    {
        return goodClass;
    }
    public void setGoodImage(String goodImage)
    {
        this.goodImage = goodImage;
    }

    @Size(min=0, max=500, message="商品图片数量过多")
    public String getGoodImage()
    {
        return goodImage;
    }
    public void setGoodDetail(String goodDetail)
    {
        this.goodDetail = goodDetail;
    }

    public String getGoodDetail()
    {
        return goodDetail;
    }

    public void setGoodStock(Long goodStock)
    {
        this.goodStock = goodStock;
    }

    public Long getGoodStock()
    {
        return goodStock;
    }
    public void setGoodPrice(BigDecimal goodPrice)
    {
        this.goodPrice = goodPrice;
    }

    public BigDecimal getGoodPrice()
    {
        return goodPrice;
    }
    public void setGoodCostPrice(BigDecimal goodCostPrice)
    {
        this.goodCostPrice = goodCostPrice;
    }

    public BigDecimal getGoodCostPrice()
    {
        return goodCostPrice;
    }
    public void setGoodMarkPrice(BigDecimal goodMarkPrice)
    {
        this.goodMarkPrice = goodMarkPrice;
    }

    public BigDecimal getGoodMarkPrice()
    {
        return goodMarkPrice;
    }
    public void setLowStock(String lowStock)
    {
        this.lowStock = lowStock;
    }

    public String getLowStock()
    {
        return lowStock;
    }
    public void setApiType(String apiType)
    {
        this.apiType = apiType;
    }

    public String getApiType()
    {
        return apiType;
    }
    public void setApiUrl(String apiUrl)
    {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl()
    {
        return apiUrl;
    }
    public void setOrderNum(Long orderNum)
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum()
    {
        return orderNum;
    }
    public void setIsDeleted(Integer isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted()
    {
        return isDeleted;
    }
    public void setIsPublish(Integer isPublish)
    {
        this.isPublish = isPublish;
    }

    public Integer getIsPublish()
    {
        return isPublish;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodId", getGoodId())
            .append("goodName", getGoodName())
            .append("goodClass", getGoodClass())
            .append("goodImage", getGoodImage())
            .append("goodDetail", getGoodDetail())
            .append("goodStock", getGoodStock())
            .append("goodPrice", getGoodPrice())
            .append("goodCostPrice", getGoodCostPrice())
            .append("goodMarkPrice", getGoodMarkPrice())
            .append("lowStock", getLowStock())
            .append("apiType", getApiType())
            .append("apiUrl", getApiUrl())
            .append("orderNum", getOrderNum())
            .append("isDeleted", getIsDeleted())
            .append("isPublish", getIsPublish())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

package com.creatorblue.cbitedu.ty.domain;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;

/**
 * 品牌
 * 
 */
public class TtyBrand extends BaseDomain{
    /**
     * 品牌id
     */
    private String brandId;

    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 资源类
     * @return
     */
    private TsysAttach tsysAttach;
    /**
     * 品牌状态
     * @return
     */
    private String brandState;

	public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

	public TsysAttach getTsysAttach() {
		return tsysAttach;
	}

	public void setTsysAttach(TsysAttach tsysAttach) {
		this.tsysAttach = tsysAttach;
	}
    
	public String getBrandState() {
		return brandState;
	}

	public void setBrandState(String brandState) {
		this.brandState = brandState;
	}
}
package com.creatorblue.cbitedu.ty.domain;
import java.util.Date;


public class TtyAdvertising {
    /**
     * 广告id
     */
    private String advertisingId;

    /**
     * 广告图片开始时间
     */
    private String advertisingBeginTime;

    /**
     * 广告图结束时间
     */
    private String advertisingEndTime;

    /**
     * 状态
     */
    private String advertisingState;
    /**
     * 资源类
     * @return
     */
    private TsysAttach tsysAttach;
    private String advertisingName;
    public String getAdvertisingId() {
        return advertisingId;
    }

    public void setAdvertisingId(String advertisingId) {
        this.advertisingId = advertisingId == null ? null : advertisingId.trim();
    }

    public String getAdvertisingBeginTime() {
        return advertisingBeginTime;
    }

    public void setAdvertisingBeginTime(String advertisingBeginTime) {
        this.advertisingBeginTime = advertisingBeginTime;
    }

    public String getAdvertisingEndTime() {
        return advertisingEndTime;
    }

    public void setAdvertisingEndTime(String advertisingEndTime) {
        this.advertisingEndTime = advertisingEndTime;
    }

    public String getAdvertisingState() {
        return advertisingState;
    }

    public void setAdvertisingState(String advertisingState) {
        this.advertisingState = advertisingState == null ? null : advertisingState.trim();
    }

	public TsysAttach getTsysAttach() {
		return tsysAttach;
	}

	public void setTsysAttach(TsysAttach tsysAttach) {
		this.tsysAttach = tsysAttach;
	}

	public String getAdvertisingName() {
		return advertisingName;
	}

	public void setAdvertisingName(String advertisingName) {
		this.advertisingName = advertisingName;
	}
    
}
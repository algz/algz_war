package com.sany.dynamic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="S_DYNAMICTASK")
public class DynamicTask {


	/**
	 * 任务ID
	 */
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "ALGZGenerator")
	private String id;
	

    /// <summary>
    /// 任务名称
    /// </summary>
	@Column(name = "TASKNAME")
	private String taskName;

    /// <summary>
    /// 机型ID
    /// </summary>
	@Column(name = "MODELID")
	private String modelId;

    /// <summary>
    /// 主机参数版本:主机参数版本(在确定机型后,选择主机参数版本).V1-20191204
    /// </summary>
	@Column(name = "WHOLEVERSION")
	private String wholeVersion;

    /// <summary>
    /// 编辑人(工程师)
    /// </summary>
	@Column(name = "EDITOR")
	private String editor;

	
    /// <summary>
    /// 状态:未发布/已发布/终版
    /// </summary>
	@Column(name = "STATE")
	private String state;

    /// <summary>
    /// 钻杆类型:机锁杆/摩阻杆
    /// </summary>
	@Column(name = "ZG_TYPE")
	private String zg_type;
	
    /// <summary>
    /// 整机参数任务ID
    /// </summary>
	@Column(name = "WHOLETASKID")
	private String wholeTaskId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getWholeVersion() {
		return wholeVersion;
	}

	public void setWholeVersion(String wholeVersion) {
		this.wholeVersion = wholeVersion;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZg_type() {
		return zg_type;
	}

	public void setZg_type(String zg_type) {
		this.zg_type = zg_type;
	}

	public String getWholeTaskId() {
		return wholeTaskId;
	}

	public void setWholeTaskId(String wholeTaskId) {
		this.wholeTaskId = wholeTaskId;
	}
    

    
    /// <summary>
    /// 载荷名称(用于版本号,唯一标准,整机参数与动力学1:1)
    /// 版本: 机型_MBD_WholeVersion_钻杆类型
    /// SR385_MBD_20200324_v2_机锁杆
    /// </summary>
//    public string  MBDName { get; set; }
//
//    public string ModelPath { get; set; }
//
//    public string ReportPath { get; set; }
//
//    public string ReportSummary { get; set; }
//
//
//    /// <summary>
//    /// 是否终版
//    /// </summary>
//    public string IsEnd { get; set; }
//
//
//
//
//    public string Version { get; set; }
//    /// <summary>
//    /// 调整施工至最小位置：35-40s
//    /// </summary>
//    public string JYZJ_TZSGZZXWZ { get; set; }
//    /// <summary>
//    /// 加载加压力：26~28s
//    /// </summary>
//    public string JYZJ_JZJYL { get; set; }
//    /// <summary>
//    /// 调整施工半径：10-20s
//    /// </summary>
//    public string JYZJ_TZSGBJ { get; set; }
//    /// <summary>
//    /// 加载动力头扭矩：5-6s
//    /// </summary>
//    public string JYZJ_JZDLTLJ { get; set; }
//    /// <summary>
//    /// 变幅至最近施工半径：70~85s
//    /// </summary>
//    public string TS_JZZTSL { get; set; }
//    /// <summary>
//    /// 加载至提升力：60-62s
//    /// </summary>
//    public string TS_BFZZJSGBG { get; set; }
//    /// <summary>
//    /// 变幅至施工半径：40-55s
//    /// </summary>
//    public string TS_BFZSGBJ { get; set; }
//
//    /// <summary>
//    /// 桅杆竖直：25-35s
//    /// </summary>
//    public string TS_WGSZ { get; set; }
//    /// <summary>
//    /// 加钻杆重量：15-20s
//    /// </summary>
//    public string TS_JZGZL { get; set; }
//    /// <summary>
//    /// 前倾桅杆至4度 0-10s
//    /// </summary>
//    public string TS_QQWG4D { get; set; }
//    /// <summary>
//    /// 起桅至桅杆竖直：75-95
//    /// </summary>
//    public string DLW_QWZWGSZ { get; set; }
//    /// <summary>
//    /// 加钻杆重量：45-50s
//    /// </summary>
//    public string DLW_JZGZL { get; set; }
//    /// <summary>
//    /// 变幅至最高：55~70s
//    /// </summary>
//    public string DLW_BFZG { get; set; }
//    /// <summary>
//    /// 变幅至最低：25-40s 
//    /// </summary>
//    public string DLW_BFZD { get; set; }
//    /// <summary>
//    /// 倒桅杆至水平： 0-20s
//    /// </summary>
//    public string DLW_LWGZSP { get; set; }
}

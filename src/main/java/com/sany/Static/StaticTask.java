package com.sany.Static;

/**
 * @author algz
 *
 */
public class StaticTask {
	
	private String id;

    /// <summary>
    /// 任务名称
    /// </summary>
    private String taskName;

    /// <summary>
    /// 编辑人ID
    /// </summary>
    private String editor;

    /// <summary>
    /// 动力学任务ID
    /// </summary>
    private String  dynamicId;
    
    /// <summary>
    /// 计算中(可再次调用仿真接口)=>完成/废弃(可删除)=>未发布=>已发布=>终版
    /// </summary>
    private String state;

    /// <summary>
    /// 模型地址
    /// </summary>
    private String cadPath;
    
    /// <summary>
    /// 模型名称
    /// </summary>
    private String cadName;
    
    
    /// <summary>
    /// 仿真工况
    /// </summary>
    private String gk;
    
    /// <summary>
    /// 载荷版本
    /// </summary>
    private String zh_Version;
    
    /// <summary>
    /// 任务创建时间
    /// </summary>
    private String createDate;

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

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(String dynamicId) {
		this.dynamicId = dynamicId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCadPath() {
		return cadPath;
	}

	public void setCadPath(String cadPath) {
		this.cadPath = cadPath;
	}

	public String getZh_Version() {
		return zh_Version;
	}

	public void setZh_Version(String zh_Version) {
		this.zh_Version = zh_Version;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getGk() {
		return gk;
	}

	public void setGk(String gk) {
		this.gk = gk;
	}

	public String getCadName() {
		return cadName;
	}

	public void setCadName(String cadName) {
		this.cadName = cadName;
	}
    
    
    
//    /// <summary>
//    /// 编辑人
//    /// </summary>
//    public string EditorName { get; set; }
//
//    /// <summary>
//    /// 机型ID
//    /// </summary>
//    public string ModelId { get; set; }
//
//    /// <summary>
//    /// 机型名称
//    /// </summary>
//    public string ModelName { get; set; }
//
//
//    /// <summary>
//    /// 部件ID(关联部件表)
//    /// </summary>
//    public string PartId { get; set; }
//
//    /// <summary>
//    /// 部件代号
//    /// </summary>
//    public string PartCode { get; set; }
//
//    /// <summary>
//    /// 部件中文名称
//    /// </summary>
//    public String PartName { get; set; }
//
//    /// <summary>
//    /// CAD模型名称
//    /// </summary>
//    public string CADName { get; set; }
//
//
//
//    /// <summary>
//    /// 版本
//    /// </summary>
//    public string Version { get; set; }
//
//
//
//    /// <summary>
//    /// CAE模型
//    /// </summary>
//    public string CAEPath { get; set; }
//
//    /// <summary>
//    /// CAE结果模型
//    /// </summary>
//    public string CAEResultPath { get; set; }
//
//    /// <summary>
//    /// 热点应力表（表单）
//    /// </summary>
//    public string LDYLB_ID { get; set; }
//
//    /// <summary>
//    /// 仿真报告路径
//    /// </summary>
//    public string ReportPath { get; set; }
//
//    /// <summary>
//    /// 报告摘要(仿真程序上传时自动写入)
//    /// </summary>
//    public string  ReportSummary { get; set; }
//
//    /// <summary>
//    /// 定版（bool,是/否）
//    /// </summary>
//    public string DB { get; set; }
//
//    /// <summary>
//    /// 应力测试方案（定版时,按评判标准自动生成应力测试方案）
//    /// </summary>
//    public string YLCSFA { get; set; }
//
//
//
//    /// <summary>
//    /// 转盘类型:1圆转盘,2方转盘
//    /// </summary>
//    public string TurntableType { get; set; }
//
//    /// <summary>
//    /// 有无加长桅杆:1有,0无
//    /// </summary>
//    public string HasLengthenedMast { get; set; }
//
//    /// <summary>
//    /// 有无下桅杆:1有,0无
//    /// </summary>
//    public string HasDownMast { get; set; }
//
//    /// <summary>
//    /// 应力测试方案系数
//    /// </summary>
//    public string YLCSFAXS { get; set; }
}

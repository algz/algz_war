package com.sany.whole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="S_WHOLETASK")
public class WholeTask {

	/**
	 * 任务ID
	 */
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "ALGZGenerator")
	private String id;

    /// <summary>
    /// 机型ID
    /// </summary>
	@Column(name = "MODELID")
	private String modelId;


    /// <summary>
    /// 任务名称
    /// </summary>
	@Column(name = "TASKNAME")
    private String taskName;

    /// <summary>
    /// 编辑人(项目经理)
    /// </summary>
	@Column(name = "EDITOR")
    private String editor;

    /// <summary>
    /// 版本号
    /// </summary>
	@Column(name = "VERSION")
    private String version;

    /// <summary>
    /// CAD文件路径
    /// </summary>
	@Column(name = "CADPATH")
    private String cadPath;

    /// <summary>
    /// 计算中=>未发布=>已发布=>终版
    /// </summary>
	@Column(name = "STATE")
    private String state;

    /// <summary>
    /// 钻杆类型
    /// </summary>
	@Column(name = "ZG_TYPE")
    private  String zg_Type;

    /// <summary>
    /// 来源:1设计载荷,2试验载荷
    /// </summary>
	@Column(name = "FROMSOURCE")
    private String fromSource;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCadPath() {
		return cadPath;
	}

	public void setCadPath(String cadPath) {
		this.cadPath = cadPath;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZg_Type() {
		return zg_Type;
	}

	public void setZg_Type(String zg_Type) {
		this.zg_Type = zg_Type;
	}

	public String getFromSource() {
		return fromSource;
	}

	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}
	
	
}

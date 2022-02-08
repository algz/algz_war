package com.algz.platform.common.flow;

public class Cell {
	
//	id	string			节点/边的唯一标识，推荐使用具备业务意义的 ID，默认使用自动生成的 UUID。
	private String id;
	
//	shape	string			渲染节点/边的图形。节点对应的默认值为 'rect'，边对应的默认值为 'edge'。
	private String shape;
	
	
	
//	markup	Markup			节点/边的 SVG/HTML 片段。
//	attrs	Attr.CellAttrs			节点/边属性样式。
//	view	string			渲染节点/边的视图。
//	zIndex	number			节点/边在画布中的层级，默认根据节点/边添加顺序自动确定。
//	visible	boolean	true		节点/边是否可见。
//	parent	string			父节点。
//	children	string[]			子节点/边。
//	tools	ToolItem | ToolItem[] | Tools			工具选项。
//	data

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
}

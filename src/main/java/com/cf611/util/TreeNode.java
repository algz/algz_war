package com.cf611.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeNode {

	/**
	 * 唯一ID
	 * 被树的 (default)ExpandedKeys / (default)CheckedKeys / (default)SelectedKeys 属性所用。
	 * 注意：整个树范围内的所有节点的 key 值不能重复！
	 * key: string | number;
	 */
	private String key;
	
	/**
	 * 标题
	 * title?: React.ReactNode;
	 */
	private String title;
	
	/**
	 * 自定义属性
	 */
	private Map<String,?> extProps;
	
	/**
	 * 子结点集合
	 * children?: DataNode[];
	 */
	private List<TreeNode> children;
	
	
	//---------以上是必须项-------------

    
	/**
	 * 禁掉响应
	 *     disabled?: boolean;
	 */
	private Boolean disabled;
	
	/**
	 * 设置节点是否可被选中
	 *     selectable?: boolean;
	 */
	private Boolean selectable;
	
	/**
	 * 设置为叶子节点 (设置了 loadData 时有效)。为 false 时会强制将其作为父节点
	 *     isLeaf?: boolean;
	 */
	private Boolean isLeaf;
	
	/**
	 * icon	自定义图标。可接收组件，props 为当前节点 props
	 *     icon?: IconType;
	 */
	private Boolean icon;
	
	/**
	 * 	checkable	当树为 checkable 时，设置独立节点是否展示 Checkbox	boolean	-
	 *     checkable?: boolean;
	 */
	private Boolean checkable;
	
	/**
	 * 	disableCheckbox	禁掉 checkbox
	 *     disableCheckbox?: boolean;
	 */
	private Boolean disableCheckbox;
	
    /**
     * 
     *    Set style of TreeNode. This is not recommend if you don't have any force requirement
     *		className?: string;
     */
    private String className;
    
    /**
     * style?: React.CSSProperties;
     */
    private String style;
    
    /**
     * switcherIcon?: IconType;
     */
    private String switcherIcon;
	
	
	/**
	 * args[0]=key,[1]=title,[2]=children
	 * @param args
	 */
	public TreeNode(String...args) {
		if(args!=null) {
			this.setKey(args.length>0?args[0]:null);
			this.setTitle(args.length>1?args[1]:null);
		}
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean getSelectable() {
		return selectable;
	}

	public void setSelectable(Boolean selectable) {
		this.selectable = selectable;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getIcon() {
		return icon;
	}

	public void setIcon(Boolean icon) {
		this.icon = icon;
	}

	public Boolean getCheckable() {
		return checkable;
	}

	public void setCheckable(Boolean checkable) {
		this.checkable = checkable;
	}

	public Boolean getDisableCheckbox() {
		return disableCheckbox;
	}

	public void setDisableCheckbox(Boolean disableCheckbox) {
		this.disableCheckbox = disableCheckbox;
	}

	public Map<String, ?> getExtProps() {
		return extProps;
	}

	public void setExtProps(Map<String, ?> extProps) {
		this.extProps = extProps;
	}

	
	

	
}

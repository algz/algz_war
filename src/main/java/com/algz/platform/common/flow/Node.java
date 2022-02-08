package com.algz.platform.common.flow;

public class Node extends Cell{

//	size	{ width: number; height: number }	{ width: 1, height: 1 }		节点大小。
	private Size size;
	
//	position	{ x: number; y: number }	-		节点位置。
	private Position position;
	
//	ports	object	-		链接桩。
	private PortMeta ports;
	
	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public PortMeta getPorts() {
		return ports;
	}

	public void setPorts(PortMeta ports) {
		this.ports = ports;
	}
	
	public class Position {

		private Integer x;
		
		private Integer y;

		public Integer getX() {
			return x;
		}

		public void setX(Integer x) {
			this.x = x;
		}

		public Integer getY() {
			return y;
		}

		public void setY(Integer y) {
			this.y = y;
		}
	}
	
	public class Size {
		private Integer width;
		
		private Integer height;

		public Integer getWidth() {
			return width;
		}

		public void setWidth(Integer width) {
			this.width = width;
		}

		public Integer getHeight() {
			return height;
		}

		public void setHeight(Integer height) {
			this.height = height;
		}

		
	}


	public class PortMeta{
		
	}
}

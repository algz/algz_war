package com.algz.platform.common.flow;

public class Edge extends Cell{

//	source	TerminalData			起点或起始节点、链接桩信息。
	private TerminalCellData source;
	
//	target	TerminalData			终点或终止节点、链接桩信息。
	private TerminalCellData target;

	public TerminalCellData getSource() {
		return source;
	}

	public void setSource(TerminalCellData source) {
		this.source = source;
	}

	public TerminalCellData getTarget() {
		return target;
	}

	public void setTarget(TerminalCellData target) {
		this.target = target;
	}
	


//	vertices	Point.PointLike[]			路径点。
//	router	RouterData			路由。
//	connector	ConnectorData			连接器。
//	labels	Label[] | string[]			标签。
//	defaultLabel
	
	
	
	public class TerminalCellData {
//      cell: string;
      private String cell;
//      port?: string;
      private String port;
		public String getCell() {
			return cell;
		}
		public void setCell(String cell) {
			this.cell = cell;
		}
		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
	}

}

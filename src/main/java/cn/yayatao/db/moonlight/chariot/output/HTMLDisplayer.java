package cn.yayatao.db.moonlight.chariot.output;

import cn.yayatao.db.moonlight.chariot.context.CompareResult;
import cn.yayatao.db.moonlight.chariot.context.MatchedColumnItme;
import cn.yayatao.db.moonlight.chariot.context.MatchedTableItme;
import cn.yayatao.db.moonlight.chariot.context.MatchedViewItme;

public class HTMLDisplayer implements Displayer {
	
	StringBuffer content = new StringBuffer("<style type=\"text/css\">  \r\n" + 
			"table.gridtable {  \r\n" + 
			"    font-family: verdana,arial,sans-serif;  \r\n" + 
			"    font-size:11px;  \r\n" + 
			"    color:#333333;  \r\n" + 
			"    border-width: 1px;  \r\n" + 
			"    border-color: #666666;  \r\n" + 
			"    border-collapse: collapse;  \r\n" + 
			"    width:100%;  \r\n "
			+ "margin:  5px 5px;                        \r\n"+	
			"}  \r\n" + 
			"table.gridtable th {  \r\n" + 
			"    border-width: 1px;  \r\n" + 
			"    padding: 8px;  \r\n" + 
			"    border-style: solid;  \r\n" + 
			"    border-color: #666666;  \r\n" + 
			"    background-color: #dedede;  \r\n" + 
			"}  \r\n" + 
			"table.gridtable td {  \r\n" + 
			"    border-width: 1px;  \r\n" + 
			"    padding: 8px;  \r\n" + 
			"    border-style: solid;  \r\n" + 
			"    border-color: #666666;  \r\n" + 
			"    background-color: #ffffff;  \r\n" + 
			"}  \r\n" + 
			"</style> <h1 style=\"text-align: center;\">不相同的表结构<h1> ");

	@Override
	public void show(MatchedTableItme tableItme, boolean ignoreEqual) {
		if(ignoreEqual && tableItme.compare() == CompareResult.EQUAL){
			return ;
		}
		
		if(tableItme.getResult() == CompareResult.LEFT_NOT_EXIST){
			String table = 
					  "<table class=\"gridtable\">"
					+ "		<tr>"
					+ "			<th style=\"width: 45%\"></th>"
					+ "			<th>左不存在</th>"
					+ "			<th style=\"width: 45%\">"+tableItme.getRight().getName()+"</th>"
					+ "		</tr>"
					+ "</talbe>";
			content.append(table);
		}else if(tableItme.getResult() == CompareResult.RIGHT_NOT_EXIST) {
			String table = 
					  "<table class=\"gridtable\">"
					+ "		<tr>"
					+ "			<th style=\"width: 45%\">"+tableItme.getLeft().getName()+"</th>"
					+ "			<th>右不存在</th>"
					+ "			<th style=\"width: 45%\"></th>"
					+ "		</tr>"
					+ "</talbe>";
			content.append(table);
		}else if(tableItme.getResult() == CompareResult.EQUAL || tableItme.getResult() == CompareResult.NOT_EQUAL) {
			String table = 
					  "<table class=\"gridtable\">"
					+ "		<tr>"
					+ "			<th style=\"width: 45%\" >"+tableItme.getLeft().getName()+"</th>"
					+ "			<th>"+(tableItme.getResult() == CompareResult.EQUAL? "相同":"不相同")+"</th>"
					+ "			<th style=\"width: 45%\">"+tableItme.getRight().getName()+"</th>"
					+ "		</tr>";
			content.append(table);
			for(MatchedColumnItme column: tableItme.getColumns()){
				content.append(markColumn(column,ignoreEqual));
			}
			content.append("</table>");
		}
	}

	private String markColumn(MatchedColumnItme column, boolean ignoreEqual) {
		if(ignoreEqual && column.compare() == CompareResult.EQUAL){
			return "";
		}
		if(column.getResult() == CompareResult.LEFT_NOT_EXIST){
			String tr = "<tr>"
					+ "			<td></td>"
					+ "			<td>左不存在</td>"
					+ "			<td>"+String.format("%s %s(%s) %s",column.getRight().getName(),column.getRight().getType(),column.getRight().getLength(),column.getRight().isNotNull() ?" NOT NULL ":" NULL")+"</td>"
					+ "	</tr>";
			return (tr);
		}else if (column.getResult() == CompareResult.RIGHT_NOT_EXIST){
			String tr = "<tr>"
					+ "			<td>"+String.format("%s %s(%s) %s",column.getLeft().getName(),column.getLeft().getType(),column.getLeft().getLength(),column.getLeft().isNotNull() ?" NOT NULL ":" NULL")+"</td>"
					+ "			<td>右不存在</td>"
					+ "			<td></td>"
					+ "	</tr>";
			return  (tr);
		}else if (column.getResult() == CompareResult.EQUAL || column.getResult() == CompareResult.NOT_EQUAL){
			String tr = "<tr>"
					+ "			<td>"+String.format("%s %s(%s,%s) %s %s",column.getLeft().getName(),column.getLeft().getType(),column.getLeft().getLength(),column.getLeft().getScale(),column.getLeft().isNotNull() ?" NOT NULL ":" NULL",column.getLeft().getComment())+"</td>"
					+ "			<td>"+(column.getResult() == CompareResult.EQUAL? "相同":"不相同") +"</td>"
					+ "			<td>"+String.format("%s %s(%s,%s) %s %s",column.getRight().getName(),column.getRight().getType(),column.getRight().getLength(),column.getRight().getScale(),column.getRight().isNotNull() ?" NOT NULL ":" NULL",column.getRight().getComment())+"</td>"
					+ "	</tr>";
			return  (tr);
		}
		return "";
	}

	@Override
	public void show(MatchedViewItme viewItme, boolean ignoreEqual) {
		if(ignoreEqual && viewItme.getResult() == CompareResult.EQUAL){
			return ;
		}
		
		
	}

	@Override
	public Object getContent() {
		return content;
	}
}

package cn.yayatao.db.moonlight.chariot.output;

import cn.yayatao.db.moonlight.chariot.context.MatchedTableItme;
import cn.yayatao.db.moonlight.chariot.context.MatchedViewItme;

public interface Displayer {
	
	/**
	 * 展示对比两个表格
	 * @param tableItme
	 * @param ignoreEqual
	 */
	public void show(MatchedTableItme tableItme, boolean ignoreEqual);

	
	/**
	 * 展示对比两个视图
	 * @param indexItme
	 * @param ignoreEqual
	 */
	public void show(MatchedViewItme  viewItme, boolean ignoreEqual);


	public Object getContent();
}

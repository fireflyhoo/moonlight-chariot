package cn.yayatao.db.moonlight.chariot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import cn.yayatao.db.moonlight.chariot.context.CompareResult;
import cn.yayatao.db.moonlight.chariot.context.MatchedColumnItme;
import cn.yayatao.db.moonlight.chariot.context.MatchedDatabaseContext;
import cn.yayatao.db.moonlight.chariot.context.MatchedDatabaseItme;
import cn.yayatao.db.moonlight.chariot.context.MatchedIndexItme;
import cn.yayatao.db.moonlight.chariot.context.MatchedTableItme;
import cn.yayatao.db.moonlight.chariot.context.MatchedViewItme;
import cn.yayatao.db.moonlight.chariot.model.Column;
import cn.yayatao.db.moonlight.chariot.model.Database;
import cn.yayatao.db.moonlight.chariot.model.Index;
import cn.yayatao.db.moonlight.chariot.model.Table;
import cn.yayatao.db.moonlight.chariot.model.View;
import cn.yayatao.db.moonlight.chariot.output.Displayer;

public class DatabaseComparator {

	Displayer displayer;

	/**
	 * @return the displayer
	 */
	public Displayer getDisplayer() {
		return displayer;
	}

	/**
	 * @param displayer
	 *            the displayer to set
	 */
	public void setDisplayer(Displayer displayer) {
		this.displayer = displayer;
	}

	public void compare(Database left, Database right) {
		MatchedDatabaseContext context = matcheDatabase(left, right);

		for (MatchedTableItme tableItme : context.getTables()) {
			displayer.show(tableItme, true);
		}

		for (MatchedViewItme viewItme : context.getViews()) {
			displayer.show(viewItme, true);
		}
	}

	/**
	 * 进行匹配 库索引和表
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	private MatchedDatabaseContext matcheDatabase(Database left, Database right) {
		MatchedDatabaseContext context = new MatchedDatabaseContext();

		MatchedDatabaseItme databaseItme = new MatchedDatabaseItme();
		databaseItme.setLeft(left);
		databaseItme.setRight(right);
		context.setDatabase(databaseItme);
		java.util.List<MatchedTableItme> tableItmes = new ArrayList<>();

		// 匹配表
		Set<Table> rightMatchedTables = new HashSet<>();
		for (Table leftTable : left.getTables()) {
			MatchedTableItme tableItme = null;
			for (Table rightTable : right.getTables()) {
				if (StringUtils.equalsIgnoreCase(leftTable.getName(), rightTable.getName())) {
					tableItme = new MatchedTableItme();
					tableItme.setLeft(leftTable);
					tableItme.setRight(rightTable);
					rightMatchedTables.add(rightTable);
					break;
				}
			}
			if (tableItme == null) {// 未匹配上
				tableItme = new MatchedTableItme();
				tableItme.setLeft(leftTable);
			}

			tableItmes.add(tableItme);
			matcheTable(tableItme);// 匹配表格
		}
		for (Table rightTable : right.getTables()) {
			if (!rightMatchedTables.contains(rightTable)) {
				MatchedTableItme tableItme = new MatchedTableItme();
				tableItme.setRight(rightTable);
				tableItmes.add(tableItme);
			}
		}
		context.setTables(tableItmes);

		// 匹配 view
		List<MatchedViewItme> viewItmes = new ArrayList<>();
		Set<View> rigthMatchedViews = new HashSet<>();
		for (View leftView : left.getViews()) {
			MatchedViewItme viewItme = null;
			for (View rigthView : right.getViews()) {
				if (StringUtils.equalsIgnoreCase(leftView.getName(), rigthView.getName())) {
					viewItme = new MatchedViewItme();
					viewItme.setLeft(leftView);
					viewItme.setRight(rigthView);
					rigthMatchedViews.add(rigthView);
					break;
				}
			}
			if (viewItme == null) {
				viewItme = new MatchedViewItme();
				viewItme.setLeft(leftView);
			}
		}

		for (View rigthView : right.getViews()) {
			if (!rightMatchedTables.contains(rigthView)) {
				MatchedViewItme viewItme = new MatchedViewItme();
				viewItme.setRight(rigthView);
				viewItmes.add(viewItme);
			}
		}
		context.setViews(viewItmes);
		return context;
	}

	/***
	 * 匹配表格内的列和索引
	 * 
	 * @param tableItme
	 */
	private void matcheTable(MatchedTableItme tableItme) {
		if (tableItme.getLeft() == null || tableItme.getRight() == null) {
			return;
		}

		// 匹配列
		List<MatchedColumnItme> columnItmes = matcheColumns(tableItme.getLeft().getColumns(),
				tableItme.getRight().getColumns(), false);
		tableItme.setColumns(columnItmes);

		// 匹配索引
		List<MatchedIndexItme> indexItmes = matcheIndexs(tableItme.getLeft().getIndexs(),
				tableItme.getRight().getIndexs());
		tableItme.setIndexs(indexItmes);
	}

	private List<MatchedIndexItme> matcheIndexs(List<Index> leftIndexs, List<Index> rigthIndexs) {
		List<MatchedIndexItme> indexItmes = new ArrayList<>();
		if (leftIndexs == null) {
			leftIndexs = new ArrayList<>();
		}
		if (rigthIndexs == null) {
			rigthIndexs = new ArrayList<>();
		}

		List<Index> matchedRigthIndex = new ArrayList<>();
		for (Index leftIndex : leftIndexs) {
			MatchedIndexItme indexItme = null;
			for (Index rigthIndex : rigthIndexs) {
				if (StringUtils.equals(leftIndex.getName(), rigthIndex.getName())) {
					indexItme = new MatchedIndexItme();
					indexItme.setLeft(leftIndex);
					indexItme.setRight(rigthIndex);
					matchedRigthIndex.add(rigthIndex);
				}
			}
			if (indexItme == null) {
				indexItme = new MatchedIndexItme();
				indexItme.setLeft(leftIndex);
			}
			indexItmes.add(indexItme);
		}
		for (Index rigthIndex : rigthIndexs) {
			if (!matchedRigthIndex.contains(rigthIndex)) {
				MatchedIndexItme indexItme = new MatchedIndexItme();
				indexItme.setRight(rigthIndex);
				indexItmes.add(indexItme);
			}
		}

		return indexItmes;
	}

	private List<MatchedColumnItme> matcheColumns(List<Column> leftColumns, List<Column> rightColumns,
			boolean sequence) {
		if (!sequence) {
			return matcheColumnsIgnoreSequence(leftColumns, rightColumns);
		}
		return matcheColumnsRequireSequence(leftColumns, rightColumns);
	}

	/***
	 * 匹配列 关心列顺序
	 * 
	 * @param leftColumns
	 * @param rightColumns
	 * @return
	 */
	private List<MatchedColumnItme> matcheColumnsRequireSequence(List<Column> leftColumns, List<Column> rightColumns) {
		throw new RuntimeException("此方法未实现");
	}

	/***
	 * 匹配列忽略顺序
	 * 
	 * @param leftColumns
	 * @param rightColumns
	 * @return
	 */
	private List<MatchedColumnItme> matcheColumnsIgnoreSequence(List<Column> leftColumns, List<Column> rightColumns) {
		List<MatchedColumnItme> indexItmes = new ArrayList<>();
		if (leftColumns == null) {
			leftColumns = new ArrayList<>();
		}
		if (rightColumns == null) {
			rightColumns = new ArrayList<>();
		}
		List<Column> matchedRigthColumn = new ArrayList<>();
		for (Column leftColumn : leftColumns) {
			MatchedColumnItme columnItme = null;
			for (Column rigthColum : rightColumns) {
				if (StringUtils.equals(leftColumn.getName(), rigthColum.getName())) {
					columnItme = new MatchedColumnItme();
					columnItme.setLeft(leftColumn);
					columnItme.setRight(rigthColum);
					matchedRigthColumn.add(rigthColum);
				}
			}
			if (columnItme == null) {
				columnItme = new MatchedColumnItme();
				columnItme.setLeft(leftColumn);
			}
			indexItmes.add(columnItme);
		}
		for (Column rigthColumn : rightColumns) {
			if (!matchedRigthColumn.contains(rigthColumn)) {
				MatchedColumnItme indexItme = new MatchedColumnItme();
				indexItme.setRight(rigthColumn);
				indexItmes.add(indexItme);
			}
		}
		return indexItmes;
	}
}

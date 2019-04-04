package cn.yayatao.db.moonlight.chariot.context;

import java.util.List;

import cn.yayatao.db.moonlight.chariot.model.Table;

public class MatchedTableItme extends AbstractMatchedItme {
	/**
	 * 左边表
	 */
	Table left;

	/**
	 * 右边表
	 */
	Table right;

	List<MatchedColumnItme> columns;

	/***
	 * 索引
	 */
	List<MatchedIndexItme> indexs;

	private CompareResult result;

	/**
	 * @return the result
	 */
	public CompareResult getResult() {
		return result;
	}

	/**
	 * @return the columns
	 */
	public List<MatchedColumnItme> getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<MatchedColumnItme> columns) {
		this.columns = columns;
	}

	/**
	 * @return the indexs
	 */
	public List<MatchedIndexItme> getIndexs() {
		return indexs;
	}

	/**
	 * @param indexs
	 *            the indexs to set
	 */
	public void setIndexs(List<MatchedIndexItme> indexs) {
		this.indexs = indexs;
	}

	/**
	 * @return the left
	 */
	public Table getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(Table left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Table getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(Table right) {
		this.right = right;
	}

	@Override
	public CompareResult compare() {
		if (left == null) {
			result = CompareResult.LEFT_NOT_EXIST;
			return result;
		}
		if (right == null) {
			result = CompareResult.RIGHT_NOT_EXIST;
			return result;
		}
		result = CompareResult.EQUAL;
		
		for (MatchedColumnItme  columnItme: columns){
			if(CompareResult.EQUAL != columnItme.compare()){
				result = CompareResult.NOT_EQUAL;
			}
		}
		
		for (MatchedIndexItme indexItme : indexs) {
			if (CompareResult.EQUAL != indexItme.compare()) {
				result = CompareResult.NOT_EQUAL;
			}
		}
		return result;
	}

}

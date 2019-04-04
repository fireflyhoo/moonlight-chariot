package cn.yayatao.db.moonlight.chariot.context;

import org.apache.commons.lang3.StringUtils;

import cn.yayatao.db.moonlight.chariot.model.View;

public class MatchedViewItme extends AbstractMatchedItme {


	/**
	 * 左边表
	 */
	View left;

	/**
	 * 右边表
	 */
	View right;

	private CompareResult result;

	/**
	 * @return the left
	 */
	public View getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(View left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public View getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(View right) {
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
		result = CompareResult.NOT_EQUAL;
		if (StringUtils.equals(left.getSql(), right.getSql())) {
			result = CompareResult.EQUAL;
		}
		return result;
	}
	
	/**
	 * @return the result
	 */
	public CompareResult getResult() {
		return result;
	}


}

package cn.yayatao.db.moonlight.chariot.context;

import org.apache.commons.lang3.StringUtils;

import cn.yayatao.db.moonlight.chariot.model.Index;

public class MatchedIndexItme extends AbstractMatchedItme {
	/**
	 * 左边
	 */
	Index left;

	/**
	 * 右边
	 */
	Index right;

	private CompareResult result;

	/**
	 * @return the left
	 */
	public Index getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(Index left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Index getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(Index right) {
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

		if (StringUtils.equals(left.getIndexType(), right.getIndexType())
				&& StringUtils.equals(left.getIndexMethod(), left.getIndexMethod())
				&& left.getColumns().equals(right.getColumns())) {
			result = CompareResult.EQUAL;
		}
		return result;
	}

}

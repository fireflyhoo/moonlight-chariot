package cn.yayatao.db.moonlight.chariot.context;

import org.apache.commons.lang3.StringUtils;

import cn.yayatao.db.moonlight.chariot.model.Column;

/**
 * 匹配好的列
 * 
 * @author Huyahui
 *
 */
public class MatchedColumnItme extends AbstractMatchedItme {
	private Column left;

	private Column right;

	private CompareResult result;

	/**
	 * @return the result
	 */
	public CompareResult getResult() {
		return result;
	}

	/**
	 * @return the left
	 */
	public Column getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(Column left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Column getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(Column right) {
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
		if (StringUtils.equals(left.getType(), right.getType())
				&& StringUtils.equals(left.getComment(), right.getComment())
				&& StringUtils.equals(left.getDefaultValue(), right.getDefaultValue())
				&& equals(left.getLength() ,right.getLength()) && left.getScale() == right.getScale()) {
			result = CompareResult.EQUAL;
		}else{
			System.out.println(left);
			System.out.println(right); 
		}
		return result;
	}

	private boolean equals(String left, String right) {
		if(left == null && left == null){
			return true;
		}
		
		return left!=null && left.equals(right);
	}

}

package org.xiangbalao.textpage;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.text.Selection;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.EditText;

public class TextPage extends EditText {

	private OnTextChangedListener mListener;

	private static final String TAG = "TextPage";

	public void setOnTextChangedListener(OnTextChangedListener mListener) {
		this.mListener = mListener;
	}

	public TextPage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public TextPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private int off; // 字符串的偏移值

	public TextPage(Context context) {
		super(context);
		initialize();
	}

	private void initialize() {
		setGravity(Gravity.TOP);
		setBackgroundColor(Color.WHITE);
	}

	@Override
	protected void onCreateContextMenu(ContextMenu menu) {
		// 不做任何处理，为了阻止长按的时候弹出上下文菜单
	}

	@Override
	public boolean getDefaultEditable() {
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		Layout layout = getLayout();
		int line = 0;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			line = layout.getLineForVertical(getScrollY() + (int) event.getY());
			off = layout.getOffsetForHorizontal(line, (int) event.getX());
			Selection.setSelection(getEditableText(), off);
			break;
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_UP:
			line = layout.getLineForVertical(getScrollY() + (int) event.getY());
			int curOff = layout
					.getOffsetForHorizontal(line, (int) event.getX());
			Selection.setSelection(getEditableText(), off, curOff);

			if (off < curOff) {

				mListener.onTextChanged(getEditableText().toString().substring(
						off, curOff));
				Log.i(TAG, getEditableText().toString().substring(off, curOff));
			} else {
				mListener.onTextChanged(getEditableText().toString().substring(
						curOff, off));
				Log.i(TAG, getEditableText().toString().substring(curOff, off));
			}

			break;
		}
		return true;
	}

}

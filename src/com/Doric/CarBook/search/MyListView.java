package com.Doric.CarBook.search;
import android.widget.ListView;

/**
 * ScrollView��Ƕ��ListView,��ListViewȫ��ʾ����
 * @author reyo
 *
 */
public class MyListView extends ListView{

	public MyListView(android.content.Context context){
		super(context);
	}

	/**
	 * ���ò�����
	 */
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}
	
}

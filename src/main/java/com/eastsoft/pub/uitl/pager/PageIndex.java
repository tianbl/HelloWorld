package com.eastsoft.pub.uitl.pager;

/**
 * ҳ��������Ҫ����ʼҳ����ֹҳ
 *
 * @author <a href="http://www.blogjava.net/lishunli/" target="_blank">ShunLi</a>
 * @notes Created on 2010-1-24<br>
 *        Revision of last commit:$Revision: 623 $<br>
 *        Author of last commit:$Author: nhjsjmz@gmail.com $<br>
 *        Date of last commit:$Date: 2010-01-25 12:41:45 +0800 (��һ, 25 һ�� 2010) $<br>
 *        <p>
 */
public class PageIndex
{
	/** ��ʼ���� **/
	private int startIndex;
	/** �������� **/
	private int endIndex;

	public PageIndex(int startIndex, int endIndex)
	{
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public int getStartIndex()
	{
		return startIndex;
	}

	public void setStartIndex(int startIndex)
	{
		this.startIndex = startIndex;
	}

	public int getEndIndex()
	{
		return endIndex;
	}

	public void setEndIndex(int endIndex)
	{
		this.endIndex = endIndex;
	}

	/**
	 * ��ʼҳ����ֹҳ
	 *
	 * @param viewPageCount
	 *            ��ʾ����ҳ
	 * @param currentPage
	 *            ��ǰҳ
	 * @param totalpage
	 *            ��ҳ��
	 * @return PageIndex ��ʼҳ����ֹҳ
	 */
	public static PageIndex getPageIndex(int viewPageCount, int currentPage, int totalpage)
	{
		int startpage = currentPage - (viewPageCount % 2 == 0 ? viewPageCount / 2 - 1 : viewPageCount / 2);
		int endpage = currentPage + viewPageCount / 2;
		if (startpage < 1)
		{
			startpage = 1;
			if (totalpage >= viewPageCount)
				endpage = viewPageCount;
			else
				endpage = totalpage;
		}
		if (endpage > totalpage)
		{
			endpage = totalpage;
			if ((endpage - viewPageCount) > 0)
				startpage = endpage - viewPageCount + 1;
			else
				startpage = 1;
		}
		return new PageIndex(startpage, endpage);
	}
}

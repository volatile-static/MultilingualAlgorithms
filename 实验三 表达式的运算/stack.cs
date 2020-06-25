using System;
using System.Collections.Generic;
using System.Windows.Forms.VisualStyles;

public class TStack<T>
{
	private List<T> data = new List<T>();

	public TStack()
	{
	}

	public void Push(T val)
    {
		data.Add(val);
    }

	public void Pop()
	{
		data.RemoveAt(data.Count - 1);
	}

	public T Top()
	{
		return data[data.Count - 1];
	}

	public int Size()
    {
		return data.Count;
    }
}

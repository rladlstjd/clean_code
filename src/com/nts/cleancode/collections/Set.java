package com.nts.cleancode.collections;

public class Set extends AbstractCollection {
	private static int INITIAL_CAPACITY = 10;
	private Object[] elements = new Object[INITIAL_CAPACITY];
	private int size = 0;
	private boolean readOnly;

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(Object element) {
		if (readOnly)
			return;

		if (shouldGrow()) {
			grow();
		}

		if (contains(element))
			return;
		addElement(element);
	}

	protected void addElement(Object element) {
		elements[size++] = element;
	}
	

	protected boolean shouldGrow() {
		return size + 1 > elements.length;
	}

	protected void grow() {
		Object[] newElements =
			new Object[elements.length + INITIAL_CAPACITY];
		for (int i = 0; i < size; i++)
			newElements[i] = elements[i];
		elements = newElements;
	}

	public boolean contains(Object element) {
		for (int i = 0; i < size; i++)
			if (elements[i].equals(element))
				return true;
		return false;
	}

	public int size() {
		return size;
	}

	public boolean remove(Object element) {
		if (readOnly)
			return false;
		for (int i = 0; i < size; i++)
			if (elements[i].equals(element)) {
				elements[i] = null;
				Object[] newElements = new Object[size - 1];
				int k = 0;
				for (int j = 0; j < size; j++) {
					if (elements[j] != null)
						newElements[k++] = elements[j];
				}
				size--;
				elements = newElements;
				return true;
			}
		return false;
	}

	public Object get(int index) {
		return elements[index];
	}

	public void addAll(List l) {
		for (int i = 0; i < l.size(); i++) {
			if (!contains(l.get(i)))
				elements[size++] = l.get(i);
		}
	}

	public int capacity() {
		return elements.length;
	}

	public void setReadOnly(boolean b) {
		readOnly = b;
	}
}

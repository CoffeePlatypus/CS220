package hw5;

import java.util.Iterator;

public interface Digits extends Comparable<Digits> {
	public Digits add(Digits other) throws IllegalArgumentException;
    public Digits mul(Digits other) throws IllegalArgumentException;
    public Digits pow(Digits other) throws IllegalArgumentException, UndefinedValueException;
	public Iterator<Integer> getDigits( boolean leastFirst );
	public String toString();
	public boolean equals(Object other);
	public boolean isZero();
	public boolean isOne();
	public Digits half();
	public boolean isEven();
	public int numberOfDigits();
}
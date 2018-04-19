package hw5;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.lang.model.element.UnknownAnnotationValueException;

public class Prestidigitation implements Digits {
	private ArrayList<Integer> digits = new ArrayList<Integer>(); // NO OTHER VARIABLES

 //constructors
	public Prestidigitation (int num) throws IllegalArgumentException {
		if(num>=0){
			if(num==0) {
				digits.add(0);
			}else{
				while(num!=0) {
					digits.add(num%10);
					num=num/10;
				}
			}
		}else{
			throw new IllegalArgumentException();
		}
	}
 
	public Prestidigitation (String num) throws NumberFormatException {
		//I feel like there is a built in way to do this but I don't remember it;
		for(int i=0; i<num.length(); i++) {
			switch(num.charAt(i)) {
			case '0': digits.add(0); break;
			case '1': digits.add(1); break;
			case '2': digits.add(2); break;
			case '3': digits.add(3); break;
			case '4': digits.add(4); break;
			case '5': digits.add(5); break;
			case '6': digits.add(0); break;
			case '7': digits.add(1); break;
			case '8': digits.add(2); break;
			case '9': digits.add(3); break;
			default: throw new NumberFormatException();
			}
		}
	}
 
	public Prestidigitation (long num) throws IllegalArgumentException {
		if(num<0){
			throw new IllegalArgumentException();
		}else if(num==0) {
			digits.add(0);
		}else{
			while(num!=0) {
				digits.add((int)num%10);
				num=num/10;
			}
		}
	}
 
	public Prestidigitation (Prestidigitation num) throws IllegalArgumentException {
		digits=num.digits;
	}
	
	private Prestidigitation (ArrayList<Integer> num) throws IllegalArgumentException {
		digits=num;
	}
	
	public ArrayList<Integer> getTheDigits() {
		return digits;
	}
 
 //Overriding methods
	public int compareTo(Digits other) {
		if(numberOfDigits()==other.numberOfDigits()) {
			if(equals(other)) {
				return 0;
			}
			Iterator<Integer> dIterator = getDigits(true);
			Iterator<Integer> oIterator = other.getDigits(true);
			while(dIterator.hasNext()) {
				int dNext=dIterator.next(), oNext=oIterator.next();
				if(dNext<oNext) {
					return 1;
				}else if(dNext>oNext) {
					return-1;
				}
			}
			return 0;
		}else{
			if(numberOfDigits()<other.numberOfDigits()) {
				return 1;
			}else{
				return -1;
			}
		}
	}
	
	public Digits add(Digits other) throws IllegalArgumentException {
		if(other instanceof Prestidigitation) {
			int carry=0;
			ArrayList<Integer> sum = new ArrayList<Integer>();
			Iterator<Integer> dIterator = getDigits(true);
			Iterator<Integer> oIterator = other.getDigits(true);
			while(dIterator.hasNext() && oIterator.hasNext()) {
				int sumnums=(dIterator.next()+oIterator.next()+carry);
				sum.add((sumnums)%10);
				carry=sumnums/10;
			}
			while(dIterator.hasNext()) {
				int sumnums=dIterator.next()+carry;
				sum.add(sumnums%10);
				carry=sumnums/10;
			}
			while(oIterator.hasNext()) {
				int sumnums=oIterator.next()+carry;
				sum.add(sumnums%10);
				carry=sumnums/10;
			}
			if(carry!=0) {
				sum.add(carry);
			}
			Digits result=new Prestidigitation(sum);
			return result;
		}else{
			throw new IllegalArgumentException();
		}
	}			

	public Digits mul(Digits other) throws IllegalArgumentException {
		if(other instanceof Prestidigitation) {
			int carry=0, i=0;
			ArrayList<ArrayList<Integer>> product =new ArrayList<ArrayList<Integer>>();
			//Iterator<Integer> dIterator = getDigits(true);
			Iterator<Integer> oIterator = other.getDigits(true);
			while(oIterator.hasNext()) {
				int zeros=i,next=oIterator.next();
				ArrayList<Integer> line = new ArrayList<Integer>();
				while(zeros>0) {
					line.add(0);
					zeros--;
				}
				i++;
				Iterator<Integer> dIterator = getDigits(true);
				while(dIterator.hasNext()) {
					int mulnums=(dIterator.next()*next)+carry;
					line.add((mulnums)%10);
					carry=mulnums/10;
				}
				if(carry!=0) {
					line.add(carry);
				}
				product.add(line);
			}
			Digits sum = new Prestidigitation(product.get(0));
			for(int k=1; k<product.size(); k++) {
				Digits atK= new Prestidigitation(product.get(k));
				sum=new Prestidigitation((Prestidigitation)(sum.add(atK)));
			}
			Iterator<Integer> sumIterator = sum.getDigits(true);// this is some iterator;
			while(sumIterator.hasNext()) {
				if(sumIterator.next()!=0) {
					Digits result=sum;
					return result;
				}else{
					Digits result= new Prestidigitation(0);
					return result;
				}
			}
			Digits result=sum;
			return result;
		}else{
			throw new IllegalArgumentException();
		}
	}
	
	public Digits pow(Digits other) throws IllegalArgumentException, UndefinedValueException {
		if(other instanceof Prestidigitation) {
			if(isZero() && other.isZero()) {
				throw new UndefinedValueException();
			}else if(!isZero() && other.isZero()) {
				Digits result=new Prestidigitation(1);
				return result;
			}else if(other.isOne()) {
				Digits result= new Prestidigitation(digits);
				return result;
			}else{
				//ArrayList<Integer> half=new ArrayList<Integer>();
				Digits half= other.half();
				Digits y= pow(half);
				Prestidigitation z;
				if(other.isEven()) {
					z=new Prestidigitation(1);
				}else{
					z=new Prestidigitation(digits);
				}
				Digits result= y.mul(y).mul(z);
				return result;
			}
		}else{
			throw new IllegalArgumentException();
		}
	}
	
	private class PrestidigIterator implements Iterator<Integer> {
		int nextIndex;
		boolean leastFirst;
		
		public PrestidigIterator(boolean lf) {
			leastFirst=lf;
			if(leastFirst) {
				nextIndex=0;
			}else{
				nextIndex=numberOfDigits()-1;
			}
		}

		public boolean hasNext() {
			if(leastFirst) {
				return nextIndex<numberOfDigits();
			}else{
				return nextIndex>=0;
			}
		}

		public Integer next() {
			if(leastFirst) {
				return digits.get(nextIndex++);
			}else{
				return digits.get(nextIndex--);
			}
		}

		public void remove() {
			if(leastFirst) {
				nextIndex--;
			}else{
				nextIndex++;
			}

		}
	}
	public Iterator<Integer> getDigits(boolean leastFirst) {
		return new PrestidigIterator(leastFirst);
	}

	public boolean isZero() {
		return digits.size()==1 && digits.get(0)==0 ;
	}

	public boolean isOne() {
		return digits.size()==1 && digits.get(0)==1;
	}

	public Digits half() {
		digits.add(0,0);
		ArrayList<Integer> half=new ArrayList<Integer>();
		for(int i=0, j=1; i<digits.size() && j< digits.size();i++, j++) {
			if((digits.get(i)%2)==0) {
				switch (digits.get(j)) {
					case 0: case 1: half.add(0); break;
					case 2: case 3: half.add(1); break;
					case 4: case 5: half.add(2); break;
					case 6: case 7: half.add(3); break;
					case 8: case 9: half.add(4); break;
				}				
			}else{
				switch (digits.get(j)) {
				case 0: case 1: half.add(5); break;
				case 2: case 3: half.add(6); break;
				case 4: case 5: half.add(7); break;
				case 6: case 7: half.add(8); break;
				case 8: case 9: half.add(9); break;
				}
			}	
		}
		digits.remove(0);
		if(half.get(0)==0) {
			half.remove(0);
		}
		Digits result=new Prestidigitation(half);
		return result;
	}

	public boolean isEven() {
		return digits.get(0)%2==0;
	}

	public int numberOfDigits() {
		return digits.size();
	}
	public boolean equals(Object other){
		if(other instanceof Prestidigitation) {
			if(numberOfDigits()==((Prestidigitation) other).numberOfDigits()) {
				Iterator<Integer> dIterator = getDigits(true);
				Iterator<Integer> oIterator = ((Prestidigitation) other).getDigits(true);
				while(dIterator.hasNext() && oIterator.hasNext()) {
					if(dIterator.next()!= oIterator.next()) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	public String toSting() {
		Iterator<Integer> dIterator = getDigits(false);
		String number="";
		while(dIterator.hasNext()) {
			number=number+dIterator.next();
		}
		return number;
	}
}

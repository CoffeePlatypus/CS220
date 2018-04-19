import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tester {
    public static String[] badWords = { "fat", "ugly", "nerd" };
    public static String[] strings = {"54601", "55555", "aardvark", "fat", "nerd", "Zimbabwe", "La Crosse", "Computer Science", "CS 220" };
    public static Integer[] odds = {1,3,5,7,9,11,13};
    public static Integer[] evens = {2,4,6,8,10,12,14,16};
    public static Double[] powersOf2 = {1.0, 2.0, 4.0, 8.0, 16.0, 32.0, 64.0};
    public static Color[] colors = { Color.red, Color.yellow, Color.white, Color.black };
    
    public static <T> List<T> getAccepted(List<T> items, Filter<T> filter) {
        List<T> result = new LinkedList<>();
        for(T t : items) {
            if(filter.accepts(t)) result.add(t);
        }
        return result;
    }
    
    public static <T> void test(List<T> input, Filter<T> filter, T[] expected, String test ) {
        List<T> output = getAccepted( input, filter );
        if( output.size() != expected.length ) {
            System.out.println("Failure: different numbers of outputs. " + test);
            System.out.println(input);
            System.out.println(output);
            System.out.println(Arrays.asList(expected));
            return;
        }
        
        for( int i = 0 ; i < output.size(); i ++ ) {
            T element = output.get( i );
            if( !element.equals( expected[ i ] ) )  {
                System.out.println("failure on test " + test );
                System.out.println(output);
                System.out.println(Arrays.asList(expected));
                return;
            }
        }
    }
    
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList( strings );
        List<Integer> list2 = Arrays.asList( odds );
        List<Integer> list3 = Arrays.asList( evens );
        List<Double> list4 = Arrays.asList( powersOf2 );
        List<Color> list5 = Arrays.asList( colors );
        
        
        Filter<Color> f1 = new IsReddish();
        Filter<Color> f2 = new IsBright();
        Filter<String> f3 = new StartsWith<String>("5");
        Filter<Integer> f4 = new StartsWith<Integer>("5");
        Filter<Color> f5 = new StartsWith<Color>("rgb");
        Filter<String> f6 = new Contains<String>("0");
        Filter<Color> f7 = new Contains<Color>("0");
        Filter<String> f8 = new LessThan<String>("March madness");
        Filter<String> f9 = new AsLongAs<String>(5);
        Filter<Color> f10 = new AsLongAs<Color>(33);
        Filter<String> f11 = new IntegerNumber<String>();
        Filter<Integer> f12 = new IntegerNumber<Integer>();
        Filter<String> f13 = new Censor<String>( badWords );
        Filter<String> f14 = new ContainsAll<String>( "e ");
        Filter<String> f15 = new Not<String>(f14);
        Filter<String> f16 = new Or<String>( f3, f8 );
        Filter<String> f17 = new And<String>( f3, f6 );
        Filter<String> f18 = new Xor<String>( f3, f6 );
        Filter<Double> f19 = new And<Double>( new LessThan<Double>("8"), new Not<Double>( new IntegerNumber<Double>() ) );
        
        
        test( list5, f1, new Color[]{Color.red}, "test1");
        test( list5, f2, new Color[]{ Color.yellow, Color.white }, "test2");
        test( list1, f3, new String[]{ "54601", "55555" }, "test3" );
        test( list2, f4, new Integer[]{5}, "test4" );
        test( list5, f5, new Color[]{}, "test5" );
        test( list1, f6, new String[]{"54601", "CS 220"}, "test6" );
        test( list5, f7, new Color[]{Color.red, Color.yellow, Color.black}, "test7" );
        test( list1, f8, new String[]{"54601", "55555", "aardvark", "fat", "La Crosse", "Computer Science", "CS 220"}, "test8");
        test( list1, f9, new String[]{"54601", "55555", "aardvark", "Zimbabwe", "La Crosse", "Computer Science", "CS 220"}, "test9" );
        test( list5, f10, new Color[]{Color.white}, "test10");
        test( list1, f11, new String[]{"54601", "55555"}, "test11" );
        test( list3, f12, new Integer[]{2,4,6,8,10,12,14,16}, "test12" );
        test( list1, f13, new String[]{"54601", "55555", "aardvark", "Zimbabwe", "La Crosse", "Computer Science", "CS 220"}, "test13" );
        test( list1, f14, new String[]{"La Crosse", "Computer Science"}, "test14");
        test( list1, f15, new String[]{"54601", "55555", "aardvark", "fat", "nerd", "Zimbabwe", "CS 220"}, "test15" );
        test( list1, f16, new String[]{ "54601", "55555", "aardvark", "fat", "La Crosse", "Computer Science", "CS 220"}, "test16" );
        test( list1, f17, new String[]{ "54601" }, "test17" );
        test( list1, f18, new String[]{ "55555", "CS 220"}, "test18");
        test( list4, f19, new Double[]{1.0, 2.0, 4.0, 16.0, 32.0, 64.0}, "test19");
    }
}

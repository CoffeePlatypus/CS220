import java.awt.Color;

public class IsReddish implements Filter<Color> {
	
	public boolean accepts(Color e) {
		return (e.getRed()>e.getBlue()&& e.getRed()>e.getGreen());
	}
}

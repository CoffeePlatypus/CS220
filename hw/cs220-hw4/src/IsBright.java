import java.awt.Color;

public class IsBright implements Filter<Color> {

	@Override
	public boolean accepts(Color e) {
		return (e.getBlue()+e.getGreen()+e.getRed())>=256;
	}

}

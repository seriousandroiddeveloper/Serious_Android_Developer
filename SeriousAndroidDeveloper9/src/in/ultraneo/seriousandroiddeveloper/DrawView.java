package in.ultraneo.seriousandroiddeveloper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
	Paint paint = new Paint();
	Context c;
	final float xPos = 80, yPos = 80, radius = 40;

	public DrawView(Context context) {
	super(context);
	c = context;
	paint.setColor(Color.WHITE);
	invalidate();
	}

	public View getAntiAlias() {

	paint.setAntiAlias(true);
	return this;
	}

	public View getShadowEffect() {
	paint.setAntiAlias(true);
	paint.setShadowLayer(10, 10, 5, Color.RED);
	return this;
	}
}

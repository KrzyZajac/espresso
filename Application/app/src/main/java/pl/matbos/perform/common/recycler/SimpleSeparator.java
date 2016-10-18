package pl.matbos.perform.common.recycler;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SimpleSeparator extends RecyclerView.ItemDecoration {

    private final int height;
    private final Paint paint;

    public SimpleSeparator(int color, int height) {
        paint = new Paint();
        paint.setColor(color);
        this.height = height;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        outRect.top = outRect.top + height;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();
        View child;
        int dividerTop;
        int dividerBottom;
        RecyclerView.LayoutParams params;
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount - 1; i++) {
            child = parent.getChildAt(i);

            params = (RecyclerView.LayoutParams) child.getLayoutParams();

            dividerTop = child.getBottom() + params.bottomMargin;
            dividerBottom = dividerTop + height;

            c.drawRect(dividerLeft + height, dividerTop, dividerRight - height, dividerBottom, paint);
        }
    }
}

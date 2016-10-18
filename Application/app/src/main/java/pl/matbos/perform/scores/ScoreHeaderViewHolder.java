package pl.matbos.perform.scores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.joda.time.DateTime;

import pl.matbos.perform.util.DateUtils;

public class ScoreHeaderViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public ScoreHeaderViewHolder(TextView itemView) {
        super(itemView);
        textView = itemView;
    }

    public void bind(@NonNull DateTime dateTime) {
        //noinspection ConstantConditions
        if (dateTime == null) {
            textView.setText("");
        }
        textView.setText(DateUtils.DISPLAY_SHORT_DATE_FORMATTER.print(dateTime));
    }
}

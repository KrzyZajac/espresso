package pl.matbos.perform.common.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.matbos.perform.R;

public class StateNotificationLayout extends FrameLayout {
    @BindView(R.id.notification_text)
    protected TextView notification;
    @BindView(R.id.notification_progressbar)
    protected ProgressBar progressBar;
    @BindDrawable(R.drawable.candy_cane)
    protected Drawable defaultErrorDrawable;
    @BindDimen(R.dimen.notification_icon_size)
    protected int notificationIconSize;


    public StateNotificationLayout(Context context) {
        this(context, null);
    }

    public StateNotificationLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateNotificationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StateNotificationLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.state_notification_layout, this, true);
        ButterKnife.bind(this, this);
        defaultErrorDrawable.setBounds(0, 0, notificationIconSize, notificationIconSize);
    }

    public void showProgress() {
        progressBar.setVisibility(VISIBLE);
        hideError();
    }

    public void hideProgress() {
        progressBar.setVisibility(GONE);
    }

    public void hideError() {
        notification.setVisibility(GONE);
    }

    public void showError(String message) {
        showError(message, 0);
    }

    public void showError(String message, @DrawableRes int drawable) {
        resetNotification();
        hideProgress();
        if (drawable != 0) {
            notification.setCompoundDrawables(null, getDrawable(drawable), null, null);
        }
        notification.setText(message);
        notification.setVisibility(VISIBLE);
    }

    public void hideAllNotifications() {
        hideProgress();
        hideError();
    }

    private void resetNotification() {
        notification.setCompoundDrawables(null, defaultErrorDrawable, null, null);
        notification.setText(R.string.no_internet_message);
    }

    private Drawable getDrawable(@DrawableRes int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        if (drawable != null) {
            drawable.setBounds(0, 0, notificationIconSize, notificationIconSize);
        }
        return drawable;
    }
}
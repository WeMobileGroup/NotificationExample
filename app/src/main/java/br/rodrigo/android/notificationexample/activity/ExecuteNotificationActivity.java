package br.rodrigo.android.notificationexample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import br.rodrigo.android.notificationexample.NotificationHelper;

/**
 * ExecuteNotificationActivity class.
 * 
 * @author Rodrigo Cericatto
 * @since Nov 17, 2013
 */
public class ExecuteNotificationActivity extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Cancels the notification.
		NotificationHelper.cancelNotification(this, NotificationHelper.NOTIFICATION_ID);

		// Sets the layout.
		TextView text = new TextView(this);
		text.setText("User selected a notification.");
		setContentView(text);
	}
}
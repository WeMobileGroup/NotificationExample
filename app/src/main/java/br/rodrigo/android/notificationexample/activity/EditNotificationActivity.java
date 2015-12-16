package br.rodrigo.android.notificationexample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import br.rodrigo.android.notificationexample.NotificationHelper;
import br.rodrigo.android.notificationexample.R;

/**
 * EditNotificationActivity class.
 * 
 * @author Rodrigo Cericatto
 * @since Dec 15, 2013
 */
public class EditNotificationActivity extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Cancels the notification.
		NotificationHelper.cancelNotification(this, R.drawable.ic_launcher);

		// Sets the layout.
		TextView text = new TextView(this);
		text.setText("User selected a notification, clicking into \"Edit\" button.");
		setContentView(text);
	}
}
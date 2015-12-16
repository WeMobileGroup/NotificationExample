package br.rodrigo.android.notificationexample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import br.rodrigo.android.notificationexample.NotificationHelper;
import br.rodrigo.android.notificationexample.R;

/**
 * MainActivity class.
 * 
 * @author Rodrigo Cericatto
 * @since Nov 17, 2013
 */
public class MainActivity extends Activity {

	//--------------------------------------------------
	// Activity Life Cycle
	//--------------------------------------------------
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		TextView text = new TextView(this);
		text.setText("A notification was fired.");
		setContentView(text);

		// Informations.
		String tickerText = "Received message";
		String titulo = "Title";
		String mensagem = "Notification example";

		// Shows the notification that will open the ExecuteNotificationActivity.
		createsNotification(tickerText, titulo, mensagem, ExecuteNotificationActivity.class);
	}

	//--------------------------------------------------
	// Methods
	//--------------------------------------------------
	
	/**
	 * Shows the notification.
	 * 
	 * @param tickerText The message shown when the notification is called.
	 * @param title The notification title, shown when the notification is seen into the notification bar.
	 * @param message The notification message, shown when the notification is seen into the notification bar.
	 * @param activity The called activity, when the notification is pressed.
	 */
	protected void createsNotification(String tickerText, String title, String message, Class<?> activity) {
		Intent intent = new Intent(this, ExecuteNotificationActivity.class);
		int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel < 17) {
			NotificationHelper.createSimpleNotification(this, tickerText, title, message,
				R.drawable.ic_launcher, NotificationHelper.NOTIFICATION_ID, intent);
		} else {
			String lines[] = new String [] {
				"Line 1", "Line 2", "Line 3",
				"Line 4", "Line 5", "Line 6",
				"Line 7", "Line 8", "Line 9", 
				"Line 10", "Line 11", "Line 12",
				"Line 13", "Line 14", "Line 15",
				"Line 16", "Line 17", "Line 18",
				"Line 19", "Line 20", "Line 21",
				"Line 22", "Line 23", "Line 24" };
			NotificationHelper.createImprovedNotification(this, tickerText, message,lines,
				R.drawable.ic_launcher, NotificationHelper.NOTIFICATION_ID, intent);
//			NotificationHelper.createImprovedNotificationSimulatingDownload(this,
// 				NotificationHelper.NOTIFICATION_ID, intent);
//			NotificationHelper.createImprovedNotificationWithButtons(this,
// 				NotificationHelper.NOTIFICATION_ID, intent);
		}
	}
}
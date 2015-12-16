package br.rodrigo.android.notificationexample;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import br.rodrigo.android.notificationexample.activity.EditNotificationActivity;
import br.rodrigo.android.notificationexample.activity.ViewNotificationActivity;

/**
 * NotificationHelper class.
 * 
 * @author Rodrigo Cericatto
 * @since Nov 17, 2013
 */
public class NotificationHelper {

	//--------------------------------------------------
	// Constants
	//--------------------------------------------------
	
	public static final int NOTIFICATION_ID = 1001;
	
	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	
	private static CharSequence mBigText =
		"In auctor varius ligula quis imperdiet. " +
		"Cras at arcu eget ligula lacinia aliquam. " +
		"Nunc gravida molestie sodales. " +
		"Nam sit amet lacus a odio dictum dignissim. " +
		"Pellentesque nec tincidunt urna. " +
		"Curabitur nec consequat nisl. " +
		"Proin eu nunc sapien, vitae euismod neque. " +
		"Nullam ultricies mollis tortor vel dictum. " +
		"Integer et nulla et arcu sollicitudin semper.";
	
	//--------------------------------------------------
	// Simple Notification
	//--------------------------------------------------
	
	/**
	 * Creates a simple notification, that works in all
	 * android devices.
	 * 
	 * @param context The activity content.
	 * @param tickerText The message shown when the notification is called.
	 * @param title The notification title, shown when the notification is seen
	 *              into the notification bar.
	 * @param message The notification message, shown when the notification is
	 *                seen into the notification bar.
	 * @param icon The small icon of the notification.
	 * @param id The id of the notification.
	 * @param intent The intent that has the information about which activity
	 *               will be open when the notification is pressed.
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void createSimpleNotification(Context context, String tickerText,
		String title, String message, int icon, int id, Intent intent) {
		
		// PendingIntent to execute the intent when we selects the notification.
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

		Notification notification = null;
		int apiLevel = Build.VERSION.SDK_INT;
		
		// Notification builder.
		Builder builder = new Notification.Builder(context);
		builder.setContentTitle(tickerText);
		builder.setContentText(message);
		builder.setSmallIcon(icon);
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
			R.drawable.ic_launcher));
		builder.setContentIntent(p);

		// Checks API level.
		if (apiLevel >= 17) {
			// Android 4.2 and upper.
			notification = builder.build();
		} else {
			// Android 3.0, 3.1, 3.2, 4.0 or 4.1.
			notification = builder.getNotification();
		}

		// Calls the notification.
		NotificationManager notificationManager = (NotificationManager)
			context.getSystemService(Activity.NOTIFICATION_SERVICE);
		
		// Calls the notification. Waits for 800ms and vibrates for 500ms, and then
		// waits for 300ms and vibrates for 1800ms.
		notification.vibrate = new long[] { 800, 500, 300, 1800 };
		
		// The id (unique number) is a Integer that identifies this notification.
		notificationManager.notify(id, notification);
	}

	//--------------------------------------------------
	// Improved Notification
	//--------------------------------------------------
	
	/**
	 * Creates a improved notification.
	 * 
	 * @param context The activity context.
	 * @param tickerText The message shown when the notification is called.
	 * @param message The notification message, shown when the notification
	 *                is seen into the notification bar.
	 * @param lines The style of the notification.
	 * @param icon The small icon of the notification.
	 * @param id The id of the notification.
	 * @param intent The intent that has the information about which
	 *               activity will be open when the notification is pressed.
	 */
	@SuppressLint("NewApi")
	public static void createImprovedNotification(Context context, String tickerText,
		String message, String[] lines, int icon, int id, Intent intent) {
		
		// PendingIntent to execute the intent when we selects the notification.
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

		// Starts the NotificationBuilder.
		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle(tickerText);
		builder.setContentText(message);
		builder.setSmallIcon(icon);
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
			R.drawable.ic_google));
		builder.setProgress(0, 0, true);
		builder.setContentIntent(pendingIntent);

		// Creates the style.
		Notification.InboxStyle style = new Notification.InboxStyle();
		style.setBigContentTitle(tickerText);
		for (String line : lines) {
			style.addLine(line);
		}
		builder.setStyle(style);

		// Calls the notification.
		Notification notification = builder.build();
		NotificationManager notificationManager = (NotificationManager)
			context.getSystemService(Activity.NOTIFICATION_SERVICE);
		
		// Calls the notification. Waits for 800ms and vibrates for 500ms, and
		// then waits for 300ms and vibrates for 1800ms.
//		notification.vibrate = new long[] { 800, 500, 300, 1800 };
		
		// The id (unique number) is a Integer that identifies this notification.
		notificationManager.notify(id, notification);
	}
	
	/**
	 * Creates a improved notification simulating an image download.
	 * 
	 * @param context The activity context.
	 * @param id The id of the notification.
	 * @param intent The intent that has the information about which activity
	 *               will be open when the notification is pressed.
	 */
	@SuppressLint("NewApi")
	public static void createImprovedNotificationSimulatingDownload(
		final Context context, final int id, Intent intent) {
		// PendingIntent to execute the intent when we selects the notification.
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		
		// Sets the NotificationManager and the NotificationBuilder.
		final NotificationManager notificationManager = (NotificationManager)
			context.getSystemService(Activity.NOTIFICATION_SERVICE);
		final Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle("Image Download").setContentText("Download in progress")
			.setSmallIcon(R.drawable.ic_google);
		builder.setContentIntent(pendingIntent);
		
		// Start a lengthy operation in a background thread.
		new Thread(
		    new Runnable() {
		        @Override
		        public void run() {
		            int increaser;
		            // Do the operation 20 times.
		            for (increaser = 0; increaser <= 100; increaser += 5) {
	                    // Sets the progress indicator to a max value, the current
	                    // completion percentage, and "determinate" state.
	                    builder.setProgress(100, increaser, false);
	                    // Displays the progress bar for the first time.
	                    notificationManager.notify(id, builder.build());
                        // Sleeps the thread, simulating an operation that takes time.
                        try {
                            // Sleep for 1 second.
                            Thread.sleep(1 * 1000);
                        } catch (InterruptedException e) {
                            Toast.makeText(context, "Sleep failure.", Toast.LENGTH_LONG)
								.show();
                        }
		            }
		            // When the loop is finished, updates the notification.
		            builder.setContentText("Download completo")
		            // Removes the progress bar.
		            .setProgress(0, 0, false);
		            
		    		// The id (unique number) is a Integer that identifies this notification.
		            notificationManager.notify(id, builder.build());
		        }
		    }
		// Starts the thread by calling the run() method in its Runnable.
		).start();
	}
	
	/**
	 * Creates a improved notification with buttons.
	 * 
	 * @param context The activity context.
	 * @param id The id of the notification.
	 * @param intent The intent that has the information about which activity
 	 *               will be open when the notification is pressed.
	 */
	@SuppressLint("NewApi")
	public static void createImprovedNotificationWithButtons(Context context,
		int id, Intent intent) {
		// PendingIntent to execute the intent when we selects the notification.
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		PendingIntent pendingIntentView = PendingIntent.getActivity(context, 0,
			new Intent(context, ViewNotificationActivity.class), 0);
		PendingIntent pendingIntentEdit = PendingIntent.getActivity(context, 0,
			new Intent(context, EditNotificationActivity.class), 0);
		
		// Define sound URI, the sound to be played when there's a notification.
		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		// In the addAction method, if you don't want any icon, just set the first param to 0.
		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle("Title of Notification with Buttons");
		builder.setContentText("Message of notification with buttons");
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setSound(soundUri);
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
			R.drawable.ic_google));
		builder.setProgress(0, 0, true);
		builder.setContentIntent(pendingIntent);
		
		// Sets buttons.
		builder.addAction(R.drawable.ic_google, "View", pendingIntentView);
		builder.addAction(0, "Edit", pendingIntentEdit);
		
		// Calls the notification.
		NotificationManager notificationManager = (NotificationManager)context
			.getSystemService(Activity.NOTIFICATION_SERVICE);
		Notification notification = builder.build();
//		Notification notification = builder.setStyle(new Notification.BigTextStyle()
// 			.bigText(mBigText.toString())).build();
//		Notification notification = new Notification.BigPictureStyle(builder)
// 			.bigPicture(BitmapFactory.decodeResource(context.getResources(),
// 			R.drawable.ic_google)).build();
		
		// Calls the notification. Waits for 800ms and vibrates for 150ms,
		// and then waits for 300ms and vibrates for 1000ms.
		notification.vibrate = new long[] { 800, 150, 300, 1000 };
		
		// The id (unique number) is a Integer that identifies this notification.
		notificationManager.notify(id, notification);
	}

	//--------------------------------------------------
	// Common Notification Methods
	//--------------------------------------------------
	
	/**
	 * Cancels a notification.
	 * 
	 * @param context The activity context.
	 * @param id The id of the notification.
	 */
	public static void cancelNotification(Context context, int id) {
		NotificationManager notificationManager = (NotificationManager)
			context.getSystemService(Activity.NOTIFICATION_SERVICE);
		notificationManager.cancel(id);
	}
}
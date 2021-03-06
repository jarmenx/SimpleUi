package commands;

import util.Command;
import android.content.Context;
import android.os.Vibrator;

public class CommandDeviceVibrate implements Command {

	public static long VIBRATE = 30;
	public static long[] DOUBLE_VIBRATE = { 0L, 40L, 60L, 40L };
	public static long[] TRIPPLE_VIBRATE = { 0L, 40L, 60L, 40L, 60L, 40L };

	private long myDuration;
	private final Vibrator vibrator;
	private long[] myPattern;

	/**
	 * @param anyActivity
	 * @param durationInMs
	 *            minimum is around 20 ms
	 */
	public CommandDeviceVibrate(Context anyActivity, long durationInMs) {
		vibrator = (Vibrator) anyActivity
				.getSystemService(Context.VIBRATOR_SERVICE);
		myDuration = durationInMs;
	}

	/**
	 * @param anyActivity
	 * @param pattern
	 *            use something like {0L,100L,250L,1000L,250L,500L}
	 */
	public CommandDeviceVibrate(Context anyActivity, long[] pattern) {
		vibrator = (Vibrator) anyActivity
				.getSystemService(Context.VIBRATOR_SERVICE);
		myPattern = pattern;
	}

	@Override
	public boolean execute() {
		if (vibrator == null) {
			return false;
		}
		if (myDuration > 0) {
			vibrator.vibrate(myDuration);
			return true;
		}
		if (myPattern != null) {
			vibrator.vibrate(myPattern, -1);
			return true;
		}
		return false;
	}

}

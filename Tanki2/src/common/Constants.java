package common;

public final class Constants {
	public static String GameName = "Tanki 2";
	public static int DefaultWindowWidth = 1024;
	public static int DefaultWindowHeight = 768;
	public static String DefaultTankImage = "media/img/tank.png";
	public static String BulletImage = "media/img/bullet.png";
	public static String AimArrowImage = "media/img/arrow.png";
	public static int FPS = 60;
	public static double TimeScale = 0.3;

	public static String getGameName() {
		/* TODO information about game version */
		return GameName;
	}
}

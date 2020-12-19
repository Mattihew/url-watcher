import java.awt.*
import java.awt.image.BufferedImage

fun main() {
    if (SystemTray.isSupported()) {

        val tray = SystemTray.getSystemTray();
        val trayIcon = TrayIcon(getTrayIcon(tray.trayIconSize), "tray demo")

        trayIcon.isImageAutoSize = true;
        trayIcon.toolTip = "tray icon demo"
        trayIcon.addActionListener {
            trayIcon.image = getTrayIcon(tray.trayIconSize, Color.RED)

            //tray.remove(trayIcon);
        }
        tray.add(trayIcon)
        //trayIcon.displayMessage("caption", "text", TrayIcon.MessageType.INFO);
        Runtime.getRuntime().addShutdownHook(Thread {
            tray.remove(trayIcon)
        })
    }
}

fun getTrayIcon(size: Dimension, color: Color = Color.ORANGE): BufferedImage {
    val image = BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB)
    image.createGraphics().apply {
        this.color = color
        this.fillOval(0, 0, size.width, size.height)
        this.color = Color.black
        this.drawChars(charArrayOf('/', '/'), 0, 2, size.width / 2 - this.font.size / 4, this.font.size);
        this.dispose()
    }

    return image
}

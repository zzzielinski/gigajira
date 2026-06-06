import os
from PySide6.QtCore import QFile, QIODevice
from PySide6.QtGui import QIcon, QPixmap
from PySide6.QtUiTools import QUiLoader

class Main:
    def __init__(self):
        CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
        UI_PATH = os.path.join(CURRENT_DIR, "main.ui")

        ui_file = QFile(UI_PATH)
        if not ui_file.open(QIODevice.ReadOnly):
            raise RuntimeError(f"Cannot open {UI_PATH}")

        loader = QUiLoader()
        self.window = loader.load(ui_file)
        ui_file.close()

        PARENT_DIR = os.path.dirname(CURRENT_DIR)
        smallLogoPath = os.path.join(PARENT_DIR, "gigaJiraLogoRounded-small.png")
        bigLogoPath = os.path.join(PARENT_DIR, "gigaJiraLogoRounded.png")

        self.window.setWindowIcon(QIcon(smallLogoPath))
        self.window.logo.setPixmap(QPixmap(bigLogoPath))

        self.window.stack.setCurrentIndex(0)
        self.window.homeButton.clicked.connect(self.goHome)
        self.window.calendarButton.clicked.connect(self.goCalendar)

    def show(self):
        self.window.show()

    def goHome(self):
        self.window.stack.setCurrentIndex(1)

    def goCalendar(self):
        self.window.stack.setCurrentIndex(0)
import os
from PySide6.QtCore import QFile, QIODevice
from PySide6.QtGui import QPixmap, QIcon
from PySide6.QtUiTools import QUiLoader

class Login:
    def __init__(self):
        CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
        UI_PATH = os.path.join(CURRENT_DIR, "login.ui")

        ui_file = QFile(UI_PATH)
        if not ui_file.open(QIODevice.ReadOnly):
            raise RuntimeError(f"Cannot open {UI_PATH}")

        loader = QUiLoader()
        self.window = loader.load(ui_file)
        ui_file.close()

        PARENT_DIR = os.path.dirname(CURRENT_DIR)
        bigLogoPath = os.path.join(PARENT_DIR, "gigaJiraLogoRounded.png")
        smallLogoPath = os.path.join(PARENT_DIR, "gigaJiraLogoRounded-small.png")

        self.window.logo.setPixmap(QPixmap(bigLogoPath))
        self.window.setWindowIcon(QIcon(smallLogoPath))

    # 5. Provide a passthrough so index.py can call .exec() normally
    def exec(self):
        self.window.exec()
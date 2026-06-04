import os
import sys
from PySide6.QtGui import QPixmap, QIcon
from PySide6.QtWidgets import QApplication
from PySide6.QtUiTools import loadUiType

LoginFormClass, LoginBaseClass = loadUiType("./login/ui/login.ui")
MainFormClass, MainBaseClass = loadUiType("./main/ui/main.ui")

class LoginWindow(LoginBaseClass, LoginFormClass):
    def __init__(self):
        super().__init__()
        self.setupUi(self)

        bigLogoPath = os.path.join(os.getcwd(), "gigaJiraLogoRounded.png")
        smallLogoPath = os.path.join(os.getcwd(), "gigaJiraLogoRounded-small.png")

        self.logo.setPixmap(QPixmap(bigLogoPath))
        self.setWindowIcon(QIcon(smallLogoPath))

if __name__ == "__main__":
    app = QApplication(sys.argv)

    loginWindow = LoginWindow()
    loginWindow.exec()
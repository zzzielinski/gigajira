import sys
from PySide6.QtWidgets import QApplication
from login.login import Login

if __name__ == "__main__":
    app = QApplication(sys.argv)

    loginWindow = Login()
    loginWindow.exec()
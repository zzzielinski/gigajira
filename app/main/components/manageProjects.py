import os
from PySide6.QtCore import QFile, QIODevice, Signal
from PySide6.QtUiTools import QUiLoader

class ManageProjects:
    def __init__(self):
        CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
        UI_PATH = os.path.join(CURRENT_DIR, "managementView.ui")

        ui_file = QFile(UI_PATH)
        if not ui_file.open(QIODevice.ReadOnly):
            raise RuntimeError(f"Cannot open {UI_PATH}")

        loader = QUiLoader()
        self.window = loader.load(ui_file)
        ui_file.close()

        self.window.addButton.clicked.connect(self.addCompany)
        self.window.homeButton.clicked.connect(self.goHome)
        self.window.listObj.setObjectName("projectList")

    def show(self):
        self.window.show()

    def addCompany(self):
        print("addProject")

    def goHome(self):
        parent = self.window.parent()

        parent.setCurrentIndex(1)
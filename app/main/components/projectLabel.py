import os
from PySide6.QtCore import QFile, QIODevice, Qt
from PySide6.QtUiTools import QUiLoader

class ProjectLabel:
    def __init__(self, id, project, company):
        CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
        UI_PATH = os.path.join(CURRENT_DIR, "projectLabel.ui")

        ui_file = QFile(UI_PATH)
        if not ui_file.open(QIODevice.ReadOnly):
            raise RuntimeError(f"Cannot open {UI_PATH}")

        loader = QUiLoader()
        self.window = loader.load(ui_file)
        ui_file.close()

        self.window.id.setText(id)
        self.window.project.setText(project)
        self.window.company.setText(company)

        self.window.setAttribute(Qt.WidgetAttribute.WA_StyledBackground, True)
        self.window.setStyleSheet("""
        #projectLabel {
                border: 1px solid #cccccc;
                border-radius: 15px;
                background-color: #8B0000;
            }
        """)

        self.window.manageButton.clicked.connect(self.manage)

    def show(self):
        self.window.show()

    def manage(self):
        print(f"Manage project {self.window.company.text()}")
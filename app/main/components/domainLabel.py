import os
from PySide6.QtCore import QFile, QIODevice, Qt
from PySide6.QtUiTools import QUiLoader

class DomainLabel:
    def __init__(self, id, domain, company):
        CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
        UI_PATH = os.path.join(CURRENT_DIR, "domainLabel.ui")

        ui_file = QFile(UI_PATH)
        if not ui_file.open(QIODevice.ReadOnly):
            raise RuntimeError(f"Cannot open {UI_PATH}")

        loader = QUiLoader()
        self.window = loader.load(ui_file)
        ui_file.close()

        self.window.id.setText(id)
        self.window.domain.setText(domain)
        self.window.company.setText(company)

        self.window.setAttribute(Qt.WidgetAttribute.WA_StyledBackground, True)
        self.window.setStyleSheet("""
            #domainLabel {
                border: 1px solid #cccccc;
                border-radius: 15px;
                background-color: #001C57;
            }
        """)

        self.window.manageButton.clicked.connect(self.manage)

    def show(self):
        self.window.show()

    def manage(self):
        print(f"Manage domain {self.window.domain.text()}")
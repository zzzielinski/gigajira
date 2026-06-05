import os
from PySide6.QtCore import QFile, QIODevice, Qt
from PySide6.QtUiTools import QUiLoader

class CalendarTask:
    def __init__(self, title, project, status, priority):
        CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
        UI_PATH = os.path.join(CURRENT_DIR, "calendarTask.ui")

        ui_file = QFile(UI_PATH)
        if not ui_file.open(QIODevice.ReadOnly):
            raise RuntimeError(f"Cannot open {UI_PATH}")

        loader = QUiLoader()
        self.window = loader.load(ui_file)
        ui_file.close()

        self.window.title.setText(title)
        self.window.project.setText(project)
        self.window.status.setText(status)
        self.window.priority.setText(str(priority))

        self.window.setAttribute(Qt.WidgetAttribute.WA_StyledBackground, True)
        self.window.setStyleSheet("""
            #calendarTask {
                border: 1px solid #cccccc;
                border-radius: 15px;
                background-color: #06402b;
            }
        """)

    def show(self):
        self.window.show()
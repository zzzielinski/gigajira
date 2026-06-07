import os
from PySide6.QtCore import QFile, QIODevice, Qt, QObject, Signal, QEvent
from PySide6.QtUiTools import QUiLoader

class ClickListener(QObject):
    clicked = Signal()

    def eventFilter(self, watched, event):
        if event.type() == QEvent.MouseButtonPress and event.button() == Qt.MouseButton.LeftButton:
            self.clicked.emit()
            return True
        return super().eventFilter(watched, event)

class CalendarTask:
    def __init__(self, title, project, status, priority, id):
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
        self.id = int(id)

        self.window.setAttribute(Qt.WidgetAttribute.WA_StyledBackground, True)
        self.window.setStyleSheet("""
            #calendarTask {
                border: 1px solid #cccccc;
                border-radius: 15px;
                background-color: #06402b;
            }
        """)

        self.clickListener = ClickListener()
        self.window.installEventFilter(self.clickListener)
        self.clickListener.clicked.connect(self.manage)

    def show(self):
        self.window.show()

    def manage(self):
        print(f"Manage task {self.id}")
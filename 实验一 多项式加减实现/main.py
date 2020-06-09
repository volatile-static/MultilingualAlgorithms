import sys
import matplotlib.pyplot as plt
from 多项式 import Polynomial as poly
from 输入框 import Ui_MainWindow as mForm
from PyQt5 import QtWidgets
from PyQt5.QtWidgets import QMessageBox

class TopGUI(QtWidgets.QMainWindow, mForm):
    def __init__(self, parent=None):
        QtWidgets.QMainWindow.__init__(self, parent)
        self.setupUi(self)
        self.pushButton.clicked.connect(self.Calculate)

    def Calculate(self):
        A = poly(self.EditA.text())   
        B = poly(self.EditB.text())  
        C = A + B                
        print(C)

        ax = plt.subplot(111)
        #ax.text(0.1, 0.2, str(C), fontsize=30, color="red")
        #plt.show()
        # QMessageBox.information(self, '233', '666', QMessageBox.Ok)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    myForm = TopGUI()
    myForm.show()
    sys.exit(app.exec_())
    
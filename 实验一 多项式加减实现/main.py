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
        D = A - B
        E = A * B
        F = A / B

        ax = plt.subplot(111)
        ax.text(0.01, 0.9, 'A(x) = ' + str(A), fontsize = 36, color = "green")
        ax.text(0.01, 0.8, 'B(x) = ' + str(B), fontsize = 36, color = 'red')
        ax.text(0.01, 0.7, 'C(x) = A(x) + B(x) = ' + str(C), fontsize = 36, color = "blue")
        ax.text(0.01, 0.6, 'D(x) = A(x) - B(x) = ' + str(D), fontsize = 36, color = "lime")
        ax.text(0.01, 0.5, 'E(x) = A(x) * B(x) = ' + str(E), fontsize = 36, color = "purple")
        ax.text(0.01, 0.4, 'F(x) = A(x) / B(x) = ' + str(F), fontsize = 36, color = "pink")
        plt.show()
        # QMessageBox.information(self, '233', '666', QMessageBox.Ok)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    myForm = TopGUI()
    myForm.show()
    sys.exit(app.exec_())
    
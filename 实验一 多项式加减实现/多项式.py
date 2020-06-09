# -*- coding: utf-8 -*-

class PolyItem:
    '多项式的一项'
    def __init__(self, coef, expon):
        self.coefficient = coef  # 系数
        self.exponential = expon  # 指数

    def __str__(self):
        return '%d %d' % (self.coefficient, self.exponential)
    
    def __add__(self, value):
        return PolyItem(self.coefficient + value.coefficient, self.exponential)

    def __sub__(self, value):
        return PolyItem(self.coefficient - value.coefficient, self.exponential)

    def __mul__(self, value):
        return PolyItem(self.coefficient * value.coefficient, self.exponential + value.exponential)

    def __truediv__(self, value):
        return PolyItem(self.coefficient / value.coefficient, self.exponential - value.exponential)
        
    def __gt__(self, value):
        return self.exponential > value.exponential

    def __eq__(self, value):
        return self.exponential == value.exponential


def Merge(a1, a2, add_sub):  # 归并有序表并去重
    tmp = []
    p1 = p2 = 0
    while p1 < len(a1) and p2 < len(a2):
        if a1[p1] > a2[p2]:
            tmp.items.append(a2[p2])
            p2 += 1
        elif a1[p1] == a1[p2]:
            tmp.items.append(a1[p1] + a2[p2] if add_sub else a1[p1] - a2[p2])
            p1 += 1
            p2 += 1
        else:
            tmp.items.append(a1[p1])
            p1 += 1
    return tmp


class Polynomial:
    '一个多项式'
    def __init__(self, st):
        self.items = []
        try:
            for i in st.split('+'):  # 用加号分割多项式
                s = i.split('x^')
                self.items.append(PolyItem(int(s[0]), int(s[1])))  # 添加一项
        except:
            self.items.clear()
            return
        self.items.sort()
        
    def __str__(self):
        ret = ''
        for it in self.items:
            ret += '%dx^%d+' % (it.coefficient, it.exponential)
        return ret[0 : len(ret) - 1]  # 去掉末尾的加号

    def __add__(self, other):
        ret = Polynomial('')
        ret.items = Merge(self.items, other.items, True)
        return ret

    def __sub__(self, other):
        ret = Polynomial('')
        ret.items = Merge(self.items, other.items, False)
        return ret

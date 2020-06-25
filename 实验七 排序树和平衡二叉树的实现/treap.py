from random import random
from typing import Tuple


class Node:
    def __init__(self, value: int = None):
        self.value = value
        self.prior = random()
        self.left = None
        self.right = None

    def __repr__(self):
        from pprint import pformat

        if self.left is None and self.right is None:
            return f"'{self.value}: {self.prior:.5}'"
        else:
            return pformat(
                {f"{self.value}: {self.prior:.5}": (self.left, self.right)}, indent=1
            )


def split(root: Node, value: int) -> Tuple[Node, Node]:
    """
    用给定值划分左右子树
    左边小右边大
    """
    if root is None: 
        return (None, None)
    elif root.value is None:
        return (None, None)
    else:
        if value < root.value:
            left, root.left = split(root.left, value)
            return (left, root)
        else:
            root.right, right = split(root.right, value)
            return (root, right)


def merge(left: Node, right: Node) -> Node:
    """
    合并左右子树
    左子树所有节点小于右子树
    """
    if (not left) or (not right):
        return left or right
    elif left.prior < right.prior:
        "左子树作为根"
        left.right = merge(left.right, right)
        return left
    else:
        right.left = merge(left, right.left)
        return right


def insert(root: Node, value: int) -> Node:
    """
    插入元素
    先划用当前值分左右子树
    插入当前节点
    合并各子树
    """
    node = Node(value)
    left, right = split(root, value)
    return merge(merge(left, node), right)


def erase(root: Node, value: int) -> Node:
    """
    删除元素
    先分后合
    """
    left, right = split(root, value - 1)
    _, right = split(right, value)
    return merge(left, right)


def inorder(root: Node):
    '中序遍历'
    if not root:  # None
        return
    else:
        inorder(root.left)
        print(root.value, end=" ")
        inorder(root.right)


def interactTreap(root, args):
    for arg in args.split():
        if arg[0] == "+":
            root = insert(root, int(arg[1:]))
        elif arg[0] == "-":
            root = erase(root, int(arg[1:]))
        else:
            print("未知命令")
    return root


if __name__ == "__main__":
    root = None
    print(
        "输入数字建树"
        "+1代表将1加入平衡树，-1代表把所有1删除"
        "输入q退出"
    )
    args = input()
    while args != "q":
        root = interactTreap(root, args)
        print(root)
        args = input()
    inorder(root)

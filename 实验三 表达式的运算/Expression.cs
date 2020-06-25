using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace 表达式求值
{
    delegate double Calc(double a, double b);

    public class Expression
    {
        Calc[] ops = new Calc[6];
        const int op_cnt = 9;
        private char[,] pri = new char[op_cnt, op_cnt] { //运算符优先等级 [栈顶] [当前]
   /*              |-------------------- 当 前 运 算 符 --------------------| */
   /*                 +      -      *      /      ^      ,      (      )      \0 */
   /* --  + */ { '>',   '>',   '<',   '<',   '<',   '>',   '<',   '>',   '>' },
   /* |   - */  { '>',   '>',   '<',   '<',   '<',   '>',   '<',   '>',   '>' },
   /* 栈  * */ { '>',   '>',   '>',   '>',   '<',   '>',   '<',   '>',   '>' },
   /* 顶  / */ { '>',   '>',   '>',   '>',   '<',   '>',   '<',   '>',   '>' },
   /* 运  ^ */ { '>',   '>',   '>',   '>',   '>',   '>',   '<',   '>',   '>' },
   /* 算  , */  { '<',   '<',   '<',   '<',   '<',   '>',   ' ',   '>',   '>' },
   /* 符  ( */  { '<',   '<',   '<',   '<',   '<',   '<',   '<',   '=',   ' ' },
   /* |   ) */   { ' ',   ' ',   ' ',   ' ',   ' ',   ' ',   ' ',   ' ',   ' ' },
   /* -- \0 */ { '<',   '<',   '<',   '<',   '<',   '<',   '<',   ' ',   '=' }
        };
        private Dictionary<char, int> dec = new Dictionary<char, int>
        { { '+', 0 }, { '-', 1 }, { '*', 2 }, { '/', 3 }, { '^', 4 }, { ',', 5 }, { '(', 6 }, { ')', 7 }, {'\0', 8 } };
        private ArrayList rpn = new ArrayList();

        private bool IsDigit(char c)
        {
            return c >= '0' && c <= '9';
        }

        double Add(double a, double b)
        {
            return a + b;
        }

        double Sub(double a, double b)
        {
            return a - b;
        }

        double Mul(double a, double b)
        {
            return a * b;
        }

        double Div(double a, double b)
        {
            return a / b;
        }

        double Max(double a, double b)
        {
            return a > b ? a : b;
        }

        private void GetRPN(string exp)
        {
            TStack<char> op = new TStack<char>();
            op.Push('\0');
            int i = 0;
            do
            {
                if (i < exp.Length && IsDigit(exp[i]))
                {
                    int tmp = 0;
                    while (i < exp.Length && IsDigit(exp[i]))
                    {
                        tmp += exp[i++] - '0';
                        tmp *= 10;
                    }
                    rpn.Add((double)tmp / 10);
                }
                if (i < exp.Length)
                    switch (pri[dec[op.Top()], dec[exp[i]]])
                    {
                        case '<':
                            op.Push(exp[i++]);
                            continue;
                        case '>':
                            rpn.Add(op.Top());
                            op.Pop();
                            continue;
                        case '=':
                            op.Pop();
                            ++i;
                            continue;
                    }
                else while (op.Size() > 0)
                    {
                        rpn.Add(op.Top());
                        op.Pop();
                    }
            } while (op.Size() > 0);
        }

        public Expression(string exp)
        {
            ops[0] = Add;
            ops[1] = Sub;
            ops[2] = Mul;
            ops[3] = Div;
            ops[5] = Max;

            // 预处理函数
            while (true)
            {
                if (!exp.Contains("max"))
                    break;
                int pos = exp.IndexOf("max");
                exp = exp.Remove(pos, 3);
            }

            // 预处理负数
            for (int i = 0; i < exp.Length; ++i)
                if (exp[i] == '-' && (i > 0 && !IsDigit(exp[i - 1]) || i == 0))
                    exp = exp.Insert(i, "0");  //!< 补0
            GetRPN(exp);
        }

        public double Evaluate()
        {
            TStack<double> opnd = new TStack<double>();
            for (int i = 0; ; ++i)
                if (rpn[i] is char)
                {
                    char ch = (char)rpn[i];
                    if (ch == '\0')
                        return opnd.Top();

                    double a = opnd.Top();
                    opnd.Pop();
                    double b = opnd.Top();
                    opnd.Pop();

                    opnd.Push(ops[dec[ch]](b, a));
                }
                else
                    opnd.Push((double)rpn[i]);
        }
    }
}

unit evaluate;

interface
function ExpressonEvaluate(exp: string): double;

implementation
uses stacks;

const
  pri: array[1..2, 1..2]of char = (('>', '>'), ('>', '='));

procedure RPN(inpu: string; var outpu: TOrderStack);
begin

end;

function Calc(rpn: TOrderStack): double;
begin

end;

function ExpressonEvaluate(exp: string): double;
var
  rpn: TOrderStack;
begin
  rpn := TOrderStack.Create;
  RPN(exp, rpn);
  Result := Calc(rpn)
end;
end.


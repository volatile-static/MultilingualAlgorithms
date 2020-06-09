unit stacks;

interface
type
  TOrderStack = class
  private
    buffer: array[1..666]of char;
    p: integer;
  public
    constructor Create;
    procedure push(c: char);
    procedure pop;
    function top : char;
    function size : integer;
  end;

  TLinkStack = class

  end;

implementation
constructor TOrderStack.Create;
begin
  inherited Create;
  p := 0
end;

procedure TOrderStack.push(c: char);
begin
  Inc(p);
  buffer[p] := c;
end;

procedure TOrderStack.pop;
begin
  Dec(p);
end;

function TOrderStack.top;
begin
  Result := buffer[p];
end;

function TOrderStack.size;
begin
  Result := p;
end;

end.

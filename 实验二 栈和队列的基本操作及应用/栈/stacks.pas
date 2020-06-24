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
    private
      next: ^TLinkStack;
      dat: char;
    public
      constructor Create;
      procedure Push(c: char);
      function Pop : char;
      function Size : integer;
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


constructor TLinkStack.Create;
begin
  inherited Create;
  next := nil;
  dat := #0;
end;

procedure TLinkStack.Push(c: Char);
begin
  new(next);
end;

function TLinkStack.Pop;
begin

end;

function TLinkStack.Size;
var
  i: ^TLinkStack;
  s: integer;
begin
  while i^.next <> nil do
    begin
      Inc(s);
      // i := i^.next;
    end;
  Result := s;
end;
end.
